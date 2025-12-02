package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.AdminCreateUserRequestDto;
import com.colitasfelices.pb.dto.request.AdminUpdateUserRequestDto;
import com.colitasfelices.pb.dto.response.AdminUserResponseDto;

import java.util.List;

public interface AdminUserService {
    List<AdminUserResponseDto> listarUsuariosPorRol(String rol);
    
void toggleEstadoUsuario(Long id, String motivo); 

    void crearUsuario(AdminCreateUserRequestDto dto);

    void desactivarUsuarioConMotivo(Long id, String motivo);

    void actualizarUsuario(Long id, AdminUpdateUserRequestDto dto);
}
