package day9;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class payloadExternal {
	
	@Test
	public void externalFile()
	{
		File payload=new File("C:\\Users\\sujit\\OneDrive\\Desktop\\payload.json");
		Response response=RestAssured.given()
		.header("Content-Type","application/json")
		.body(payload)
		.log().all()
		.when()
		.post("https://petstore.swagger.io/v2/pet")
		.then().extract().response();
		int status_code=response.statusCode();
		//String header_contentType=response.getHeader("Contetnt-Type");
		Assert.assertEquals(status_code, 200);
		//Assert.assertEquals(header_contentType, "application/json; charset=utf-8");
	}

}
