package com.ssjwellery.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssjwellery.ecommerce.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
