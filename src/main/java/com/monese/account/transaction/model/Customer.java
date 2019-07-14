/**
 * Entity possess the reference for the CUSTOMER table .  
 * CUSTOMER_ID would be the foreign key in the ACCOUNT table with a One to many relation
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    
    public Customer(Long id,String name,String address,String phone,String email) {
    	this.id=id;
    	this.name=name;
    	this.address= address;
    	this.phone=phone;
    	this.email=email;
    }
    public Customer(){}
    @OneToMany(cascade=CascadeType.ALL,mappedBy="customer",fetch=FetchType.LAZY)
    @JsonManagedReference
    private List<Account> accounts;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
}
