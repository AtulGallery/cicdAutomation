package POJO;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilder {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* RestAssured.baseURI = "https://rahulshettyacademy.com"; */
		
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
		
	RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
	
 ResponseSpecification specrespo =      new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		RequestSpecification res  = given().spec(req).body(p);
		
	Response response =	res.when().post("/maps/api/place/add/json").then().spec(specrespo).extract().response();
	
	String responseString = 	((ResponseBodyData) response).asString(); 
	System.out.println(responseString);
		 
	}

}
