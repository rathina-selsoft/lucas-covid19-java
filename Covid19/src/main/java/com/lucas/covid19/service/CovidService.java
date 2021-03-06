package com.lucas.covid19.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucas.covid19.models.FoodRequest;
import com.lucas.covid19.models.User;

@Service
public interface CovidService {

	User addUser(User user) throws Throwable;

	User userLogin(User user) throws Throwable;

	FoodRequest addFoodRequest(FoodRequest foodRequest) throws Throwable;

	List<FoodRequest> getFoodHistoryByUser(int userId, String fromDate, String toDate) throws Throwable;

}
