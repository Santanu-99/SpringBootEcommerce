package com.ssjwellery.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrderDTO {
	
	@NotBlank(message="First Name cannot be blank")
	@Size(min = 2, max = 32, message = "First Name must be between 2 and 32 characters long")
	private String firstName;
	
	@Size(min = 0, max = 32, message = "Last Name can be 32 characters long at max")
	private String lastName;
	
	@NotBlank(message="Address Line One cannot be blank")
	@Size(min = 5, message = "Address Line One must have a length of atleast 5")
	private String addressLineOne;
	private String addressLineTwo;
	
	@NotBlank(message="Pin Code cannot be blank")
	@Size(min = 6 , max=6, message = "Pincode should be of length 6")
	private String pinCode;
	
	@NotBlank(message="City cannot be blank")
	@Size(min = 2 , message = "City name should be atleast of length 2")
	private String city;
	
	@NotBlank(message="Phone number cannot be blank")
	@Size(min = 10, max = 10 , message = "Phone number should be of length 10")
	private String phone;
	
	private String email;
	private String additionalInformation;
	
	public OrderDTO() {
	}
	
	
	public OrderDTO(String firstName, String lastName, String addressLineOne,String addressLineTwo,String pinCode,String city,  String phone, String email,
			String additionalInformation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.pinCode = pinCode;
		this.city = city;
		this.additionalInformation = additionalInformation;
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

	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	public String getAddressLineTwo() {
		return addressLineTwo;
	}


	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}


	public String getAddressLineOne() {
		return addressLineOne;
	}


	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}


	@Override
	public String toString() {
		return "OrdersDTO [firstName=" + firstName + ", lastName=" + lastName + ", addressLineOne=" + addressLineOne
				+ ", addressLineTwo=" + addressLineTwo + ", pinCode=" + pinCode + ", city=" + city + ", phone=" + phone
				+ ", email=" + email + ", additionalInformation=" + additionalInformation + "]";
	}
	
	
	
	
	
	
}
