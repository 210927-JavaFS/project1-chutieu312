package com.revature.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
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
	@JoinColumn(name="author")	
    private User author;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="resolver")	
    private User resolver;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="reimbStatus")	
    private ReimbStatus reimbStatus;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="reimbType")	
    private ReimbType reimbType;


	public Reimb(int reimbId, double amount, Date submittedlDate, Date resolvedlDate, String description,
			String receipt, User author, User resolver, ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedlDate = submittedlDate;
		this.resolvedlDate = resolvedlDate;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	
	
	public Reimb(double amount, Date submittedlDate, Date resolvedlDate, String description, String receipt,
			User author, User resolver, ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.amount = amount;
		this.submittedlDate = submittedlDate;
		this.resolvedlDate = resolvedlDate;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
	}

	

	public Reimb(double amount, String description, String receipt, User author, ReimbType reimbType) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.reimbType = reimbType;
	}

	
	// This use for create DB in bootstrap
	public Reimb(int reimbId, double amount, Date submittedlDate, String description, String receipt, User author,
			ReimbStatus reimbStatus, ReimbType reimbType) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submittedlDate = submittedlDate;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
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



	public User getAuthor() {
		return author;
	}



	public void setAuthor(User author) {
		this.author = author;
	}



	public User getResolver() {
		return resolver;
	}



	public void setResolver(User resolver) {
		this.resolver = resolver;
	}



	public ReimbStatus getReimbStatus() {
		return reimbStatus;
	}



	public void setReimbStatus(ReimbStatus reimbStatus) {
		this.reimbStatus = reimbStatus;
	}



	public ReimbType getReimbType() {
		return reimbType;
	}



	public void setReimbType(ReimbType reimbType) {
		this.reimbType = reimbType;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((resolvedlDate == null) ? 0 : resolvedlDate.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((submittedlDate == null) ? 0 : submittedlDate.hashCode());
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
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
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
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (resolvedlDate == null) {
			if (other.resolvedlDate != null)
				return false;
		} else if (!resolvedlDate.equals(other.resolvedlDate))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (submittedlDate == null) {
			if (other.submittedlDate != null)
				return false;
		} else if (!submittedlDate.equals(other.submittedlDate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Reimb [reimbId=" + reimbId + ", amount=" + amount + ", submittedlDate=" + submittedlDate
				+ ", resolvedlDate=" + resolvedlDate + ", description=" + description + ", receipt=" + receipt
				+ ", author=" + author + ", resolver=" + resolver + ", reimbStatus=" + reimbStatus + ", reimbType="
				+ reimbType + "]";
	}



	
    
}
