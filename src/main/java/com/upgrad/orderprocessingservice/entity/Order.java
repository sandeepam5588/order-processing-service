package com.upgrad.orderprocessingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {
    private String orderID;
    private String orderStatus;
    private double orderAmount;
    private String paymentReferenceNumber;
}
