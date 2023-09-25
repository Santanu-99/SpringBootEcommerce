package com.ssjwellery.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssjwellery.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
