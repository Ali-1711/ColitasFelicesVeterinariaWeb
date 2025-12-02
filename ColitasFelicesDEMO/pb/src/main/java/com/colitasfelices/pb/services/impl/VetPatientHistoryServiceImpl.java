package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.response.VetClientWithPetResponseDto;
import com.colitasfelices.pb.dto.response.VetClinicalRecordResponseDto;
import com.colitasfelices.pb.dto.response.VetHistoryPetResponseDto;
import com.colitasfelices.pb.dto.response.VetPatientListResponseDto;
import com.colitasfelices.pb.entity.AtencionMedica;
import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.entity.Pet;
import com.colitasfelices.pb.entity.User;
import com.colitasfelices.pb.repository.AtencionMedicaRepository;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.repository.CitaRepository;
import com.colitasfelices.pb.services.interfaces.VetPatientHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetPatientHistoryServiceImpl implements VetPatientHistoryService {

    private final AtencionMedicaRepository atencionMedicaRepository;
    private final UserRepository userRepository;
    private final CitaRepository citaRepository;

    @Override
    public List<VetPatientListResponseDto> getAllPatientsWithHistory() {
        List<AtencionMedica> records = atencionMedicaRepository.findAllWithPetAndUser();

        return records.stream()
                .map(att -> new VetPatientListResponseDto(
                        att.getCita().getPet().getId(),
                        att.getCita().getPet().getName(),
                        att.getCita().getPet().getBreed(),
                        att.getCita().getPet().getType(),
                        att.getCita().getUsuario().getNombres()
                ))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
public List<VetClinicalRecordResponseDto> getClinicalHistoryByPet(Long petId) {
    return atencionMedicaRepository.findByPetId(petId).stream()
            .filter(record -> "PAGADA".equalsIgnoreCase(record.getCita().getEstado())) // solo citas pagadas
            .map(record -> VetClinicalRecordResponseDto.builder()
                    .fecha(record.getCita().getFecha())
                    .servicio(record.getCita().getServicio().getDescripcion())
                    .diagnostico(record.getDiagnostico())
                    .tratamiento(record.getTratamiento())
                    .observaciones(record.getObservacionesClinicas())
                    .build())
            .collect(Collectors.toList());
}

    @Override
public List<VetClientWithPetResponseDto> obtenerClientesAtendidos(String correoVeterinario) {
    User vetUser = userRepository.findByCorreo(correoVeterinario)
            .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));

    Long vetId = vetUser.getVeterinario().getId();

    List<Cita> citas = citaRepository.findByVeterinarioIdAndEstado(vetId, "COMPLETADA");

    Map<User, List<Pet>> clienteMascotasMap = citas.stream()
            .collect(Collectors.groupingBy(
                    Cita::getUsuario,
                    Collectors.mapping(Cita::getPet, Collectors.toList())
            ));

    List<VetClientWithPetResponseDto> resultado = new ArrayList<>();

    for (Map.Entry<User, List<Pet>> entry : clienteMascotasMap.entrySet()) {
        User cliente = entry.getKey();
        List<Pet> mascotas = entry.getValue();

        // Usar Set para filtrar mascotas únicas
        Set<String> mascotaKeys = new HashSet<>();
        List<VetHistoryPetResponseDto> mascotaDtos = mascotas.stream()
                .filter(m -> mascotaKeys.add(m.getName() + "|" + m.getType() + "|" + m.getBreed()))
                .map(m -> new VetHistoryPetResponseDto(
                        m.getName(),
                        m.getType(),
                        m.getBreed()
                ))
                .toList();

        resultado.add(new VetClientWithPetResponseDto(
                cliente.getNombres() + " " + cliente.getApellidoPaterno(),
                cliente.getCorreo(),
                mascotaDtos
        ));
    }

    return resultado;
}



}

