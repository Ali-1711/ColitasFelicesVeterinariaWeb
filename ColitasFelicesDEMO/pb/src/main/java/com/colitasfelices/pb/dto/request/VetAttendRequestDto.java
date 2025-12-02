package com.colitasfelices.pb.dto.request;

import lombok.Data;

@Data
public class VetAttendRequestDto {
    private String observacionesClinicas;
    private String diagnostico;
    private String tratamiento;
    private String prescripcion;

    private Long practicanteId; // ← NECESARIO
}
