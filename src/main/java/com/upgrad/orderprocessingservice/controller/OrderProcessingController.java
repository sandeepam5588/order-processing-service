package com.upgrad.orderprocessingservice.controller;

import com.upgrad.orderprocessingservice.exception.OrderProcessingFailedException;
import com.upgrad.orderprocessingservice.model.OrderResponseVO;
import com.upgrad.orderprocessingservice.model.OrderVO;
import com.upgrad.orderprocessingservice.service.OrderProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProcessingController {
    private OrderProcessingService orderProcessingService;

    public OrderProcessingController(OrderProcessingService orderProcessingService) {
        this.orderProcessingService = orderProcessingService;
    }

    @PostMapping("/v1/order")
    public ResponseEntity<OrderResponseVO> createOrder(@RequestBody OrderVO orderVO) throws OrderProcessingFailedException {
        return ResponseEntity.ok(orderProcessingService.createOrder(orderVO));
    }
}
