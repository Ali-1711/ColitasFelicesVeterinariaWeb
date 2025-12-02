package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.AdminCreateServiceRequestDto;
import com.colitasfelices.pb.dto.response.AdminServiceResponseDto;

import java.util.List;

public interface AdminServiceService {

    void registrarServicio(AdminCreateServiceRequestDto dto);

    List<AdminServiceResponseDto> listarServicios();

    void cambiarEstadoServicio(Long servicioId);

    void editarServicio(Long id, AdminCreateServiceRequestDto dto);

    void eliminarServicio(Long id);
}