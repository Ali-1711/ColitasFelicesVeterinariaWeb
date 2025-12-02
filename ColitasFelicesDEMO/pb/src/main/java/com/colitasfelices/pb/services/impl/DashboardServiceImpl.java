package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.dashboard.DashboardClientResponseDto;
import com.colitasfelices.pb.entity.User;
import com.colitasfelices.pb.repository.UserRepository;
import com.colitasfelices.pb.services.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    @Override
    public DashboardClientResponseDto getClientDashboard(Authentication authentication) {
        String correo = authentication.getName(); // email del usuario autenticado

        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String fullName = user.getNombres() + " " + user.getApellidoPaterno();

        return new DashboardClientResponseDto(
                fullName,
                "/calendar",             // ruta para ver citas
                "/payments/pending",     // ruta para ver pagos pendientes
                "/reports"               // ruta para ver reportes
        );
    }
}
