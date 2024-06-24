package com.example.paymentservice.Services;


import com.example.paymentservice.PaymentGateway.PaymentGateway;
import com.example.paymentservice.PaymentGateway.StripePaymentGateway.StripePaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private StripePaymentGateway stripePaymentGateway;
    public PaymentServices(StripePaymentGateway stripePaymentGateway)
    {
        this.stripePaymentGateway=stripePaymentGateway;
    }

    public String generatePaymentLintk( Long orderId) throws Exception {
        return stripePaymentGateway.createpaymentLint(10000L);
    }
}

