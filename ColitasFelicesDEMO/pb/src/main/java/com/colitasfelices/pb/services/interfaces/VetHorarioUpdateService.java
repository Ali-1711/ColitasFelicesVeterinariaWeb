package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.HorarioUpdateRequest;

import java.util.List;

public interface VetHorarioUpdateService {
    void actualizarHorariosSemanaSiguiente(List<HorarioUpdateRequest> nuevosHorarios, String correoVeterinario);
}