package ru.gb.OpenFaign.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.OpenFaign.model.Payment;
import ru.gb.OpenFaign.proxy.PaymentProxy;

@RestController
@AllArgsConstructor
public class PaymentController {
    private final PaymentProxy proxy;

    @PostMapping("/payment")
    public Payment addPayment(@RequestBody Payment payment){
        String requestId = "1";

        return proxy.pay(requestId, payment);
    }
}
