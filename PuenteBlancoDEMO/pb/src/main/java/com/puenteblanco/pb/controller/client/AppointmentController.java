// AppointmentController.java
package com.puenteblanco.pb.controller.client;

import com.puenteblanco.pb.dto.request.AppointmentRequestDto;
import com.puenteblanco.pb.dto.response.AppointmentListResponseDto;
import com.puenteblanco.pb.services.interfaces.AppointmentClientService;
import com.puenteblanco.pb.services.interfaces.AppointmentShowClientService;
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
