package com.ssjwellery.ecommerce.model;

import jakarta.persistence.CascadeType;
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
@Table(name="cart_item")
public class CartItem {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id" , referencedColumnName="id")
	private Product product;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="amount")
	private double amount;
	
	public CartItem() {
	}

	public CartItem( Product product, Long userId, int quantity, double amount) {
		this.product = product;
		this.userId = userId;
		this.quantity = quantity;
		this.amount = amount;
	}
	

	public CartItem(Long id, Product product, Long userId, int quantity,double amount) {
		this.id = id;
		this.product = product;
		this.userId = userId;
		this.quantity = quantity;
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", product=" + product + ", userId=" + userId + ", quantity=" + quantity
				+ ", amount=" + amount + "]";
	}

	
	
	
	
	
	
}
