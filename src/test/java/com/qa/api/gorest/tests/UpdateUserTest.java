package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest extends BaseTest {
	
	@Test
	public void createUserAndUpdateTest() {
		//Create User
		User newUser = new User(null,"Amit", StringUtils.getRandomEmailId(), "male", "active");
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, newUser, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
		
		int userId = response.jsonPath().getInt("id");
		System.out.println("New User id is " + userId);
		
		//Get User
		Response responseGet = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.jsonPath().getInt("id"), userId);
		
		//update the same user
		newUser.setName("Kamit");
		newUser.setStatus("inactive");
		Response responsePut = restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, newUser, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.jsonPath().getString("name"), newUser.getName());
		Assert.assertEquals(responsePut.jsonPath().getString("status"), newUser.getStatus());
	}
	
	
}
