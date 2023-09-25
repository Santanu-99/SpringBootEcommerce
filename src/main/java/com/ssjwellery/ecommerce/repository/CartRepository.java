package com.ssjwellery.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssjwellery.ecommerce.model.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findAllByUserId(Long id);
}
