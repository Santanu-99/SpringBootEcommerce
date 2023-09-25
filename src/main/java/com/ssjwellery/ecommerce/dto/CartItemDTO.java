package com.ssjwellery.ecommerce.dto;

public class CartItemDTO {
	
	private Long id;
	private Long productId;	
	private Long userId;
	private int quantity;
	
	
	public CartItemDTO() {
	}
	
	public CartItemDTO(Long id, Long productId, Long userId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	@Override
	public String toString() {
		return "CartItemDTO [id=" + id + ", productId=" + productId + ", userId=" + userId + ", quantity=" + quantity
				+ "]";
	}
	
	
}
