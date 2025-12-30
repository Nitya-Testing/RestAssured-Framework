package com.qa.api.client;

import java.io.File;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIExceptions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.*;

public class RestClient {

	// define responseSpec
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification responseSpec204 = expect().statusCode(204);

	private RequestSpecification setupRequest(String baseUrl, AuthType authType, ContentType contentType) {
		RequestSpecification request = RestAssured.given().log().all().baseUri(baseUrl).contentType(contentType)
				.accept(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer 120c4667fe92fd1b40733ae5aa40f9435c0653d197693bb2e885ac1748ea8bf7");
			break;

		case BASIC_AUTH:
			request.header("Authorization", "Basic ");
			break;

		case API_KEY:
			request.header("x-api-key", "api key ");
			break;

		case NO_AUTH:
			System.out.println("Auth is not required...");
			break;

		default:
			throw new APIExceptions("======== Invalid AuthType  =========");
		}

		return request;
	}

	private void applyParams(RequestSpecification request, Map<String, String> pathParms,
			Map<String, String> queryParms) {
		if (queryParms != null) {
			request.queryParams(queryParms);
		}
		if (pathParms != null) {
			request.pathParams(pathParms);
		}

	}

	/**
	 * This method is used to call any GET APIs...
	 * 
	 * @param baseUrl
	 * @param endPoint
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the GET API call response
	 */
	public Response get(String baseUrl, String endPoint, Map<String, String> pathParms, Map<String, String> queryParms,
			AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, pathParms, queryParms);

		Response response = request.get(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This method is used to call any POST APIs...
	 * 
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the POST API call response
	 */
	public <T> Response post(String baseUrl, String endPoint, T body, Map<String, String> pathParms,
			Map<String, String> queryParms, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, pathParms, queryParms);

		Response response = request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This method is used to call any POST APIs...
	 * 
	 * @param baseUrl
	 * @param endPoint
	 * @param filePath
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the POST API call response
	 */

	public Response post(String baseUrl, String endPoint, File filePath, Map<String, String> pathParms,
			Map<String, String> queryParms, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, pathParms, queryParms);

		Response response = request.body(filePath).post(endPoint).then().spec(responseSpec200or201).extract()
				.response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This method is used to call any PUT APIs...
	 * 
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the PUT API call response
	 */
	public <T> Response put(String baseUrl, String endPoint, T body, Map<String, String> pathParms,
			Map<String, String> queryParms, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, pathParms, queryParms);

		Response response = request.body(body).put(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
	}

	/**
	 * This method is used to call any PATCH APIs...
	 * 
	 * @param <T>
	 * @param baseUrl
	 * @param endPoint
	 * @param body
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the PATCH API call response
	 */
	public <T> Response patch(String baseUrl, String endPoint, T body, Map<String, String> pathParms,
			Map<String, String> queryParms, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);
		applyParams(request, pathParms, queryParms);

		Response response = request.body(body).patch(endPoint).then().spec(responseSpec200or201).extract().response();
		response.prettyPrint();
		return response;
	}
	
	/**
	 * This method is used to call any DELETE APIs...
	 *
	 * @param baseUrl
	 * @param endPoint
	 * @param authType
	 * @param contentType
	 * @return It returns the DELETE API call response
	 */
	public Response delete(String baseUrl, String endPoint, AuthType authType, ContentType contentType) {
		RequestSpecification request = setupRequest(baseUrl, authType, contentType);

		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}
	
	

}
