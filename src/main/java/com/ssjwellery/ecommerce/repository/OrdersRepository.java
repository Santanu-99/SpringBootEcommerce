package com.ssjwellery.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssjwellery.ecommerce.model.Orders;



public interface OrdersRepository extends JpaRepository<Orders, Long> {
	List<Orders> findAllByUser_Id(Long id);
}
