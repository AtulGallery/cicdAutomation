package filesnew;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class DynamicJson {
	@Test(dataProvider="booksData")
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI="http://216.10.245.166";
	String response = given().log().all().body(payload.Addbook(isbn,aisle)).
			          when().post("/Library/Addbook.php").
			          then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	String id = js.get("ID");

	System.out.println(id);
	/*
	 * JsonPath js = Reusablemethods.rawtoJSON(response); String id = js.get("ID");
	 * System.out.println(id);
	 */
	
	}

	// array- collection of elements
	//multi-dimensional array-collection of arrays
	@DataProvider(name="booksData")
	public Object[][] getData() {
	
	return new Object [][] {{"azxowocv","09630"},{"azxcqsxwrv","09845350"},{"azxvfrwcttv","098993780"}};	
}
	
	
	@Test(dataProvider="deletebooksData")
	public void deleteBook(String ID) {

		RestAssured.baseURI="http://216.10.245.166";
	String response = given().log().all().body(payload.deletebook(ID)).
			          when().post("/Library/Addbook.php").
			          then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js = new JsonPath(response);
	String message = js.get("msg");

	System.out.println(message);
	
}
	@DataProvider(name="deletebooksData")
	public Object[][] deleteData() {
	
	return new Object [][] {{"azxowocv","09630"},{"azxcqsxwrv","09845350"},{"azxvfrwcttv","098993780"}};	
}	
}
