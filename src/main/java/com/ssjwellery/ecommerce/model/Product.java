package com.ssjwellery.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id" , referencedColumnName="category_id")
	private Category category;
	
	@Column(name="price")
	private double price;
	
	@Column(name="weight")
	private double weight;
	
	@Column(name="description")
	private String description;
	
	@Column(name="image_name")
	private String imageName;
	
	public Product() {
		
	}
	
	public Product(String name, Category category, double price, double weight, String description, String imageName) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}

	public Product(Long id, String name, Category category, double price, double weight, String description,
			String imageName) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", weight="
				+ weight + ", description=" + description + ", imageName=" + imageName + "]";
	}
	
}
