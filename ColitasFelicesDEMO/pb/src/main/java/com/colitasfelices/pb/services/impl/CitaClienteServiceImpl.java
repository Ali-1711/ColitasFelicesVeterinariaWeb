package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.response.AppointmentPaidResponseDto;
import com.colitasfelices.pb.dto.response.AppointmentScheduledResponseDto;
import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.repository.CitaRepository;
import com.colitasfelices.pb.services.interfaces.CitaClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaClienteServiceImpl implements CitaClienteService {

    private final CitaRepository citaRepository;

    @Override
    public List<AppointmentScheduledResponseDto> obtenerCitasProgramadasPorUsuario(String correoUsuario) {
        List<Cita> citas = citaRepository.findByUsuarioCorreoAndEstadoIgnoreCase(correoUsuario, "COMPLETADA");

        return citas.stream().map(cita ->
                AppointmentScheduledResponseDto.builder()
                        .citaId(cita.getId())
                        .nombreMascota(cita.getPet().getName())
                        .razaMascota(cita.getPet().getBreed())
                        .tipoServicio(cita.getServicio().getDescripcion())
                        .monto(cita.getPrecioCobrado())
                        .fechaCita(cita.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .estado(cita.getEstado())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
public List<AppointmentPaidResponseDto> obtenerCitasPagadasPorUsuario(String correo) {
    List<Cita> citas = citaRepository.findAllByUsuarioCorreoAndEstado(correo, "pagada");

    return citas.stream()
            .map(cita -> AppointmentPaidResponseDto.builder()
                    .citaId(cita.getId())
                    .nombreMascota(cita.getPet().getName())
                    .razaMascota(cita.getPet().getBreed())
                    .tipoServicio(cita.getServicio().getDescripcion())
                    .monto(cita.getPrecioCobrado())
                    .fechaCita(cita.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .estado(cita.getEstado())
                    .build())
            .collect(Collectors.toList());
}
}
