package com.lucas.covid19.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lucas.covid19.models.User;

@Mapper
public interface CovidMapper {

	void addNewUser(User user) throws Throwable;

}
