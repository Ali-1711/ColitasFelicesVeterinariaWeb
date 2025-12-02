package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.AppointmentRequestDto;
import org.springframework.security.core.Authentication;

public interface AppointmentClientService {
    void bookAppointment(Authentication auth, AppointmentRequestDto dto);
}
