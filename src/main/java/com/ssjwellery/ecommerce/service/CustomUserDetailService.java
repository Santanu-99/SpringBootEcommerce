package com.ssjwellery.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssjwellery.ecommerce.model.CustomUserDetail;
import com.ssjwellery.ecommerce.model.User;
import com.ssjwellery.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findUserByEmail(email);
		if(user.isEmpty()) {
		    throw new UsernameNotFoundException("User With the Given Email not Found");
		}
		else {
			CustomUserDetail customUserDetail = new CustomUserDetail(user.get());
			return customUserDetail;
		}
	}

}
