package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AppointmentCancelOptionDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentCancelClientService {

    List<AppointmentCancelOptionDto> getCancelableAppointments(Authentication auth);

    void cancelAppointment(Long id, String motivoCancelacion, Authentication authentication);

}
