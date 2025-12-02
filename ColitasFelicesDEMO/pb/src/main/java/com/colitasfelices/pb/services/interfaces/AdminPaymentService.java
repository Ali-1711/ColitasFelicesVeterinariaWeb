package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.response.AdminPaymentResponseDto;

import java.util.List;

public interface AdminPaymentService {
    List<AdminPaymentResponseDto> obtenerPagos();
}