// AppointmentController.java
package com.colitasfelices.pb.controller.client;

import com.colitasfelices.pb.dto.response.AppointmentCalendarResponseDto;
import com.colitasfelices.pb.services.interfaces.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/appointments")
@RequiredArgsConstructor
public class CalendarController {

    private final AppointmentService appointmentService;

    @GetMapping("/calendar")
    public List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication) {
        return appointmentService.getCalendarAppointments(authentication);
    }
}
