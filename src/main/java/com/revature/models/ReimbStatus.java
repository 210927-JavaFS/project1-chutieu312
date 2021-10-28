package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReimbStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusId;
	
	private String status;

	public ReimbStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public ReimbStatus(String status) {
		super();
		this.status = status;
	}

	public ReimbStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + statusId;
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
		ReimbStatus other = (ReimbStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	
	

}
