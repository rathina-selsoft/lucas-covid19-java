package com.lucas.covid19.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.covid19.exceptions.CovidException;
import com.lucas.covid19.mapper.CovidMapper;
import com.lucas.covid19.models.FoodRequest;
import com.lucas.covid19.models.User;

@Service("covidService")
public class CovidServiceImpl implements CovidService {
	
	@Autowired
	private CovidMapper covidMapper;

	@Override
	public User addUser(User user) throws Throwable {
		
		if (StringUtils.isBlank(user.getFullName())) {
			throw new CovidException("Validation Error!", "User Full Name not found!");
		}
		
		if (StringUtils.isBlank(user.getEmailId())) {
			throw new CovidException("Validation Error!", "User Email not found!");
		}
		
		if (StringUtils.isBlank(user.getPhoneNumber())) {
			throw new CovidException("Validation Error!", "User Phone Number not found!");
		}
		
		if (StringUtils.isBlank(user.getPassword())) {
			throw new CovidException("Validation Error!", "User Password not found!");
		}
				
		validateUserEmail(user);
		covidMapper.addNewUser(user);		
		return user;
	}

	private void validateUserEmail(User user) throws Throwable {
		User userEmail = covidMapper.findUserByEmail(user.getEmailId());
		if (userEmail != null) {
			throw new CovidException("Validation Error!", "User Email already exist!");
		}
		
		User userPhone = covidMapper.findUserByPhone(user.getPhoneNumber());
		if (userPhone != null) {
			throw new CovidException("Validation Error!", "User Phone Number already exist!");
		}
	}

	@Override
	public User userLogin(User user) throws Throwable {
		if (StringUtils.isBlank(user.getEmailId())) {
			throw new CovidException("Validation Error!", "User Email not found!");
		}
		
		if (StringUtils.isBlank(user.getPassword())) {
			throw new CovidException("Validation Error!", "User Password not found!");
		}
		
		User userEmail = covidMapper.findUserByEmail(user.getEmailId());
		if (userEmail == null) {
			throw new CovidException("Validation Error!", "User not found with this : " + user.getEmailId() + " EmailId.");
		}
		
		if (!StringUtils.equals(userEmail.getPassword(), user.getPassword())) {
			throw new CovidException("Validation Error!", "User Password mismatch!");
		}
		
		return userEmail;
	}

	@Override
	public FoodRequest addFoodRequest(FoodRequest foodRequest) throws Throwable {
		if (foodRequest.getEnteredBy() == 0) {
			throw new CovidException("Validation Error!", "Food entered is missing!");
		}
		
		User user = covidMapper.findUserById(foodRequest.getEnteredBy());
		if (user == null) {
			throw new CovidException("Validation Error!", "User not found!");
		}
		
		if (StringUtils.isBlank(foodRequest.getFullName())) {
			throw new CovidException("Validation Error!", "Name not found!");
		}
		
		if (StringUtils.isBlank(foodRequest.getPhoneNumber())) {
			throw new CovidException("Validation Error!", "Phone Number not found!");
		}
		
		if (StringUtils.isBlank(foodRequest.getAddress())) {
			if (foodRequest.getLatitude() == 0 || foodRequest.getLongitude() == 0) {
				throw new CovidException("Validation Error!", "Please enable current location");
			}
		}
		
		if (foodRequest.getDateLong() == 0) {
			throw new CovidException("Validation Error!", "Date is empty!");
		}
		
		if (foodRequest.getTimeLong() == 0) {
			throw new CovidException("Validation Error!", "Time is empty!");
		}
		
		covidMapper.addFoodRequest(foodRequest);
		return foodRequest;
	}

}
