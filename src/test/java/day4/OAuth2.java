package day4;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2 {
	String Access_token;
	
	@Test
	public void getAccessToken()
	{
		String username="AX_tLWfeEFeaYeg5B7zMY6lswRi9lYUr2M5udXn97zNya-FeYJog8xw1Y2QTCSSRRn704jLnxWjglJGf";
		String password="EEM3Lc6lXRMVoK82oZY6xZ-14GmziyLKgHYRr3IAwQGxAAjyDjEqU7CYLVKhlQ8NBVXA3pvEF15EEXJn";
		Response response=RestAssured.given()
		.auth().preemptive().basic(username, password)
		.queryParam("grant_type", "client_credentials")
		.log().all()
		.when()
		.post("https://api-m.sandbox.paypal.com/v1/oauth2/token")
		.then().log().all().extract().response();
		
		Access_token=response.jsonPath().getString("access_token");
		
	}
	
	@Test
public void getInvoices()
{
		//https://api-m.sandbox.paypal.com/v1/invoicing/invoices?page=3&page_size=4&total_count_required=true
	Response response=RestAssured.given()
	.baseUri("https://api-m.sandbox.paypal.com")
	.header("Authorization","Bearer "+Access_token)
	.queryParam("page", 2)
	.queryParam("page_size", 4)
	.queryParam("total_count_required", true)
	.when()
	.get("/v1/invoicing/invoices")
	.then().log().all().extract().response();
}
}
