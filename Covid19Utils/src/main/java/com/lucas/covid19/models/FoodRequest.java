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
@Table(name = "FoodRequest")
public class FoodRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4896606399535337241L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int foodId;

	private String fullName;
	private String phoneNumber;
	private String address;
	private double latitude;
	private double longitude;
	private String date;
	private long dateLong;
	private String time;
	private long timeLong;

	private int enteredBy;
	private User user;

	private Timestamp createdAt;
	private Timestamp updatedAt;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getDateLong() {
		return dateLong;
	}

	public void setDateLong(long dateLong) {
		this.dateLong = dateLong;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public long getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(long timeLong) {
		this.timeLong = timeLong;
	}

	public int getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(int enteredBy) {
		this.enteredBy = enteredBy;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("foodId", this.foodId);
		jsonObject.put("fullName", this.fullName);
		jsonObject.put("phoneNumber", this.phoneNumber);
		jsonObject.put("address", this.address);
		jsonObject.put("latitude", this.latitude);
		jsonObject.put("longitude", this.longitude);
		jsonObject.put("date", this.date);
		jsonObject.put("dateLong", this.dateLong);
		jsonObject.put("time", this.time);
		jsonObject.put("timeLong", this.timeLong);
		jsonObject.put("enteredBy", this.enteredBy);
		if (user != null)
			jsonObject.put("user", this.user.toJSON());
		return jsonObject;
	}

}
