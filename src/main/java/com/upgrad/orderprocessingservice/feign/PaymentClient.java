package com.upgrad.orderprocessingservice.feign;

import com.upgrad.orderprocessingservice.model.PaymentResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient library provided by Spring framework
 * It is a wrapper around REST template
 */
@FeignClient(name = "payment-service", url = "${payment-service-url}")
public interface PaymentClient {
    @GetMapping("/v1/payments/status")
    public PaymentResponseVO getPaymentStatus(@RequestParam String orderId);
}
