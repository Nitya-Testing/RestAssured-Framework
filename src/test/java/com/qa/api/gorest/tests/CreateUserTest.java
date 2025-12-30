package com.qa.api.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	@Test
	public void createUserByUserLombokTest() {
		
		User userBody = new User("Aditya", StringUtils.getRandomEmailId(), "male", "active");
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userBody, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	
	@Test
	public void createUserByUserJsonFileTest() {
		File userJson = new File("./src/test/resource/jsons/user.json");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}
}
