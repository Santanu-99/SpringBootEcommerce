package com.ssjwellery.ecommerce.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssjwellery.ecommerce.dto.OrderDTO;
import com.ssjwellery.ecommerce.model.Orders;
import com.ssjwellery.ecommerce.repository.CartRepository;
import com.ssjwellery.ecommerce.repository.CategoryRepository;
import com.ssjwellery.ecommerce.repository.OrderItemRepository;
import com.ssjwellery.ecommerce.repository.OrdersRepository;
import com.ssjwellery.ecommerce.repository.ProductRepository;
import com.ssjwellery.ecommerce.repository.RoleRepository;
import com.ssjwellery.ecommerce.repository.UserRepository;
import com.ssjwellery.ecommerce.service.ProductService;

import jakarta.validation.Valid;

import com.ssjwellery.ecommerce.model.CartItem;
import com.ssjwellery.ecommerce.model.OrderItem;
import com.ssjwellery.ecommerce.model.OrderStatus;
import com.ssjwellery.ecommerce.model.User;

@Controller
public class CheckoutController {
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@PostMapping("/checkout")
	public String saveOrder(@Valid @ModelAttribute OrderDTO orderDTO,BindingResult result , Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUserEmail = authentication.getName();
		
		
		User user = userRepository.findUserByEmail(currentUserEmail).get();
		
		double totalAmount = 0;
		for(CartItem cartItem : user.getCartItems()) {
			totalAmount +=  cartItem.getProduct().getPrice()* cartItem.getQuantity();			
		}
		
//		System.out.println(orderDTO);
//		System.out.println(orderDTO.getEmail().trim().equals("")+"=====================================>");
		
		if(result.hasErrors()) {
		    
			model.addAttribute("orderDTO" , orderDTO);
			model.addAttribute("total", totalAmount);
			
			return "checkout";
		}
		else {
			
			Orders order = new Orders();
			
			order.setId(0l);
			order.setFirstName(orderDTO.getFirstName());
			order.setLastName(orderDTO.getLastName());
			order.setAdditionalInformation(orderDTO.getAdditionalInformation());
			if(orderDTO.getEmail().trim().equals("")) {
				order.setEmail(user.getEmail());
			}
			else {
				order.setEmail(orderDTO.getEmail());				
			}
			order.setPhone(orderDTO.getPhone());
			
			String address = orderDTO.getAddressLineOne() +" , " + orderDTO.getAddressLineTwo() +" , City: "+ orderDTO.getCity() +" , PinCode: "+orderDTO.getPinCode() ;
			
			order.setUser(user);
			order.setAddress(address);
			order.setTotalAmount(totalAmount);
			
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String dateTime = dtf.format(now);
			order.setDateTime(dateTime);
			
			OrderStatus initial = new OrderStatus(1,"Pending");
			order.setStatus(initial);
			
			Orders placedOrder = ordersRepository.save(order);
			
			List<OrderItem> orderItems = convertCartItemToOrderItem(user.getCartItems() , placedOrder);
			
			orderItemRepository.saveAll(orderItems);
			
			
			
			return "redirect:/orders";
		}
		
	}
	
	private List<OrderItem> convertCartItemToOrderItem(List<CartItem> cartItems , Orders order) {
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(0l);
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setAmount(cartItem.getAmount());
			orderItems.add(orderItem);
			
		}
		return orderItems;
	}
}
