package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.VetAttendRequestDto;
import com.colitasfelices.pb.dto.response.VetAppointmentResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentInternDetailResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentValidateResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface VetAppointmentService {
    List<VetAppointmentResponseDto> getAppointmentsForDate(LocalDate date);
    List<VetAppointmentValidateResponseDto> getCitasEvaluadasPendientesValidacion();
    VetAppointmentInternDetailResponseDto getDetalleEvaluacion(Long citaId);
    void actualizarEstadoCita(Long citaId, String nuevoEstado);
    void registrarAtencion(Long citaId, VetAttendRequestDto request);
    void assignInternToAppointment(Long citaId, Long internId);

}
