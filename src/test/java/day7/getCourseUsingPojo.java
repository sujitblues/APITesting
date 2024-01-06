package day7;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class getCourseUsingPojo {
	
	@Test
	public void getCourses()
	{
		RestAssured.given()
		.when()
		.get("https://6e49d649-c6f7-4c5e-a395-2707499d3a71.mock.pstmn.io/getcourse")
		.then().log().all().statusCode(200);
	}

}
