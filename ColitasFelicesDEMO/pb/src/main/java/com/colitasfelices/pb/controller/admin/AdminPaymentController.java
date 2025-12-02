package com.colitasfelices.pb.controller.admin;

import com.colitasfelices.pb.dto.response.AdminPaymentResponseDto;
import com.colitasfelices.pb.services.interfaces.AdminPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
@RequiredArgsConstructor
public class AdminPaymentController {

    private final AdminPaymentService adminPaymentService;

    @GetMapping
    public List<AdminPaymentResponseDto> listarPagos() {
        return adminPaymentService.obtenerPagos();
    }
}