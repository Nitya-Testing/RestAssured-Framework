package com.qa.api.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.exceptions.APIExceptions;

import io.restassured.response.Response;

public class ObjectMapperUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	public static <T>T deserialize(Response response, Class<T> targetClass) {
		try {
			return mapper.readValue(response.getBody().asString(), targetClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new APIExceptions("Deserialization is failed!! " + targetClass.getName());
		} 
	}

}
