package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AppointmentPaidResponseDto;
import com.colitasfelices.pb.dto.response.AppointmentScheduledResponseDto;

import java.util.List;

public interface CitaClienteService {
    List<AppointmentScheduledResponseDto> obtenerCitasProgramadasPorUsuario(String correoUsuario);
    List<AppointmentPaidResponseDto> obtenerCitasPagadasPorUsuario(String correo);
}
