package com.upgrad.orderprocessingservice.service;

import com.upgrad.orderprocessingservice.entity.Order;
import com.upgrad.orderprocessingservice.feign.PaymentClient;
import com.upgrad.orderprocessingservice.model.OrderResponseVO;
import com.upgrad.orderprocessingservice.model.OrderVO;
import com.upgrad.orderprocessingservice.repository.OrderProcessingRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {
    private OrderProcessingRepository orderProcessingRepository;
    private PaymentClient paymentClient;

    public OrderProcessingService(OrderProcessingRepository orderProcessingRepository, PaymentClient paymentClient) {
        this.orderProcessingRepository = orderProcessingRepository;
        this.paymentClient = paymentClient;
    }

    public OrderResponseVO createOrder(OrderVO orderVO){
        //call the payment service microservice 
        paymentClient.getPaymentStatus(orderVO.getOrderId());

        orderProcessingRepository.save(Order
                .builder()
                .orderID(orderVO.getOrderId())
                .orderAmount(orderVO.getOrderAmount())
                .build());
        return OrderResponseVO
                .builder()
                .orderId(orderVO.getOrderId())
                .build();
    }
}
