package com.qa.api.scehma.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestApiSchemaTest extends BaseTest {
	
	@BeforeMethod
	public void goRestSetUp() {
		ConfigManager.set("bearerToken", "f10447447800387b779c3400a480df64ac66d8bdcd3293948c1308c84f2192ab");
	}

	@Test
	public void getUsersAPISchemaTest() {
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertTrue(SchemaValidator.validateSchema(response, "getUserSchema.json"));
	}
	
	@Test
	public void createUserAPISchemaTest() {
		User user = new User(null,"Asit", StringUtils.getRandomEmailId(), "male", "active");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(SchemaValidator.validateSchema(response, "createUserSchema.json"));
	}

}
