package com.qa.api.spotify.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SpotifyAPITest extends BaseTest{
	private String accessToken;
	@BeforeMethod
	public void getAccessToken() {
		Response response = restClient.post(BASE_URL_SPOTIFY, SPOTIFY_TOKEN_ENDPOINT, ConfigManager.get("clientid_spotify"), ConfigManager.get("clientsecret_spotify"),ConfigManager.get("granttype_spotify"), ContentType.URLENC);
		accessToken = response.jsonPath().getString("access_token");
		System.out.println("Access Token is :" + accessToken );
		ConfigManager.set("bearerToken", accessToken);
	}
	
	@Test
	public void getAlbumsTest() {
		Response response = restClient.get(BASE_URL_SPOTIFY_ALBUM, SPOTIFY_ALBUMS_ENDPOINT+"/4aawyAB9vmqN3uQ7FjRGTy", null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
	}
}
