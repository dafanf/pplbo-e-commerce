package com.pplbo.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "payment", url = "http://localhost:8080")
public interface PaymentClient {
    @RequestMapping(method = RequestMethod.GET, value = "/payments/exists")
    boolean isPaymentExist(@RequestParam Long id);
}
