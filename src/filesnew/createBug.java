package filesnew;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;


public class createBug {
	
	public static void main(String[] args) {
		RestAssured.baseURI = "https://atulpathakdec.atlassian.net/";
		
	String bugResponse =	given().header("Content-Type","application/json").header("Authorization","Basic YXR1bHBhdGhha2RlY0BnbWFpbC5jb206QVRBVFQzeEZmR0Ywd3V6SWI3NW5LN2FYNFJtUGNfUzNDbUp5Z2VVeUUyYTdwaW14Y3lQeTQyQTR2Wk45NFpDaGJVcG5LV3pOcEM1Q04ybXBvN0NKdTV0TEhsSDNCYUFlbkRwcDVjWVR1SF9oSXAxdlJfb29QcUstMEpwSW9xOFAxV3ZoX2xYdEdZNWhSdjR1cVYwVkFjaW9MUEVJME4wQjJXV195SjhBLW1oTmp2U0p4RlNhemVjPTMxOTMyMjcw")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"SCRUM\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"website items are not working-automation\",\r\n"
				+ "\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
	
	JsonPath js  = new JsonPath(bugResponse);
	String issueID = js.getString("id");
	System.out.println(issueID);
	
	//Add attachment
	
	given().pathParam("key", issueID)
	
	.header("X-Atlassian-Token","no-check")
	.header("Authorization","Basic YXR1bHBhdGhha2RlY0BnbWFpbC5jb206QVRBVFQzeEZmR0Ywd3V6SWI3NW5LN2FYNFJtUGNfUzNDbUp5Z2VVeUUyYTdwaW14Y3lQeTQyQTR2Wk45NFpDaGJVcG5LV3pOcEM1Q04ybXBvN0NKdTV0TEhsSDNCYUFlbkRwcDVjWVR1SF9oSXAxdlJfb29QcUstMEpwSW9xOFAxV3ZoX2xYdEdZNWhSdjR1cVYwVkFjaW9MUEVJME4wQjJXV195SjhBLW1oTmp2U0p4RlNhemVjPTMxOTMyMjcw")
	.multiPart("file", new File("C:\\Users\\Atul\\Downloads\\atul_pic.jpg")).log().all()
	.post("rest/api/3/issue/{key}/attachments").then()
	.log().all().assertThat().statusCode(200);
	
	
	}
	
	
	

}
