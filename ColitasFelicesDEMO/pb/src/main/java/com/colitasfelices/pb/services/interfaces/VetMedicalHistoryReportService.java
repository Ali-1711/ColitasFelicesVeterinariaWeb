package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.reportes.HistorialMedicoMascotaDTO;

import java.util.List;

public interface VetMedicalHistoryReportService {
    List<HistorialMedicoMascotaDTO> obtenerHistorialMedico(Long petId);
}
