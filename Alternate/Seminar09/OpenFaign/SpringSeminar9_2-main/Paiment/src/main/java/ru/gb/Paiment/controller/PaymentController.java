package ru.gb.Paiment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Paiment.model.Payment;

import java.util.UUID;

@RestController
public class PaymentController {
    @PostMapping("/payment")
    public ResponseEntity<Payment> pay(@RequestHeader String requestId, @RequestBody Payment payment){
        payment.setId(UUID.randomUUID().toString());

        return ResponseEntity.status(HttpStatus.OK).header("requestId", requestId).body(payment);
    }
}
