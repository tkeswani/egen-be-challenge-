package usermgmt;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import spark.Spark;
import spark.utils.IOUtils;

public class UserTest {

	 
	@Test
	// checks the response returned for getting all the users.
	// if response status is not 200 it shows fail message "Not Yet Implemented".
	public void testMain() throws JSONException {
		
		TestResponse res = request("GET", "/getusers");
		//JSONObject json = (JSONObject) res.json();
		assertEquals(200, res.status);
		//assertNotNull(json.get("id"));
		//log.info("LIST OF USERS" + json.get("id"));
		//System.out.println(res.status);
		if(res.status != 200) {
			fail("Not yet implemented");
		}
	}
	
	private TestResponse request(String method, String path) {
		try {
			//url that will be called along with the path which is equal to /getusers
			URL url = new URL("http://localhost:4567" + path);
			// establishing the connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String,String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
	@BeforeClass
	// the main method will run at the start of the application testing
	public static void beforeClass() {
		UserManegement.main(null);
	}
	@AfterClass
	// spark will  be stopped at the end of test execution.
	public static void afterClass() {
		Spark.stop();
	}
	
}
