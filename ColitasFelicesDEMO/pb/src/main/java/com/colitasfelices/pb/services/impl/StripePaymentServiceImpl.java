package com.colitasfelices.pb.services.impl;

import com.colitasfelices.pb.entity.Cita;
import com.colitasfelices.pb.entity.Pago;
import com.colitasfelices.pb.repository.PagoRepository;
import com.colitasfelices.pb.services.interfaces.StripePaymentService;
import com.stripe.model.PaymentIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StripePaymentServiceImpl implements StripePaymentService {

    private final PagoRepository pagoRepository;

    @Override
    public Pago guardarPago(PaymentIntent paymentIntent, Cita cita) {
        Pago pago = Pago.builder()
                .cita(cita)
                .monto(paymentIntent.getAmount())
                .moneda(paymentIntent.getCurrency())
                .descripcion(paymentIntent.getDescription())
                .estado(paymentIntent.getStatus())
                .stripePaymentIntentId(paymentIntent.getId())
                .fechaPago(LocalDateTime.now())
                .build();

        return pagoRepository.save(pago);
    }
}