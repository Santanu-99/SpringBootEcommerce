package com.ssjwellery.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="order_status")
public class OrderStatus {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="status")
	private String status;

	public OrderStatus() {
	}
	
	public OrderStatus(String status) {
		this.status = status;
	}
	
	public OrderStatus(long id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderStatus [id=" + id + ", status=" + status + "]";
	}
	
	
	
}
