package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AppointmentListResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentShowClientService {
    List<AppointmentListResponseDto> getAppointmentsByClient(Authentication auth);
}
