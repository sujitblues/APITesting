package day3;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Authorization {
	
	@Test
	public void Basic()
	{
		//https://postman-echo.com/basic-auth
		Response response=RestAssured.given()
		.baseUri("https://postman-echo.com")
		.auth().basic("postman", "password")
		.log().all()
		.when()
		.get("/basic-auth")
		.then().log().all().extract().response();
		
		int status=response.statusCode();
		String success=response.jsonPath().getString("authenticated");
		
		Assert.assertEquals(status, 200);
		Assert.assertEquals(success, "true");
	
		
	}
	public void digest()
	{
		//https://postman-echo.com/digest-auth
		Response response=RestAssured.given()
		.baseUri("https://postman-echo.com")
		.auth().digest("postman", "password")
		.log().all()
		.when()
		.get("/digest-auth")
		.then().log().all().extract().response();
		
		int status=response.statusCode();
		String success=response.jsonPath().getString("authenticated");
		
		Assert.assertEquals(status, 200);
		Assert.assertEquals(success, "true");
	
		
	}
	
	@Test
	public void berrarToken()
	{
		//https://gorest.co.in/public/v2/users
		Faker faker=new Faker();
		String token="1b12c0a69e060daa4cfdb44d95ab08c5d135a70e2555cde2e2e8f6a27f3813ff";
		//.header("Authorization","Bearer "+"1b12c0a69e060daa4cfdb44d95ab08c5d135a70e2555cde2e2e8f6a27f3813ff")
		/*
		 * HashMap<String,Object> payload=new HashMap<String,Object>();
		 * payload.put("name", faker.name()); payload.put("gender", "Female");
		 * payload.put("email", "xyx@mailinator.com"); payload.put("satus", "active");
		 */
		String payload="{\r\n"
				+ "\"name\":\"Ramesh\",\r\n"
				+ "\"gender\":\"male\",\r\n"
				+ "\"email\":\"ramesh2@mailinator.com\",\r\n"
				+ "\"satus\":\"active\"\r\n"
				+ "}";
		Response response=RestAssured.given()
		.baseUri("https://gorest.co.in/public/v2")
		.header("Authorization","Bearer "+token)
		.header("Content-Type","application/json")
		.body(payload)
		.log().all()
		.when()
		.post("/users")
		.then().log().all().extract().response();
		
		int status=response.statusCode();
			
		Assert.assertEquals(status, 200);
		
		
	}
	

	@Test
	public void apikey()
	{
		//https://api.openweathermap.org/data/2.5/weather
		
		String apikey="566be371a97a11b1763b0cdaaca945d8";
		
		Response response=RestAssured.given()
		.baseUri("https://api.openweathermap.org")
		.queryParam("appid", apikey)
		.queryParam("q", "Delhi")
		.log().all()
		.when()
		.get("/data/2.5/weather")
		.then().log().all().extract().response();
		
		int status=response.statusCode();
			
		Assert.assertEquals(status, 200);
			
	}
	
}
