package com.qa.api.client;

import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIExceptions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;

public class RestClient {
	
	//define responseSpec
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	
	private RequestSpecification setupRequest(String baseUrl,AuthType authType, ContentType contentType) {
		RequestSpecification request =  RestAssured.given().log().all().baseUri(baseUrl).contentType(contentType).accept(contentType);
		
		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " );
			break;
		
		case BASIC_AUTH:
			request.header("Authorization", "Basic " );
			break;
			
		case API_KEY:
			request.header("x-api-key", "api key " );
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
		if(queryParms != null) {
			request.queryParams(queryParms);
		}
		if(pathParms != null) {
			request.pathParams(pathParms);
		}
		
	}
	/**
	 * This method is used to call any GET APIs...
	 * @param baseUrl
	 * @param endPoint
	 * @param pathParms
	 * @param queryParms
	 * @param authType
	 * @param contentType
	 * @return It returns the GET API call response
	 */
	public Response get(String baseUrl,String endPoint, Map<String,String> pathParms, Map<String,String> queryParms, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseUrl,authType,contentType);
		applyParams(request,pathParms,queryParms);
		
		Response response = request.get(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}

}
