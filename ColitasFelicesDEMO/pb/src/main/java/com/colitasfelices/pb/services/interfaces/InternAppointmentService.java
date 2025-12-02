package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.InternAppointmentResponseDto;
import com.colitasfelices.pb.dto.response.InternCitaValidadaResponseDto;

import java.util.List;

public interface InternAppointmentService {
    List<InternAppointmentResponseDto> getCitasDerivadasDelInterno(Long internId);
    void marcarCitasDerivadasComoVistas(Long internId); 
    List<InternCitaValidadaResponseDto> getCitasValidadasPorIntern(Long internId);
    List<InternAppointmentResponseDto> getCitasDerivadasNoVistas(Long internId);

}
