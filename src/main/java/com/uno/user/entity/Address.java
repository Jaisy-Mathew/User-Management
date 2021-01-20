package com.uno.user.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Integer addressId;
	@Column(name = "postcode")
	private String postcode;
	@Column(name = "suburb")
	private String suburb;
	@Column(name = "state")
	private String state;
	@Column(name = "full_address")
	private String fullAddress;

	@OneToOne(mappedBy = "address")
	private User user;
	
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

}
