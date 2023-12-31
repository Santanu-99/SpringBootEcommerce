package com.ssjwellery.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssjwellery.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByEmail(String email);
}
