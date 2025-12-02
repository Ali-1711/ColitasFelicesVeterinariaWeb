package com.colitasfelices.pb.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateAppointmentRequestDto {
    private Long veterinarioId;  // puede ser veterinario o interno
    private Long servicioId;
}