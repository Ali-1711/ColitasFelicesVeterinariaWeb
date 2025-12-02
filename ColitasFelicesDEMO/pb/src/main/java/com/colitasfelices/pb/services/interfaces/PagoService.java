package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.dto.request.StripePaymentRequestDto;
import com.stripe.model.PaymentIntent;
import com.stripe.exception.StripeException;

public interface PagoService {
    PaymentIntent processPayment(StripePaymentRequestDto request) throws StripeException;
}