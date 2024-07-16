package ru.gb.OpenFaign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.gb.OpenFaign.model.Payment;

@FeignClient(name = "payments", url = "http://localhost:8080")
public interface PaymentProxy {
    @PostMapping("/payment")
    Payment pay(@RequestHeader String requestId, @RequestBody Payment payment);
}
