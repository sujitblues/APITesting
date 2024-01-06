package day10;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CrudOperationPet {
	
	MainPayload payload;
	
	@BeforeClass
	public void dataSet()
	{
		CategoryPojo category =new CategoryPojo();
		category.setId(1);
		category.setName("animal");
		
        
		
				List<String> url=new ArrayList<String>();
				url.add("http://url");
		payload=new MainPayload();
		payload.setId(1);
		payload.setName("doggie");
		payload.setCategory(category);
		//payload.setTags(tag);
		payload.setPhotoUrls(url);
		
}
	
	@Test
	public void CreatePet()
	{
		RestAssured.given()
		.body(payload)
		.when()
		.post("https://petstore.swagger.io/v2/pet")
		.then().statusCode(200);
	}

}
