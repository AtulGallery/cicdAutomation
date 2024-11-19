package filesnew;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import POJO.createOrder;
import POJO.createProduct;
import POJO.loginDetailsforEcomm;
import POJO.loginResponse;
import POJO.orderDetails;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ecommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		loginDetailsforEcomm login = new loginDetailsforEcomm();
		login.setUserEmail("atulpathakdec@gmail.com");
		login.setUserPassword("Atul1234");

		RequestSpecification reqLogin = given().log().all().spec(req).body(login);

		loginResponse loginresponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract()
				.response().as(loginResponse.class);

		System.out.println(loginresponse.getToken());
		String token = loginresponse.getToken();
		System.out.println(loginresponse.getUserId());
		String userId = loginresponse.getUserId();

// Add product

		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();

		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "acer")
				.param("productAddedBy", userId).param("productCategory", "shoes")
				.param("productSubCategory", "shirts").param("productPrice", "1502210")
				.param("productDescription", "del").param("productFor", "men")
				.multiPart("productImage", new File("C:\\Users\\Atul\\Desktop\\testimage.jpg"));

		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product").then().log().all()
				.extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");

//createProduct

		RequestSpecification createProductBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON).build();

		orderDetails orderdetails = new orderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productId);

		List<orderDetails> orderdetailList = new ArrayList<orderDetails>();
		orderdetailList.add(orderdetails);

		createOrder order = new createOrder();
		order.setOrders(orderdetailList);

		RequestSpecification createproduct = given().log().all().spec(createProductBaseReq).body(order);

		String CreateProductresponse = createproduct.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		System.out.println(CreateProductresponse);

		
		//Delete product--how to send path parameter
		
		RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).setContentType(ContentType.JSON).build();
		RequestSpecification deleteProdReq = given().log().all().spec(deleteProductBaseReq)		.pathParam("productId", productId);
	String deleteProductresponse = 	deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then()
		.log().all().extract().response().asString();
	
	JsonPath js1 = new JsonPath(deleteProductresponse);
	String deletemessage = js.get("message");
	System.out.println(deletemessage);
	
	}

}
