package com.yahitesh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String email;
	private String pincodes;
	private String notify;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincodes() {
		return pincodes;
	}

	public void setPincodes(String pincodes) {
		this.pincodes = pincodes;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public String getNotify() {
		return notify;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", email=" + email + ", pincodes=" + pincodes + "]";
	}

}
