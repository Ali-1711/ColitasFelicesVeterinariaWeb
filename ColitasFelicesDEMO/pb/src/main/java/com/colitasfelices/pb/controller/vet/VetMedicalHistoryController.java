package com.colitasfelices.pb.controller.vet;

import com.colitasfelices.pb.dto.request.AttendAppointmentRequestDto;
import com.colitasfelices.pb.dto.response.AppointmentDetailResponseDto;
import com.colitasfelices.pb.services.interfaces.VetClinicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vet/appointments")
@RequiredArgsConstructor
public class VetMedicalHistoryController {

    private final VetClinicalRecordService vetClinicalRecordService;

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailResponseDto> getAppointmentDetails(@PathVariable Long id) {
        AppointmentDetailResponseDto response = vetClinicalRecordService.getAppointmentDetails(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/attend")
    public ResponseEntity<Void> attendAppointment(@PathVariable Long id,
                                                  @RequestBody AttendAppointmentRequestDto dto) {
        vetClinicalRecordService.saveMedicalAttention(id, dto);
        return ResponseEntity.ok().build();
    }
}
