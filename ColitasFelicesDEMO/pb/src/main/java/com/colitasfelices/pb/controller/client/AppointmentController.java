// AppointmentController.java
package com.colitasfelices.pb.controller.client;

import com.colitasfelices.pb.dto.response.AppointmentListResponseDto;
import com.colitasfelices.pb.services.interfaces.AppointmentClientService;
import com.colitasfelices.pb.services.interfaces.AppointmentShowClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentShowClientService appointmentShowClientService;
    private final AppointmentClientService appointmentClientService;

    // ✅ 1. Listar citas del cliente
    @GetMapping
    public List<AppointmentListResponseDto> getClientAppointments(Authentication auth) {
        System.out.println("HOLAAAAAAA" + auth);
        return appointmentShowClientService.getAppointmentsByClient(auth);

    }


    // ✅ 2. Registrar nueva cita
    /*@PostMapping
    public String bookAppointment(Authentication auth, @RequestBody AppointmentRequestDto dto) {
        try {
            appointmentClientService.bookAppointment(auth, dto);
            return "✅ Cita registrada correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error al registrar la cita: " + e.getMessage();
        }
    }
    */

}
