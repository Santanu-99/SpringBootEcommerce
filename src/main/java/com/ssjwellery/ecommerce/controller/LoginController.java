package com.ssjwellery.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssjwellery.ecommerce.global.GlobalData;
import com.ssjwellery.ecommerce.model.Role;
import com.ssjwellery.ecommerce.model.User;
import com.ssjwellery.ecommerce.repository.RoleRepository;
import com.ssjwellery.ecommerce.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository; 
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register" )
	public String registerGet(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid @ModelAttribute("user") User user , BindingResult result,   Model model , HttpServletRequest  request) throws ServletException{
//		System.out.println("======================"+result.hasErrors()+"========================");
		if(result.hasErrors()) {
			System.out.println(result);
			model.addAttribute("user", user);
			return "register";
		}
		else {
			String password = user.getPassword();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			
			userRepository.save(user);
			
			request.login(user.getEmail(), password);
			return "redirect:/";
			
		}
		
		
	}
	
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public String duplicateEmailError() {
		
		return "register";
	}

}
