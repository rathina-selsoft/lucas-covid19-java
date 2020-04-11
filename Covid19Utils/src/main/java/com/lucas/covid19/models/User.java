package com.lucas.covid19.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9070780974832671287L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String fullName;
	private String email;
	private String phoneNumber;
	private String password;
	private int userType;
	private String fcmToken;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", this.userId);
		jsonObject.put("fullName", this.fullName);
		jsonObject.put("email", this.email);
		jsonObject.put("phoneNumber", this.phoneNumber);
		jsonObject.put("userType", this.userType);
		return jsonObject;
	}


}
