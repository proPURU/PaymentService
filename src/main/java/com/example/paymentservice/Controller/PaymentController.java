package com.example.paymentservice.Controller;


import com.example.paymentservice.DTOs.PaymentLinkRequestDTO;
import com.example.paymentservice.Services.PaymentServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    PaymentServices paymentServices;
    public PaymentController(PaymentServices paymentServices)
    {
        this.paymentServices=paymentServices;
    }



    @PostMapping("/paymentLink")
    public String generatePayment(@RequestBody PaymentLinkRequestDTO requestDTO) throws Exception {
        String link=paymentServices.generatePaymentLintk(requestDTO.getPrice());


        return link;

    }



}
