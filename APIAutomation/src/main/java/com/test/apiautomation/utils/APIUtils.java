package com.test.apiautomation.utils;

import org.testng.Reporter;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

/*
 * Contains methods realted to posting an api request. 
 */

public class APIUtils extends JavaUtils {

	/*
	 * Takes two arguments API endpoint and API Request. 
	 * Gets the server host and path from the configuration that has been already read and put in a hashmap
	 * Http Method : POST
	 */
	public String postRequest(String apiName, String requestInStringFormat) {

		String server = configProperties.get("serverHostName");
		String path = "";

		path = configProperties.get("serverPath");

		String serverURL = server + path + apiName;
		System.out.println(serverURL);
		double startTime = System.currentTimeMillis();

		Response post = given().relaxedHTTPSValidation().contentType("application/json").body(requestInStringFormat)
				.when().post(serverURL);

		double stopTime = System.currentTimeMillis();

		String response = post.asString();
		Reporter.log("\nServer URL : " + serverURL, true);

		Reporter.log("\nTotal time taken for the post request is : " + ((stopTime - startTime) / 1000) + " Seconds ...",
				true);

		Reporter.log("\nRequest sent is \n" + requestInStringFormat, true);

		Reporter.log("\nResponse Obtained : " + response, true);
		return response;
	}
}
