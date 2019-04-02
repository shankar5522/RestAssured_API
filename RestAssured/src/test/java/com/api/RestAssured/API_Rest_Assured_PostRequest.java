/**
 * 
 */
package com.api.RestAssured;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Shankar
 *
 */
public class API_Rest_Assured_PostRequest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender11");
		requestParams.put("LastName", "Singh11");
		requestParams.put("UserName", "sdimpleuser2dd201111");
		requestParams.put("Password", "password111");
		requestParams.put("Password", "password111"); // put() method is from hashpmap, Hence removing duplicate data from map 
		requestParams.put("Email", "sample2ee26d911@gmail.com");

		System.out.println(requestParams.toString());
		System.out.println("!!!!!! " + requestParams.toJSONString());
		System.out.println("@@@@@@ " + requestParams.toString("FirstName", "Virender")); //Key, value pair

		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		request.body(requestParams.toJSONString());

		// Post the request and check the response
		Response response = request.post("/register");

		//Step 4: Validate the Response

		int statusCode = response.getStatusCode();
		System.out.println("Status Code : " + statusCode);
		//Assert.assertEquals(statusCode, "201");

		/*we can doo like for the below code to get the successCode
		 * 
		 * JsonPath jsonPath = response.jsonPath();
		String successCode = jsonPath.getString("Message");*/

		String successCode = response.jsonPath().get("Message");
		System.out.println("Success Code : " + successCode);
		//Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");

		// Now priting the content of response received from server
		System.out.println("Response body: " + response.body().asString());
		System.out.println("Response ststus code : " + response.getStatusCode());
		System.out.println("Resoponse status line : " + response.getStatusLine());
		System.out.println("Response Body : " + response.getBody().asString()); // we can get the response body using getBody() method too
		//System.out.println("Response header : " + response.getBody().toString()); //tostring method is not works here

	}

}
