package day8;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;

public class RequestBuilder {
	RequestSpecification request=new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType("application/json").build();
	ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
	int id;
	
	@Test(priority=1)
	public void createUser()
	{
		String payload="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		Response response =RestAssured.given()
		.spec(request)
		.body(payload)
		.when()
		.post("api/users")
		.then().spec(res).extract().response();
		id=response.jsonPath().getInt("id");
		
	}
	
	@Test(priority=2)
	public void getUreateUser()
	{
		
		RestAssured.given()
		.spec(request)
		.pathParam("id", id)
		.when()
		.get("api/users/{id}")
		.then().spec(res);
		
	}
	@Test(priority=3)
	public void updateUser()
	{
		String payload="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		RestAssured.given()
		.spec(request)
		.body(payload)
		.pathParam("id", id)
		.when()
		.put("api/users/{id}")
		.then().spec(res);
		
	}
}
