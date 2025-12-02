package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.dashboard.DashboardClientResponseDto;
import org.springframework.security.core.Authentication;

public interface DashboardService {
    DashboardClientResponseDto getClientDashboard(Authentication auth);
}
