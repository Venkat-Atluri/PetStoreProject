package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserUrls2;
import io.restassured.response.Response;
import payload.UserModel;

public class UserTestCases2 {
	
	Faker faker;
	UserModel userPayload;
	
	public Logger logger; 
	
	@BeforeClass
	public void setUp() {
		
		faker=new Faker();
		userPayload=new UserModel();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5,10));
		
		//for logs
		logger=LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("********************creating user***********************");
		
		Response response=UserUrls2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***********************user created**************************");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		logger.info("********************retrieving user***********************");
		
		Response response=UserUrls2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********************user retrieved***********************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		logger.info("********************updating user***********************");
		
		//update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserUrls2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate=UserUrls2.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("********************user updated***********************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("********************deleting user***********************");
		
		Response response=UserUrls2.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********************user deleted***********************");
	}

}
