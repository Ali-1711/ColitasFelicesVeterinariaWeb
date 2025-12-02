package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.request.InternAttendRequestDto;
import com.colitasfelices.pb.entity.AtencionMedica;
import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.entity.User;
import com.colitasfelices.pb.exception.ResourceNotFoundException;
import com.colitasfelices.pb.repository.AtencionMedicaRepository;
import com.colitasfelices.pb.repository.CitaRepository;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.services.interfaces.InternAttendService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternAttendServiceImpl implements InternAttendService {

    private final CitaRepository citaRepository;
    private final AtencionMedicaRepository atencionMedicaRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void registrarAtencionMedica(Long citaId, InternAttendRequestDto dto, String emailInterno) {
        // Buscar la cita
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + citaId));

        if (!"DERIVADA".equals(cita.getEstado())) {
            throw new IllegalStateException("Solo se puede registrar atención médica para citas derivadas.");
        }

        // Buscar el ID del usuario practicante a partir del correo
        User practicante = userRepository.findByCorreo(emailInterno)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con correo: " + emailInterno));

        // Crear la atención médica
        AtencionMedica atencion = new AtencionMedica();
        atencion.setCita(cita);
        atencion.setObservacionesClinicas(dto.getObservacionesClinicas());
        atencion.setDiagnostico(dto.getDiagnostico());
        atencion.setTratamiento(dto.getTratamiento());
        atencion.setPrescripcion(dto.getPrescripcion());
        atencion.setRegistradaPorId(practicante.getId()); // ← Asigna ID directamente
        atencion.setEstadoValidacion("EVALUADA");
        atencion.setActivo(true);

        atencionMedicaRepository.save(atencion);

        // Actualizar el estado de la cita
        cita.setEstado("EVALUADA");
        citaRepository.save(cita);
    }
}