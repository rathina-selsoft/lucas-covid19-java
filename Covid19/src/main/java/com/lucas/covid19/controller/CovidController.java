package com.lucas.covid19.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.covid19.exceptions.CovidException;
import com.lucas.covid19.models.FoodRequest;
import com.lucas.covid19.models.User;
import com.lucas.covid19.service.CovidService;

@RestController
@RequestMapping(value = "/covid")
public class CovidController {

	@Autowired
	private CovidService covidService;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addClient(HttpServletRequest request, HttpServletResponse httpResponse, @RequestBody User user) {

		JSONObject jsonObject = new JSONObject();
		try {

			httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			user = covidService.addUser(user);

			jsonObject.put("success", true);
			jsonObject.put("user", user.toJSON());
		} catch (CovidException e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getError());
		} catch (Exception e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		} catch (Throwable e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toString();

	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String userLogin(HttpServletRequest request, HttpServletResponse httpResponse, @RequestBody User user) {

		JSONObject jsonObject = new JSONObject();
		try {

			httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			user = covidService.userLogin(user);
			jsonObject.put("success", true);
			jsonObject.put("user", user.toJSON());
		} catch (CovidException e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getError());
		} catch (Exception e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		} catch (Throwable e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toString();

	}

	@RequestMapping(value = "/addFoodRequest", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addFoodRequest(HttpServletRequest request, HttpServletResponse httpResponse,
			@RequestBody FoodRequest foodRequest) {

		JSONObject jsonObject = new JSONObject();
		try {

			httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			foodRequest = covidService.addFoodRequest(foodRequest);
			jsonObject.put("success", true);
			jsonObject.put("foodRequest", foodRequest.toJSON());
		} catch (CovidException e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getError());
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		} catch (Throwable e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toString();

	}

	@RequestMapping(value = "/foodRequestHistory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String foodRequestHistory(HttpServletRequest request, HttpServletResponse httpResponse,
			@RequestBody Map<String, Object> foodRequestJSON) {

		JSONObject jsonObject = new JSONObject();
		try {

			httpResponse.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			
			int userId = foodRequestJSON.containsKey("userId") ? (int) foodRequestJSON.get("userId") : 0;
			String fromDate = foodRequestJSON.containsKey("fromDate") ? (String) foodRequestJSON.get("fromDate") : null;
			String toDate = foodRequestJSON.containsKey("toDate") ? (String) foodRequestJSON.get("toDate") : null;
			
			if (userId == 0) {
				throw new CovidException("Error!", "User id is empty");
			}

			List<FoodRequest> foodRequests = covidService.getFoodHistoryByUser(userId, fromDate, toDate);
			jsonObject.put("success", true);
			jsonObject.put("foodRequests", foodRequests);
		} catch (CovidException e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getError());
		} catch (Exception e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		} catch (Throwable e) {
			jsonObject.put("success", false);
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toString();
	}
	
}
