package com.ssjwellery.ecommerce.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="date_time")
	private String dateTime;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="additional_information")
	private String additionalInformation;
	
	
	@ManyToOne
    @JoinColumn(name="status_id", referencedColumnName="id")
	private OrderStatus status;
	
	@ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="order_id")
	private List<OrderItem> orderItems;
	


	

	public Orders() {
	}
	
	public Orders(String dateTime, String firstName, String lastName, String address, double totalAmount,
			String phone, String email, String additionalInformation, OrderStatus status, User user,
			List<OrderItem> orderItems) {
		this.dateTime = dateTime;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.totalAmount = totalAmount;
		this.phone = phone;
		this.email = email;
		this.additionalInformation = additionalInformation;
		this.status = status;
		this.user = user;
		this.orderItems = orderItems;
	}
	
	public Orders(Long id, String dateTime, String firstName, String lastName, String address, double totalAmount,
			String phone, String email, String additionalInformation, OrderStatus status, User user,
			List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.totalAmount = totalAmount;
		this.phone = phone;
		this.email = email;
		this.additionalInformation = additionalInformation;
		this.status = status;
		this.user = user;
		this.orderItems = orderItems;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDateTime() {
		return dateTime;
	}


	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public OrderStatus getStatus() {
		return status;
	}


	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", dateTime=" + dateTime + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", totalAmount=" + totalAmount + ", phone=" + phone + ", email=" + email
				+ ", additionalInformation=" + additionalInformation + ", status=" + status + ", user=" + user
				+ ", orderItems=" + orderItems + "]";
	}

}
