package com.test.apiautomation.login;

import java.util.HashMap;

import com.google.gson.Gson;
import com.test.apiautomation.utils.APIUtils;

public class LoginAPIRequestBuilder extends APIUtils{

	/*
	 * retrurn string json body
	 */
	public String getRequest(HashMap<String, String> testData) {
		LoginAPIPOJO loginAPIPOJO = new LoginAPIPOJO();
//		 check if value exists in json file then use that otherwise get it from login constant class
		
		loginAPIPOJO.setName((testData.get("username") != null) ? testData.get("username") : LoginConstants.username);
		loginAPIPOJO
				.setPassword((testData.get("password") != null) ? testData.get("password") : LoginConstants.password);
		loginAPIPOJO.setKey((testData.get("key") != null) ? Integer.parseInt(testData.get("key")) : LoginConstants.key);
		return new Gson().toJson(loginAPIPOJO);
	}
}
