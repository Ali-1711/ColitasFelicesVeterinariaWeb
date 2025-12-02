package com.colitasfelices.pb.controller.client;

import com.colitasfelices.pb.dto.response.AppointmentPaidResponseDto;
import com.colitasfelices.pb.dto.response.AppointmentScheduledResponseDto;
import com.colitasfelices.pb.services.interfaces.CitaClienteService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/client/appointments")
@RequiredArgsConstructor
public class CitaClienteController {

    private final CitaClienteService citaClienteService;

    @GetMapping("/scheduled")
    public List<AppointmentScheduledResponseDto> getScheduledAppointments(Principal principal) {
        return citaClienteService.obtenerCitasProgramadasPorUsuario(principal.getName());
    }

    @GetMapping("/pagadas")
    public List<AppointmentPaidResponseDto> getPaidAppointments(Principal principal) {
        return citaClienteService.obtenerCitasPagadasPorUsuario(principal.getName());
    }
}
