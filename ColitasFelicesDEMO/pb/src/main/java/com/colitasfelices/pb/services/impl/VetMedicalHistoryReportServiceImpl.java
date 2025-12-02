package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.reportes.HistorialMedicoMascotaDTO;
import com.colitasfelices.pb.entity.AtencionMedica;
import com.colitasfelices.pb.repository.AtencionMedicaRepository;
import com.colitasfelices.pb.services.interfaces.VetMedicalHistoryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VetMedicalHistoryReportServiceImpl implements VetMedicalHistoryReportService {

    private final AtencionMedicaRepository atencionMedicaRepository;

    @Override
    public List<HistorialMedicoMascotaDTO> obtenerHistorialMedico(Long petId) {
        List<AtencionMedica> atenciones = atencionMedicaRepository.findByCitaPetIdOrderByCitaFechaAsc(petId);

        return atenciones.stream().map(at -> {
            var dto = new HistorialMedicoMascotaDTO();
            dto.setFecha(at.getCita().getFecha());
            dto.setServicio(at.getCita().getServicio().getDescripcion());
            dto.setDiagnostico(at.getDiagnostico());
            dto.setTratamiento(at.getTratamiento());
            dto.setObservaciones(at.getObservacionesClinicas());
            return dto;
        }).collect(Collectors.toList());
    }
}