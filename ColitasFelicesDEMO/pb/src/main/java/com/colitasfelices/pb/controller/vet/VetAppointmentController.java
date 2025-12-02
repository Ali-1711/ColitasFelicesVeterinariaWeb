package com.colitasfelices.pb.controller.vet;

import com.colitasfelices.pb.dto.request.VetAttendRequestDto;
import com.colitasfelices.pb.dto.request.VetAppointmentStatusUpdateRequestDto;
import com.colitasfelices.pb.dto.response.VetAppointmentResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentValidateResponseDto;
import com.colitasfelices.pb.dto.response.VetAppointmentInternDetailResponseDto;
import com.colitasfelices.pb.services.interfaces.VetAppointmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vet")
@RequiredArgsConstructor
public class VetAppointmentController {

    private final VetAppointmentService vetAppointmentService;

    //----------------- OBTENER CITAS POR FECHA --------------------//
    @GetMapping("/appointments")
    public ResponseEntity<List<VetAppointmentResponseDto>> getAppointmentsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<VetAppointmentResponseDto> citas = vetAppointmentService.getAppointmentsForDate(date);
        return ResponseEntity.ok(citas);
    }

    // -------------------- ACTUALIZAR ESTADO DE UNA CITA --------------------

    @PutMapping("/appointments/{citaId}/estado")
    public ResponseEntity<Void> actualizarEstadoCita(
            @PathVariable Long citaId,
            @RequestBody VetAppointmentStatusUpdateRequestDto dto) {
        vetAppointmentService.actualizarEstadoCita(citaId, dto.getEstado());
        return ResponseEntity.ok().build();
    }

    // -------------------- REGISTRAR ATENCION MEDICA --------------------
    @PostMapping("/appointments/{citaId}/atencion")
    public ResponseEntity<Void> registrarAtencion(
            @PathVariable Long citaId,
            @RequestBody VetAttendRequestDto request) {

        vetAppointmentService.registrarAtencion(citaId, request);
        return ResponseEntity.ok().build();
    }

    // -------------------- ASIGNAR INTERN A UNA CITA --------------------
    @PutMapping("/appointments/{citaId}/assign-intern/{internId}")
    public ResponseEntity<Void> assignInternToAppointment(
            @PathVariable Long citaId,
            @PathVariable Long internId) {

        vetAppointmentService.assignInternToAppointment(citaId, internId);
        return ResponseEntity.ok().build();
    }

    // -------------------- OBTENER DETALLE DE EVALUACION --------------------
    @GetMapping("/appointments/{citaId}/detalle-evaluacion")
    public ResponseEntity<VetAppointmentInternDetailResponseDto> getDetalleEvaluacion(
            @PathVariable Long citaId) {

        VetAppointmentInternDetailResponseDto detalle = vetAppointmentService.getDetalleEvaluacion(citaId);
        return ResponseEntity.ok(detalle);
    }

    // -------------------- OBTENER CITAS EVALUADAS PENDIENTES DE VALIDACION --------------------
    @GetMapping("/appointments/pending-validation")
    public ResponseEntity<List<VetAppointmentValidateResponseDto>> getCitasEvaluadasPendientesValidacion() {

        List<VetAppointmentValidateResponseDto> citas = vetAppointmentService.getCitasEvaluadasPendientesValidacion();
        return ResponseEntity.ok(citas);
    }

}
