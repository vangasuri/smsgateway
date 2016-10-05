package com.mv.common;

import java.io.Serializable;
import java.util.Date;

public class CustomerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int Id;
	public String custName;
	public String custPhNo;
	public String custBillAmnt;
	public String discount;
	public String smsTriggered;
	public Date promotionExpiryDate;
	public String createdBy;
	public Date createdDate;
	public String modifiedBy;
	public Date modifiedDate;
	
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhNo() {
		return custPhNo;
	}
	public void setCustPhNo(String custPhNo) {
		this.custPhNo = custPhNo;
	}
	public String getCustBillAmnt() {
		return custBillAmnt;
	}
	public void setCustBillAmnt(String custBillAmnt) {
		this.custBillAmnt = custBillAmnt;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Date getPromotionExpiryDate() {
		return promotionExpiryDate;
	}
	public void setPromotionExpiryDate(Date promotionExpiryDate) {
		this.promotionExpiryDate = promotionExpiryDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getSmsTriggered() {
		return smsTriggered;
	}
	public void setSmsTriggered(String smsTriggered) {
		this.smsTriggered = smsTriggered;
	}
	
}
