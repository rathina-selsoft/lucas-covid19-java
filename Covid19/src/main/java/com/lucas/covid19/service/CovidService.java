package com.lucas.covid19.service;

import org.springframework.stereotype.Service;

import com.lucas.covid19.models.User;

@Service
public interface CovidService {

	User addUser(User user) throws Throwable;

	User userLogin(User user) throws Throwable;

}
