package endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.UserModel;

//UserEndPoints.java - created to perform Create, Read, Update and Delete requests to the User API.

public class UserUrls2 {
	
	public static ResourceBundle getURL() {
		ResourceBundle routes=ResourceBundle.getBundle("urls");	//load properties file
		return routes;
	}
	
	//create user
			public static Response createUser(UserModel payload){
				Response response=given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(getURL().getString("post_url"));
				return response;
			}
			
			//read user
			public static Response readUser(String userName){
				Response response=given()
					.pathParam("username", userName)
				.when()
					.get(getURL().getString("get_url"));
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
					.put(getURL().getString("update_url"));
				return response;
			}
			
			//delete user
			public static Response deleteUser(String userName){
				Response response=given()
					.pathParam("username", userName)
				.when()
					.delete(getURL().getString("delete_url"));
				return response;
			}

}
