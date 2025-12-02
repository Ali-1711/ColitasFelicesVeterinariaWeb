package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.dto.response.AdminPaymentResponseDto;
import com.colitasfelices.pb.repository.PagoRepository;
import com.colitasfelices.pb.services.interfaces.AdminPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPaymentServiceImpl implements AdminPaymentService {

    private final PagoRepository pagoRepository;

    @Override
    public List<AdminPaymentResponseDto> obtenerPagos() {
        return pagoRepository.findAllPaymentsForAdmin();
    }
}