package com.example.paymentservice.PaymentGateway.StripePaymentGateway;

import com.example.paymentservice.PaymentGateway.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PaymentLinkUpdateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class StripePaymentGateway implements PaymentGateway {


    @Value("${stripe.key.secret}")
    private String apiKey;

    @Override
    public String createpaymentLint( Long amount) throws StripeException {

        Stripe.apiKey =apiKey;
        // Set your secret key. Remember to switch to your live secret key in production.
// See your keys here: https://dashboard.stripe.com/apikeys

        ProductCreateParams productCreateParams =
                ProductCreateParams.builder().setName("Generic").build();
        Product product = Product.create(productCreateParams);

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(params);




        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://example.com")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.getUrl();
    }
}
