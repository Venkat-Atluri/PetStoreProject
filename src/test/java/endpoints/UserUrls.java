package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.UserModel;

public class UserUrls {
	
		//create user
		public static Response createUser(UserModel payload){
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(Urls.post_url);
			return response;
		}
		
		//read user
		public static Response readUser(String userName){
			Response response=given()
				.pathParam("username", userName)
			.when()
				.get(Urls.get_url);
			return response;
		}
		
		//update user
		public static Response updateUser(String userName, UserModel payload){
			Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
			.when()
				.put(Urls.put_url);
			return response;
		}
		
		//delete user
		public static Response deleteUser(String userName){
			Response response=given()
				.pathParam("username", userName)
			.when()
				.delete(Urls.delete_url);
			return response;
		}

}
