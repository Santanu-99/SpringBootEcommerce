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
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id" , referencedColumnName="id")
	private Product product;
	
	@ManyToOne
    @JoinColumn(name="order_id", referencedColumnName="id")
	private Orders orders;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="amount")
	private double amount;
	
	public OrderItem() {
	}

	public OrderItem( Product product, Orders orders, int quantity, double amount) {
		
		this.product = product;
		this.orders = orders;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	
	public OrderItem(Long id, Product product, Orders orders, int quantity, double amount) {
		super();
		this.id = id;
		this.product = product;
		this.orders = orders;
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

	public Orders getOrder() {
		return orders;
	}

	public void setOrder(Orders orders) {
		this.orders = orders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", product=" + product + ", orders=" + orders + ", quantity=" + quantity
				+ ", amount=" + amount + "]";
	}

	
	

}
