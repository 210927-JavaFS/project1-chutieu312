package com.revature.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Reimb {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reimbId;
	private double amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedlDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date resolvedlDate;
    private String description;
    private String receipt;
    
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="authorId")	
    private int authorId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="resolverId")	
    private int resolverId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="statusId")	
    private int statusId;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="typeId")	
    private int typeId;

	public Reimb(int reimbId, double amount, Date submittedlDate, Date resolvedlDate, String description,
			String receipt, int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedlDate = submittedlDate;
		this.resolvedlDate = resolvedlDate;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimb(double amount, String description, String receipt,
			int authorId, int resolverId, int statusId, int typeId) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimb() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSubmittedlDate() {
		return submittedlDate;
	}

	public void setSubmittedlDate(Date submittedlDate) {
		this.submittedlDate = submittedlDate;
	}

	public Date getResolvedlDate() {
		return resolvedlDate;
	}

	public void setResolvedlDate(Date resolvedlDate) {
		this.resolvedlDate = resolvedlDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((resolvedlDate == null) ? 0 : resolvedlDate.hashCode());
		result = prime * result + resolverId;
		result = prime * result + statusId;
		result = prime * result + ((submittedlDate == null) ? 0 : submittedlDate.hashCode());
		result = prime * result + typeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimb other = (Reimb) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolvedlDate == null) {
			if (other.resolvedlDate != null)
				return false;
		} else if (!resolvedlDate.equals(other.resolvedlDate))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submittedlDate == null) {
			if (other.submittedlDate != null)
				return false;
		} else if (!submittedlDate.equals(other.submittedlDate))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", submittedlDate=" + submittedlDate
				+ ", resolvedlDate=" + resolvedlDate + ", description=" + description + ", receipt=" + receipt
				+ ", authorId=" + authorId + ", resolverId=" + resolverId + ", statusId=" + statusId + ", typeId="
				+ typeId + "]";
	}

    
}
