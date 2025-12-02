package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AppointmentCalendarResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentService {
    List<AppointmentCalendarResponseDto> getCalendarAppointments(Authentication authentication);
}
