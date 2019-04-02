package com.api.RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
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
		//Response httpResponse = httpRequestSpecification.request(Method.GET, "/Hyderabad");

		// or we can write like this below to Make a GET request call directly by using RequestSpecification.get() method.
		Response httpResponse = httpRequestSpecification.get("/Hyderabad");

		//
		//TO print the status code of received response AND
		//How to Validate Response Status Code? using statusCode() & getStatusCode() method
		int statusCode = httpResponse.getStatusCode();
		System.out.println("Status Code : " + statusCode);
		int statusCode2 = httpResponse.statusCode();
		System.out.println("Second Status Code : " + statusCode2);
		Assert.assertEquals(statusCode, 200, "InCorrect status code returned");

		//
		//How to Validate Response Status Line?
		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = httpResponse.getStatusLine();
		System.out.println("Resonse Status Line : " + statusLine);
		//Validate Response Status Line?
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Correct status code returned");

		//
		//Validate Response Header using Rest Assured? using header() and getHeaders() Method
		String contentType = httpResponse.header("Content-Type");
		System.out.println("Content Type : " + contentType);
		Assert.assertEquals(contentType, "application/json"); //Validation

		String serverType = httpResponse.getHeader("Server");
		System.out.println("Server Type : " + serverType);

		String languageAccepted = httpResponse.header("Content-Encoding");
		System.out.println("Language Accepted : " + languageAccepted);

		//print all the headers using headers() & getHeaders() method
		System.out.println("----------------ALL THE HEADERS----------------------");
		//Headers ListHeaders = httpResponse.getHeaders();
		Headers ListHeaders = httpResponse.headers();
		for (Header header : ListHeaders) {
			System.out.println(header.getName() + "     " + header.getValue());
		}
		System.out.println("----------------END OF ALL THE HEADERS----------------------");

		//
		// Read JSON Response Body using Rest Assured using body() and getBody() methods
		String responseBody = httpResponse.getBody().asString();
		System.out.println("My Response BODY 1 : " + responseBody);

		String body1 = httpResponse.body().asString();
		System.out.println("My Response BODY 2 " + body1);

		// we can do like these also
		//ResponseBody body3 = httpResponse.getBody();
		ResponseBody body3 = httpResponse.body();
		System.out.println("My Resonse Body 3 : " + body3.asString());

		//How to Validate Response Body contains some String?
		Assert.assertEquals(body1.contains("Hyderabad"), true);
		//Assert.assertEquals(body1.contains("Hyderabad"), "Hyderabad");  //<- throwing error but why?

		//How to Extract a Node text from Response using JsonPath?
		JsonPath jsonPath = httpResponse.jsonPath();
		String city = jsonPath.get("City");
		System.out.println("City Received From JsonPath : " + city);

		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Incorrect city name received in the Response");

		String humidity = jsonPath.get("Humidity");
		System.out.println("Humidity Received From JsonPath : " + humidity);

		// it return null because JsonPath only use to get the data from resonse body not from header
		String contentType1 = jsonPath.get("Content-Type");
		System.out.println("Humidity Received From JsonPath : " + contentType1);

	}
}