package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.exception.ResourceNotFoundException;
import com.colitasfelices.pb.dto.request.VetAttendRequestDto;
import com.colitasfelices.pb.dto.response.VetAppointmentResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentInternDetailResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentValidateResponseDto;
import com.colitasfelices.pb.entity.AtencionMedica;
import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.entity.User;
import com.colitasfelices.pb.repository.AtencionMedicaRepository;
import com.colitasfelices.pb.repository.CitaRepository;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.services.interfaces.VetAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.colitasfelices.pb.entity.Veterinario;
import com.colitasfelices.pb.security.AuthUtils;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetAppointmentServiceImpl implements VetAppointmentService {

    private final CitaRepository citaRepository;
    private final UserRepository userRepository;
    private final AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public List<VetAppointmentResponseDto> getAppointmentsForDate(LocalDate date) {
        String correo = AuthUtils.getAuthenticatedEmail();
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Veterinario vet = user.getVeterinario();
        if (vet == null) throw new RuntimeException("Usuario no es veterinario");

        List<Cita> citas = citaRepository.findByVeterinarioIdAndFecha(vet.getId(), date);

        return citas.stream().map(cita -> {
            String nombreCliente = cita.getUsuario().getNombres() + " " + cita.getUsuario().getApellidoPaterno();
            String nombreMascota = cita.getPet() != null ? cita.getPet().getName() : "(No registrada)";
            String razaMascota = cita.getPet().getBreed();
            String nombreServicio = cita.getServicio().getDescripcion();
            String comentarios = ""; // Agregar cuando la entidad Cita tenga un campo comentarios

            return new VetAppointmentResponseDto(
                    cita.getId(),
                    cita.getHora().toString(),
                    nombreCliente,
                    nombreMascota,
                    razaMascota,
                    nombreServicio,
                    comentarios,
                    cita.getEstado()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<VetAppointmentValidateResponseDto> getCitasEvaluadasPendientesValidacion() {
        List<Cita> citas = citaRepository.findByEstado("EVALUADA");

        return citas.stream().map(cita -> VetAppointmentValidateResponseDto.builder()
                .citaId(cita.getId())
                .nombreCliente(cita.getUsuario().getNombres())
                .nombreMascota(cita.getPet().getName())
                .tipoMascota(cita.getPet().getType())
                .razaMascota(cita.getPet().getBreed())
                .servicio(cita.getServicio().getDescripcion())
                .fecha(cita.getFecha().toString())
                .hora(cita.getHora().toString())
                .nombrePracticante(cita.getIntern().getNombres())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public VetAppointmentInternDetailResponseDto getDetalleEvaluacion(Long citaId) {
        AtencionMedica atencion = atencionMedicaRepository.findByCita_Id(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Atención médica no encontrada para esta cita"));

        Cita cita = atencion.getCita();

        return VetAppointmentInternDetailResponseDto.builder()
                .citaId(cita.getId())
                .observacionesClinicas(atencion.getObservacionesClinicas())
                .diagnostico(atencion.getDiagnostico())
                .tratamiento(atencion.getTratamiento())
                .prescripcion(atencion.getPrescripcion())
                .build();
    }

    @Override
    public void actualizarEstadoCita(Long citaId, String nuevoEstado) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + citaId));
        cita.setEstado(nuevoEstado);
        citaRepository.save(cita);
    }
    @Override
    public void registrarAtencion(Long citaId, VetAttendRequestDto request) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + citaId));

        // Crear la atención médica
        AtencionMedica atencion = AtencionMedica.builder()
                .cita(cita)
                .observacionesClinicas(request.getObservacionesClinicas())
                .diagnostico(request.getDiagnostico())
                .tratamiento(request.getTratamiento())
                .prescripcion(request.getPrescripcion())
                .build();

        atencionMedicaRepository.save(atencion);

        // Cambiar el estado de la cita
        cita.setEstado("EVALUADA");
        citaRepository.save(cita);
    }

    @Override
    public void assignInternToAppointment(Long citaId, Long internId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + citaId));

        User intern = userRepository.findById(internId)
                .orElseThrow(() -> new ResourceNotFoundException("Practicante no encontrado con ID: " + internId));

        cita.setIntern(intern);
        citaRepository.save(cita);
    }

}
