package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.AdminUpdateAppointmentRequestDto;
import com.colitasfelices.pb.dto.response.AdminAppointmentResponseDto;

import java.util.List;

public interface AdminAppointmentService {
    List<AdminAppointmentResponseDto> listarCitas();
    void actualizarCita(Long id, AdminUpdateAppointmentRequestDto dto);
}
