package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.ObjectMapperUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserUsingDeserialization extends BaseTest {
	
	@BeforeMethod
	public void goRestSetUp() {
		ConfigManager.set("bearerToken", "f10447447800387b779c3400a480df64ac66d8bdcd3293948c1308c84f2192ab");
	}
	
	@Test
	public void createUserTest() {
		User userBody = new User(null,"Aditya", StringUtils.getRandomEmailId(), "male", "active");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userBody, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), userBody.getName());
		
		int userId = response.jsonPath().getInt("id");
		System.out.println("User id is : " + userId);
		
		//Get
		Response getResponse = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		
		//deserialization
		User userResponse = ObjectMapperUtil.deserialize(getResponse, User.class);
		
	    //Assertions
		Assert.assertEquals(userResponse.getName(), userBody.getName());
		Assert.assertEquals(userResponse.getGender(), userBody.getGender());
		Assert.assertEquals(userResponse.getEmail(), userBody.getEmail());
		Assert.assertEquals(userResponse.getStatus(), userBody.getStatus());
		Assert.assertEquals(userResponse.getId(), userId);
		
		
	}

}
