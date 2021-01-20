package com.uno.user.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId;
    @Column(name = "title")
	private String title;
    @Column(name = "first_name")
	private String firstName;
    @Column(name = "last_name")
	private String lastName;
    @Column(name = "mobile_number")
	private String mobileNumber;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "address_id")
    private Address address;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}