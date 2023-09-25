package com.ssjwellery.ecommerce.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ssjwellery.ecommerce.dto.ProductDTO;
import com.ssjwellery.ecommerce.model.Category;
import com.ssjwellery.ecommerce.model.Product;
import com.ssjwellery.ecommerce.service.CategoryService;
import com.ssjwellery.ecommerce.service.ProductService;

@Controller
public class AdminController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
//	System.getProperty("user.dir") +
	
	@Value("${project.imagefile.path}")
	String uploadDir;
	
	
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	// Category Section
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id , Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		return "404";
	}
	
	
	// Product Section
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String postProductAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
								@RequestParam("productImage") MultipartFile imgFile,
								@RequestParam("imgName") String imgName) throws IOException {
		
		
		
		
		Category category = categoryService.getCategoryById(productDTO.getCategoryId()).get();
		
		String UPLOAD_DIR = new ClassPathResource("static/productImages/").getFile().getAbsolutePath();
		
		String imgUUID = "";
		if(!imgFile.isEmpty()) {
			// getting the original image name
			String imageName = imgFile.getOriginalFilename();
			// generating random unique id
			imgUUID = UUID.randomUUID().toString();
			// adding the image file extension to the UUID creating an unique name for each image 
			imgUUID = imgUUID.concat(imageName.substring(imageName.lastIndexOf(".")));
			
			
			// building the path by joining the upload directory and the file name 
			Path filePathAndName = Paths.get(UPLOAD_DIR , imgUUID);
			
			// incase image gets updated , then we need to delete the previous image
			try {
				System.out.println("<---------------------The image name is:  "+imgName+"  --------------------->");
				Path imgFilePath =Paths.get( uploadDir , imgName);
				Files.deleteIfExists( imgFilePath);				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			// copying the file into the directory
			Files.copy(imgFile.getInputStream(), filePathAndName);
			
			//			Files.write(filePathAndName , imgFile.getBytes());
		}
		else {
			imgUUID = imgName;
		}
		
		Product product = new Product(productDTO.getId(), productDTO.getName(), category , productDTO.getPrice() , productDTO.getWeight(), productDTO.getDescription(),imgUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id){
		Product product = productService.getProductById(id).get();
		Path imgFilePath =Paths.get( uploadDir, product.getImageName());
		
		try {
			Files.deleteIfExists( imgFilePath);
			productService.removeProductById(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id , Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO(product.getId(), product.getName() , product.getCategory().getId() ,product.getPrice() ,product.getWeight() ,product.getDescription(),product.getImageName());
		
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	
	
}
