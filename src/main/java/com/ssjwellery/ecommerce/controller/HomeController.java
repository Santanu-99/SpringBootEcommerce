package com.ssjwellery.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssjwellery.ecommerce.model.CartItem;
import com.ssjwellery.ecommerce.model.User;
import com.ssjwellery.ecommerce.repository.UserRepository;
import com.ssjwellery.ecommerce.service.CategoryService;
import com.ssjwellery.ecommerce.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping({"/" , "home"})
	public String getHome(Model model) {
		return "redirect:/shop";
	}
	
	@GetMapping("/orders")
	public String getOrders(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserEmail = authentication.getName();
	    
	    int cartCount = 0;
	    User user = userRepository.findUserByEmail(currentUserEmail).get();
	    for(CartItem cartItem : user.getCartItems()) {
			cartCount += cartItem.getQuantity();				
		}
	    
	    model.addAttribute("cartCount",cartCount);
	    model.addAttribute("user", user);
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "orders";
	}
	
	@GetMapping("/shop")
	public String getShop(Model model) {
		
		int cartCount = 0;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserEmail = authentication.getName();
		    User user = userRepository.findUserByEmail(currentUserEmail).get();    
		    for(CartItem cartItem : user.getCartItems()) {
				cartCount += cartItem.getQuantity();				
			}
		}
		
		model.addAttribute("cartCount",cartCount);
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String getShopByCategory(@PathVariable int id ,  Model model) {
		
		int cartCount = 0;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserEmail = authentication.getName();
		    User user = userRepository.findUserByEmail(currentUserEmail).get();    
		    for(CartItem cartItem : user.getCartItems()) {
				cartCount += cartItem.getQuantity();				
			}
		}
		
		model.addAttribute("cartCount",cartCount);
		
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		model.addAttribute("categories", categoryService.getAllCategory());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id ,  Model model) {
		int cartCount = 0;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserEmail = authentication.getName();
		    User user = userRepository.findUserByEmail(currentUserEmail).get();    
		    for(CartItem cartItem : user.getCartItems()) {
				cartCount += cartItem.getQuantity();				
			}
		}
		
		model.addAttribute("cartCount",cartCount);
		model.addAttribute("product",productService.getProductById(id).get());
		return "viewProduct";
	}
}
