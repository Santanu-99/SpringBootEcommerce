package com.ssjwellery.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssjwellery.ecommerce.model.Product;
import com.ssjwellery.ecommerce.model.User;
import com.ssjwellery.ecommerce.service.ProductService;
import com.ssjwellery.ecommerce.dto.OrderDTO;
import com.ssjwellery.ecommerce.model.CartItem;
import com.ssjwellery.ecommerce.repository.CartRepository;
import com.ssjwellery.ecommerce.repository.CategoryRepository;
import com.ssjwellery.ecommerce.repository.OrderItemRepository;
import com.ssjwellery.ecommerce.repository.OrdersRepository;
import com.ssjwellery.ecommerce.repository.ProductRepository;
import com.ssjwellery.ecommerce.repository.RoleRepository;
import com.ssjwellery.ecommerce.repository.UserRepository;

@Controller
public class CartController {
	
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
	
	@GetMapping("/addToCart/{productId}")
	public String addToCart(@PathVariable long productId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserEmail = authentication.getName();
		    User user = userRepository.findUserByEmail(currentUserEmail).get();
		    List<CartItem> cartItems = user.getCartItems();
		    
		    boolean isPresentInCart = false;
			
			for(CartItem cartItem : cartItems) {
				if(cartItem.getProduct().getId() == productId) {
					isPresentInCart = true;
					cartItem.setQuantity(cartItem.getQuantity() + 1);
					cartItem.setAmount(cartItem.getQuantity() * cartItem.getProduct().getPrice());
					cartRepository.save(cartItem);
					break;
				}
			}
			
			if(!isPresentInCart) {
				CartItem cartItem = new CartItem();
				
				cartItem.setId(0l);
				cartItem.setUserId(user.getId());
				Product product = productRepository.findById(productId).get();
				cartItem.setProduct(product);
				cartItem.setQuantity(1);
				cartItem.setAmount(cartItem.getQuantity() * cartItem.getProduct().getPrice());
				cartRepository.save(cartItem);			
			}
		    
		    return "redirect:/shop";
		    
		}
		else {
			return "redirect:/login";			
		}
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserEmail = authentication.getName();
	    
	    
	    User user = userRepository.findUserByEmail(currentUserEmail).get();
	    
	    double totalAmount = 0l;
		int cartCount = 0;
	    for(CartItem cartItem : user.getCartItems()) {
	    	totalAmount +=  cartItem.getProduct().getPrice()* cartItem.getQuantity();
			cartCount += cartItem.getQuantity();				
		}
	    model.addAttribute("cartCount",cartCount);
		model.addAttribute("total", totalAmount);
		model.addAttribute("cart", user.getCartItems());
		
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{cartItemId}")
	public String cartItemRemove(@PathVariable long cartItemId) {
		cartRepository.deleteById(cartItemId);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserEmail = authentication.getName();
	    
	    User user = userRepository.findUserByEmail(currentUserEmail).get();
	    
	    OrderDTO orderDTO = new OrderDTO();
	    
	    orderDTO.setFirstName(user.getFirstName());
	    orderDTO.setLastName(user.getLastName());
	    orderDTO.setEmail(user.getEmail());
	    
	    double totalAmount = 0;
	    for(CartItem cartItem : user.getCartItems()) {
	    	totalAmount +=  cartItem.getProduct().getPrice()* cartItem.getQuantity();			
		}
		
	    model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("total", totalAmount);
		return "checkout";
	}
}
