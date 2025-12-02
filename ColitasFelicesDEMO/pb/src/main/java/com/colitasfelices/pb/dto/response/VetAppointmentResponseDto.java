package com.colitasfelices.pb.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VetAppointmentResponseDto {
    private Long id;
    private String hora;         // Ej: "10:00"
    private String cliente;      // Ej: "Juan Pérez"
    private String mascota;      // Ej: "Fido"
    private String raza;
    private String servicio;     // Ej: "Vacunación"
    private String comentarios;  // Comentario opcional
    private String estado;       // Ej: "PENDIENTE"
}
