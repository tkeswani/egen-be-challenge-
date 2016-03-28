package usermgmt;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;
public class test {

	public test (final UserService userService) {
		System.out.println("In TEST");
		
		// Usage of Lambda expressions which is JAVA 8 features.
		
		// this statement will be executed when the url http://localhost:4567/createuser will be run
		// this will create the user and enter into the mongodb
		post("/createuser",(req,res)->userService.createUser(req.body()),JsonUtil.json());
		
		//this statement will be executed when the url http://localhost:4567/updateuser will be run
		// this will update the user if the input has the same id.
		put("/updateuser",(req,res)->userService.updateUser(req.body()),JsonUtil.json());
		
		//this statement will be executed when the url http://localhost:4567/getusers will be run
		// will get all the users in the mongodb.
		get("/getusers", (req,res)->userService.getUser(),JsonUtil.json());
		
		// using this filter the response will be returned in json format.
		after((req, res) -> {
			res.type("application/json");
			res.status(200);
			});
		
	}

}
