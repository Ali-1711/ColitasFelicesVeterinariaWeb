package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.InternAttendRequestDto;

public interface InternAttendService {
    void registrarAtencionMedica(Long citaId, InternAttendRequestDto dto, String emailInterno);
}
