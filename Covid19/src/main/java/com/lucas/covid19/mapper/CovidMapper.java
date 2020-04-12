package com.lucas.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lucas.covid19.models.FoodRequest;
import com.lucas.covid19.models.User;

@Mapper
public interface CovidMapper {

	void addNewUser(User user) throws Throwable;

	User findUserByEmail(String emailId) throws Throwable;

	User findUserByPhone(String phoneNumber) throws Throwable;

	void addFoodRequest(FoodRequest foodRequest) throws Throwable;

	User findUserById(int enteredBy) throws Throwable;

}
