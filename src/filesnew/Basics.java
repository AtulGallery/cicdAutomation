package filesnew;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
// validate if add place API is working as expected
		// given- all input details
		// when- submit the api -- resource and https methods
		// then- validate the response

		// Add place -> update place with new address -> Get place to validate if new
		// address is present in response
		//how to handle static json payload 
		//As body accepts string ->convert payload content of the file to string -> content of file into Byte-> Byte data into string

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().log().all().queryParams("key", "qaclick123")
				.header("Content-Type", "application/json").body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Atul\\new.json")))).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		// we have a class as JSON path to extract response

		JsonPath js = new JsonPath(response); // for parsing JSON
		String placeID = js.getString("place_id");
		System.out.println(placeID);

		// now we have to update address through put http method -- update place

		String newaddress = "summer walk Africa";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newaddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GET place API to validate if address is changed

		String response1 = RestAssured.given().param("key", "qaclick123").param("place_id", placeID).when()
				.get("/maps/api/place/get/json").then().extract().response().asString();

		System.out.println(response1);

	JsonPath js1 =	Reusablemethods.rawToJson(response1);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newaddress);

	}

}
