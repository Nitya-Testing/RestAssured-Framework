package com.qa.api.spotify.tests;

import org.testng.annotations.BeforeTest;

import com.qa.api.base.BaseTest;

public class SpotifyAPITest extends BaseTest{
	@BeforeTest
	public void getAccessToken() {
		restClient.post(BASE_URL_SPOTIFY, SPOTIFY_TOKEN_ENDPOINT, BASE_URL_SPOTIFY, BASE_URL_HEROKU_BASIC_AUTH, BASE_URL_GOREST, null);
	}
}
