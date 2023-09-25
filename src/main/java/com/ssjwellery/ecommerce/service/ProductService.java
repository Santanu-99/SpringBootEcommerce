package com.ssjwellery.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssjwellery.ecommerce.dto.ProductDTO;
import com.ssjwellery.ecommerce.model.Category;
import com.ssjwellery.ecommerce.model.Product;
import com.ssjwellery.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryService categoryService;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void addProduct(ProductDTO productDTO) {
		Optional<Category> category = categoryService.getCategoryById(productDTO.getCategoryId());
		if(category.isPresent()) {
			Product product = new Product(productDTO.getName(), category.get(), productDTO.getPrice() , productDTO.getWeight(), productDTO.getDescription(),productDTO.getImageName());
			productRepository.save(product);
		}
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void removeProductById(long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProductById(long id){
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductsByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}

}
