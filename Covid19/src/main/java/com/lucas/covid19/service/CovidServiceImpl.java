package com.lucas.covid19.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

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

	private static final DateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat mySqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
			throw new CovidException("Validation Error!",
					"User not found with this : " + user.getEmailId() + " EmailId.");
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

	@SuppressWarnings("deprecation")
	@Override
	public List<FoodRequest> getFoodHistoryByUser(int userId, String fromDate, String toDate) throws Throwable {

		User adminUser = covidMapper.findUserById(userId);
		if (adminUser == null) {
			throw new CovidException("Validation Error!", "User not found!");
		}

		if (!isValidDateFormat(fromDate)) {
			throw new CovidException("Error!", "Invalid date format: " + fromDate);
		}

		if (!isValidDateFormat(toDate)) {
			throw new CovidException("Error!", "Invalid date format: " + toDate);
		}

		isoDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date fromDateObj = isoDateFormat.parse(fromDate);
		fromDateObj.setHours(0);
		fromDateObj.setMinutes(0);
		fromDateObj.setSeconds(0);
		fromDate = mySqlDateFormat.format(fromDateObj);

		isoDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date toDateObj = isoDateFormat.parse(toDate);
		toDateObj.setHours(23);
		toDateObj.setMinutes(59);
		toDateObj.setSeconds(59);
		toDate = mySqlDateFormat.format(toDateObj);

		List<FoodRequest> foodRequests = new ArrayList<FoodRequest>();
		if (adminUser.getUserType() == 1) {
			foodRequests = covidMapper.filterFoodRequestByDate(fromDate, toDate);
			for (FoodRequest rq: foodRequests) {
				User user = covidMapper.findUserById(rq.getEnteredBy());
				rq.setUser(user);
			}
		} else {
			foodRequests = covidMapper.filterFoodRequestByUserId(userId, fromDate, toDate);
		}
		return foodRequests;

	}

	private boolean isValidDateFormat(String date) throws Throwable {
		final String regex = "^(?:[1-9]\\d{3}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[1-9]\\d(?:0[48]|[2468][048]|[13579][26])|(?:[2468][048]|[13579][26])00)-02-29)";
		return Pattern.compile(regex).matcher(date).find();
	}

}
