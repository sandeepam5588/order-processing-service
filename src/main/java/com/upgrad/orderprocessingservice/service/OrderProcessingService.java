package com.upgrad.orderprocessingservice.service;

import com.upgrad.orderprocessingservice.entity.Order;
import com.upgrad.orderprocessingservice.model.OrderResponseVO;
import com.upgrad.orderprocessingservice.model.OrderVO;
import com.upgrad.orderprocessingservice.repository.OrderProcessingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService {
    private OrderProcessingRepository orderProcessingRepository;

    public OrderProcessingService(OrderProcessingRepository orderProcessingRepository) {
        this.orderProcessingRepository = orderProcessingRepository;
    }

    public OrderResponseVO createOrder(OrderVO orderVO){
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
