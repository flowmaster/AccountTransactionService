/**
 * Entity possess the reference for the ACCOUNT table .  
 * ACCOUNT_ID would be the foreign key in the Transaction table with a One to many relation
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Account {
	
	@Id
	private String accountId;
	private BigDecimal balance;

	@OneToMany(mappedBy = "account" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Transaction> transaction;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID")
    @JsonBackReference
    private Customer customer;
	
	public Account(){}
	
public Account(String accountId,BigDecimal balance){
		this.accountId=accountId;
		this.balance = balance;
	}

	public Account(String accountId,BigDecimal balance,Customer customer, Transaction... transaction){
		
		this.accountId=accountId;
		this.balance = balance;
		this.customer=customer;
		this.transaction=Stream.of(transaction).collect(Collectors.toList());
		this.transaction.forEach( x -> x.setAccount(this));
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
