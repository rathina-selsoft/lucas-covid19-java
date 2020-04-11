package com.lucas.covid19.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.covid19.exceptions.CovidException;
import com.lucas.covid19.mapper.CovidMapper;
import com.lucas.covid19.models.User;

@Service("covidService")
public class CovidServiceImpl implements CovidService {
	
	@Autowired
	private CovidMapper covidMapper;

	@Override
	public User addUser(User user) throws Throwable {
		
		if (StringUtils.isNotBlank(user.getEmail())) {
			throw new CovidException("Validation Error!", "User Email not found!");
		}
		
		if (StringUtils.isNotBlank(user.getFullName())) {
			throw new CovidException("Validation Error!", "User Full Name not found!");
		}
		
		if (StringUtils.isNotBlank(user.getPhoneNumber())) {
			throw new CovidException("Validation Error!", "User Phone Number not found!");
		}
		
		if (StringUtils.isNotBlank(user.getPassword())) {
			throw new CovidException("Validation Error!", "User Password not found!");
		}
				
		covidMapper.addNewUser(user);		
		return user;
	}

}