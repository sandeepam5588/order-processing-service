package com.upgrad.orderprocessingservice.service;

import com.upgrad.orderprocessingservice.entity.Order;
import com.upgrad.orderprocessingservice.exception.OrderProcessingFailedException;
import com.upgrad.orderprocessingservice.feign.PaymentClient;
import com.upgrad.orderprocessingservice.model.OrderResponseVO;
import com.upgrad.orderprocessingservice.model.OrderVO;
import com.upgrad.orderprocessingservice.model.PaymentResponseVO;
import com.upgrad.orderprocessingservice.repository.OrderProcessingRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import static com.upgrad.orderprocessingservice.constants.OrderProcessingConstants.*;

@Service
public class OrderProcessingService {
    private OrderProcessingRepository orderProcessingRepository;
    private PaymentClient paymentClient;

    public OrderProcessingService(OrderProcessingRepository orderProcessingRepository, PaymentClient paymentClient) {
        this.orderProcessingRepository = orderProcessingRepository;
        this.paymentClient = paymentClient;
    }

    public OrderResponseVO createOrder(OrderVO orderVO) throws OrderProcessingFailedException {
        PaymentResponseVO paymentResponseVO = null;
        try {
             paymentResponseVO = paymentClient.getPaymentStatus(orderVO.getOrderId());
        } catch (FeignException e){
            throw new OrderProcessingFailedException();
        }
        OrderResponseVO orderResponseVO = OrderResponseVO
                .builder()
                .orderId(orderVO.getOrderId())
                .build();

        if (PAYMENT_SUCCESS.equals(paymentResponseVO.getPaymentStatus())){
            orderProcessingRepository.save(Order
                    .builder().orderID(orderVO.getOrderId())
                    .orderAmount(orderVO.getOrderAmount())
                    .orderStatus(ORDER_CREATED)
                    .paymentReferenceNumber(paymentResponseVO.getPaymentReferenceNumber())
                    .build());
            orderResponseVO.setOrderStatus(ORDER_CREATED);
        } else {
            orderProcessingRepository.save(Order
                    .builder().orderID(orderVO.getOrderId())
                    .orderAmount(orderVO.getOrderAmount())
                    .orderStatus(ORDER_PROCESSING_FAILED)
                    .build());
            orderResponseVO.setOrderStatus(ORDER_PROCESSING_FAILED);
        }
        return orderResponseVO;
    }
}
