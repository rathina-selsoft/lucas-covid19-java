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
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7555246760863139293L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userTypeId;

	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		jsonObject.put("userTypeId", this.userTypeId);
		jsonObject.put("name", this.name);
		return jsonObject;
	}


}
