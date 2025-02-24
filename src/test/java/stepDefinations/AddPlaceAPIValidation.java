package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;
import pojo.AddPlace;


public class AddPlaceAPIValidation extends Utils {
	
	RequestSpecification req1 ;
	Response response ;
	TestDataBuild data = new TestDataBuild();
	static String placeID ;
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
			
		// Storing the request in a RequestSpecification object
		 req1 = given().spec(requestspecification()).body(data.addPlacePayload(name , address , language ));
		
	}
		
	@When("user call {string} with {string} request")
	public void user_call_with_request(String resource, String method) {
		
		APIresources resourceobj =APIresources.valueOf(resource);
		String resourcevalue = resourceobj.getresources();
		
		if(method.equalsIgnoreCase("post"))
		response  =req1.when().post(resourcevalue)
		.then().spec(responsespecification()).extract().response();
		else if (method.equalsIgnoreCase("GET"))
			response  =req1.when().get(resourcevalue)
			.then().spec(responsespecification()).extract().response();
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(), 200);
		
	    
	}
	@Then("the {string} in the response body is {string}")
	public void the_in_the_response_body_is(String actvalue, String expvalue) {
		
		String actual = getJosnPath(response , actvalue);
		System.out.println(actual);
		assertEquals(actual, expvalue);
		
	}

	@Then("verify the place_id created maps to {string} using the {string}")
	public void verify_the_place_id_created_maps_to_using_the(String expectedname, String resource) throws IOException {
	   
		placeID = getJosnPath(response , "place_id");
		req1 = given().spec(requestspecification()).queryParam("place_id", placeID);
		user_call_with_request(resource, "GET");
		String actname = getJosnPath(response , "name");
		assertEquals(actname , expectedname);
		
		System.out.println(actname);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   
		req1 = given().spec(requestspecification()).body(data.deleteAPIPayload(placeID));
		
	 	
		
	}

	
	
}
