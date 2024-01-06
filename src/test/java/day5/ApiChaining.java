package day5;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiChaining {
	int id;
	@Test(priority=1)
	public void createUser()
	{
		//https://reqres.in/api/users
		HashMap<String,Object> payload=new HashMap<String,Object>();
		payload.put("name", "Saumya");
		payload.put("job", "SQA");
		
		Response response=RestAssured.given()
		.baseUri("https://reqres.in")
		.header("Content-Type","application/json")
		.body(payload)
		.log().all()
		.when()
		.post("api/users")
		.then().log().all().extract().response();
		id=response.jsonPath().getInt("id");
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 201);
		String contentType=response.getHeader("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
	}
	@Test(priority=2,dependsOnMethods="createUser")
	public void UpdateUser()
	{
		//https://reqres.in/api/users
		HashMap<String,Object> payload=new HashMap<String,Object>();
		payload.put("name", "Saumya Raj");
		payload.put("job", "SQA");
		
		Response response=RestAssured.given()
		.baseUri("https://reqres.in")
		.header("Content-Type","application/json")
		.pathParam("userId", id)
		.body(payload)
		.log().all()
		.when()
		.put("api/users/{userId}")
		.then().log().all().extract().response();
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 200);
		String contentType=response.getHeader("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		
	}
	@Test(priority=3)
	public void getUser()
	{
		//https://reqres.in/api/users
			
		Response response=RestAssured.given()
		.baseUri("https://reqres.in")
		.pathParam("userId", id)
		
		.log().all()
		.when()
		.get("api/users/{userId}")
		.then().log().all().extract().response();
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 200);
		
		
	}
	@Test(priority=3)
	public void deleteUser()
	{
		//https://reqres.in/api/users
			
		Response response=RestAssured.given()
		.baseUri("https://reqres.in")
		
		.pathParam("userId", id)
		.log().all()
		.when()
		.delete("api/users/{userId}")
		.then().log().all().extract().response();
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode, 204);
		
		
	
	}
}
