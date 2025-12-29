package com.qa.api.base;

import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

public class BaseTest {
	
	//********** API Base URLs *******************************
	protected static final String BASE_URL_GOREST = "https://gorest.co.in";
	
	
	//********** API End Points *******************************
	protected static final String GOREST_USERS_ENDPOINT = "/public/v2/users";
	
	protected RestClient restClient;
	
	@BeforeTest
	public void initSetup() {
		restClient = new RestClient();
	}

}
