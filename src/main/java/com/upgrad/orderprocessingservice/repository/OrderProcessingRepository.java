package com.upgrad.orderprocessingservice.repository;

import com.upgrad.orderprocessingservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderProcessingRepository extends MongoRepository<Order, String> {
}
