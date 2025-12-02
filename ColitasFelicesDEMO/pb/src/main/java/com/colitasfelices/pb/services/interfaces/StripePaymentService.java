package com.colitasfelices.pb.services.interfaces;

import com.colitasfelices.pb.entity.Pago;
import com.colitasfelices.pb.entity.Cita;
import com.stripe.model.PaymentIntent;

public interface StripePaymentService {
    Pago guardarPago(PaymentIntent paymentIntent, Cita cita);
}