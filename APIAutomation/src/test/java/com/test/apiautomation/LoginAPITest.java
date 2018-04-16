package com.test.apiautomation;

import java.util.HashMap;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.apiautomation.login.LoginAPIRequestBuilder;
import com.test.apiautomation.utils.JavaUtils;

public class LoginAPITest {

	String apiEndpoint = "login", testType = "Positive", fileKey = "testData", apiTestData="loginTestData";
	JavaUtils javaUtils = new JavaUtils();
	LoginAPIRequestBuilder login;
	HashMap<String, String> usrData;
	
	/*
	 * gets executed before any other method by testNG
	 * reads the configuration and store them in a hashmap
	 */
	@BeforeSuite
	public void readConfig() {
		javaUtils.readConfigProperties();
	}
	
	/*
	 * called after BeforeSuite method
	 * but this depends upon a data provider method
	 * data provider will inject data in this mehtod in a hashmap
	 */
	@Test(dataProvider="getTestData")
	public void loginTest(HashMap<String,String> testData) {
		login = new LoginAPIRequestBuilder();
		String requestPayload = login.getRequest(testData);
		System.out.println(requestPayload);

		//		following method call works if provided url is correct
		//		String response = login.postRequest(apiEndpoint, requestPayload);
		
	}
	
	/*
	 * gets executed before test method
	 * will call test method to the number of rows in 2D array  
	 */
	@DataProvider
	public Object[][] getTestData() {
		Object[][] allValues = javaUtils.returnAllUniqueValuesInMap(fileKey, apiTestData, testType);

		return allValues;

	}
}
