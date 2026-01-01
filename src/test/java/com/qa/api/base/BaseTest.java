package com.qa.api.base;

import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;

public class BaseTest {
	
	//********** API Base URLs *******************************
	protected static final String BASE_URL_GOREST = "https://gorest.co.in";
	protected static final String BASE_URL_HEROKU_BASIC_AUTH = "https://the-internet.herokuapp.com";
	protected static final String BASE_URL_SPOTIFY = "https://accounts.spotify.com";
	
	
	//********** API End Points *******************************
	protected static final String GOREST_USERS_ENDPOINT = "/public/v2/users";
	protected static final String HEROKU_BASIC_AUTH_ENDPOINT = "/basic_auth";
	protected static final String SPOTIFY_TOKEN_ENDPOINT = "/api/token";
	
	protected RestClient restClient;
	
	@BeforeTest
	public void initSetup() {
		restClient = new RestClient();
	}

}
