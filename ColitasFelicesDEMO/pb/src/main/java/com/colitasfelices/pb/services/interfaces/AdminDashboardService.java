package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AdminAlertResponseDto;
import com.colitasfelices.pb.dto.response.AdminMonthlyRevenueResponseDto;

import java.util.List;

public interface AdminDashboardService {

    int countTodayAppointments();

    double getWeeklyRevenue();

    int countWeeklyCancellations();

    List<AdminMonthlyRevenueResponseDto> getMonthlyRevenue();

    List<AdminAlertResponseDto> getRecentAlerts();
}
