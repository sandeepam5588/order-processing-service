package com.upgrad.orderprocessingservice.controller.advice;

import com.upgrad.orderprocessingservice.exception.OrderProcessingFailedException;
import com.upgrad.orderprocessingservice.model.ErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.upgrad.orderprocessingservice.constants.OrderProcessingConstants.PAYMENT_FAILED_ERROR_CODE;

@ControllerAdvice
public class OrderProcessingControllerAdvice {
    @ExceptionHandler(OrderProcessingFailedException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public @ResponseBody
    ErrorVO handleOrderProcessingFailedException(){
        return ErrorVO
                .builder()
                .errorCode(PAYMENT_FAILED_ERROR_CODE)
                .build();
    }
}
