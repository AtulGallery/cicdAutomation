package POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setAddress("29, sid1e layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhonenumber("(+91) 983 893 3937");
		p.setName("Atul academy");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		p.setLocation(location);

		Response res  = given().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract().response();
	String responseString = 	res.asString();
	System.out.println(responseString);
		
	}

}
