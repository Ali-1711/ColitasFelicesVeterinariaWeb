// HistorialMedicoService.java
package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AppointmentDetailResponseDto;
import com.colitasfelices.pb.dto.request.AttendAppointmentRequestDto;

public interface VetClinicalRecordService {

    AppointmentDetailResponseDto getAppointmentDetails(Long citaId);

    void saveMedicalAttention(Long citaId, AttendAppointmentRequestDto dto);
}
