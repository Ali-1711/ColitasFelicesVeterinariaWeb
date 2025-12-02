package com.colitasfelices.pb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VetAppointmentDetailResponseDto {
    private Long id;
    private String nombreCliente;
    private String emailCliente;
    private String nombreMascota;
    private String tipoMascota;
    private String razaMascota;
    private Integer edadMascota;
    private String servicio;
    private String fecha;
    private String hora;
}
