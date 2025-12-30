package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest {
	
	@Test
	public void getAllUsersTest() {
	Response response =	restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void getAllUsersWithQueryParam() {
		Map<String,String> queryMap = new HashMap<>();
		queryMap.put("name", "Naveen");
		queryMap.put("status", "active");
		Response response =	restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, queryMap, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
