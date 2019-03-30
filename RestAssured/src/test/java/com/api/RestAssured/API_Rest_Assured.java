package com.api.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_Rest_Assured {

	public static void main(String[] args) {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequestSpecification = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response httpResponse = httpRequestSpecification.request(Method.GET, "/Hyderabad");

		// Make a GET request call directly by using RequestSpecification.get() method.
		//Response httpResponse = httpRequestSpecification.get("/Hyderabad");

		//TO print the status code of received response AND
		//How to Validate Response Status Code?
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code : " + statusCode);
		int statusCode2 = httpResponse.statusCode();
		System.out.println("Second Status Code : " + statusCode2);
		Assert.assertEquals(statusCode, 200, "InCorrect status code returned");

		//How to Validate Response Status Line?
		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = httpResponse.getStatusLine();
		System.out.println("Resonse Status Line : " + statusLine);
		//Validate Response Status Line?
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Correct status code returned");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = httpResponse.getBody().asString();
		System.out.println("My Response BODY is : " + responseBody);
	}
}