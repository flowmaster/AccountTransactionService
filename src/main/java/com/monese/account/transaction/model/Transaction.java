/**
 * Entity possess the reference for the TRANSACTION table .  
 * ACCOUNT_ID of customer table would be the foreign key in the Transaction table with a many to one relation.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	private Date transTime;
	private String transType;
	
	@ManyToOne
	@JoinColumn(name = "accountId",referencedColumnName = "accountId")
	@JsonBackReference
	private Account account;
	
	public Transaction(){}
	
	public Transaction(BigDecimal amount,Date transTime,String transType){
		this.amount=amount;
		this.transTime=transTime;
		this.transType=transType;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
