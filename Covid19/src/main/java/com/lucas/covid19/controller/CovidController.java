package com.lucas.covid19.controller;

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

}
