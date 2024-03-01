package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.UserUrls;
import payload.UserModel;
import utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String UserID, String userName, String fname, String lname, String useremail, String pwd, String ph) {
		
		UserModel userPayload=new UserModel();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserUrls.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		
		Response response=UserUrls.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
