package com.monese.account.transaction.model;

/**
 * Entity possess the reference for the TRANSFER_DETAIL table which populate all transaction records with
 * status of each transaction.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TransferDetail {
	
	@Id
	@GeneratedValue
	private Long id;
	private String srcAccountId;
	private String dstAccountId;
	private BigDecimal amount;
	@JsonIgnore
	private Date transTime;
	@JsonIgnore
	private String transSuccess;
	public TransferDetail(){}
		
	public TransferDetail(String srcAccountId,String dstAccountId,BigDecimal amount)
	{
		this.srcAccountId=srcAccountId;
		this.dstAccountId=dstAccountId;
		this.amount=amount;
	}
	

	public String getSrcAccountId() {
		return srcAccountId;
	}
	public void setSrcAccountId(String srcAccountId) {
		this.srcAccountId = srcAccountId;
	}
	public String getDstAccountId() {
		return dstAccountId;
	}
	public void setDstAccountId(String dstAccountId) {
		this.dstAccountId = dstAccountId;
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
	public String getTransSuccess() {
		return transSuccess;
	}
	public void setTransSuccess(String transSuccess) {
		this.transSuccess = transSuccess;
	}
}
