package day1;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class CrudOperation {
	
	@Test(priority=1)
	public void getListUser()
	{
		RestAssured.given()
		.baseUri("https://reqres.in/")
		.log().all()
		.when()
		.get("api/users?page=2")
		.then()
		.log().headers()
		.statusCode(200)
		;
	}
	@Test(priority=2)
	public void createUser()
	{
		//Header headerContent=new Header("Content-Type","application/json");
		//Header headerAccept=new Header("accept","application/json");
	
		//Headers headerContents=new Headers(headerContent,headerAccept);
		HashMap<String,String> headers =new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("accept", "application/json");
		
		String payload="{\r\n"
				+ "    \"name\":\"Rahul\",\r\n"
				+ "    \"job\":\"Team Lead\"\r\n"
				+ "}";
		RestAssured.given()
		.baseUri("https://reqres.in/")
		//.header("Content-Type","application/json")
		//.header("accept","application/json")
		//.header(headerContent)
		//.header(headerAccept)
		.headers(headers)
		.body(payload)
		.log().all()
		.when()
		.post("api/users")
		.then()
		.log().all()
		.statusCode(201);
		
	}
	@Test(priority=3)
	public void updatUser()
	{
		String payload="{\r\n"
				+ "    \"name\":\"Rahul Gupta\",\r\n"
				+ "    \"job\":\"Team Lead\"\r\n"
				+ "}";
		RestAssured.given()
		.baseUri("https://reqres.in/")
		.header("Content-Type","application/json")
		.body(payload)
		.log().all()
		.when()
		.put("api/users/12")
		.then()
		.log().all()
		.statusCode(200);
		
	}
	@Test(priority=4)
	public void deleteUser()
	{
		
		RestAssured.given()
		.baseUri("https://reqres.in/")
		.log().all()
		.when()
		.delete("api/users/12")
		.then()
		.log().all()
		.statusCode(204);
		
	}
		
	}
	
