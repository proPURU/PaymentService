package com.example.paymentservice.PaymentGateway;

import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;


@Service
public interface PaymentGateway {

    public String createpaymentLint(Long amount) throws StripeException;
}
