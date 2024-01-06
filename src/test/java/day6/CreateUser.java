package day6;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUser {
	@Test
public void createUserTest()
{
	CreateUserPojo payload=new CreateUserPojo();
	payload.setName("Shalu");
	payload.setJob("Testing");
	
	RestAssured.given()
	.contentType(ContentType.JSON)
	.body(payload)
	.log().all()
	.when()
	.post("https://reqres.in/api/users").then().log().all();
}

}
