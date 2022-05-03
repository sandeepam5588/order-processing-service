package com.upgrad.orderprocessingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseVO {
    private String orderId;
    private String paymentStatus;
    private String paymentReferenceNumber;
}
