package usermgmt;

import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class UserService {

	public String createUser(String input) throws JSONException, UnknownHostException {
		System.out.println("INPUT " + input);
//		String id = (String) inputJsonObj.get("id");
//		String firstName = (String) inputJsonObj.get("firstName");
//		String lastName = (String) inputJsonObj.get("lastName");
//		String email = (String) inputJsonObj.get("email");
//		JSONObject address = (JSONObject) inputJsonObj.get("address");
//		String city = (String) address.get("city");
		
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("UserManagementDB");
		
		DBCollection collection = db.getCollection("UserManagement");
		DBObject dbObject = (DBObject) JSON.parse(input);
		System.out.println("LINK " + dbObject.get(input));
		JSONObject obj = new JSONObject(input);
		//System.out.println("PPPPP " + obj.getString("id"));
		collection.insert(dbObject); 
//		DBCursor cursorDoc = collection.find();  
//		
//		while (cursorDoc.hasNext()) {  
//			  //System.out.println(cursorDoc.next());  
//		} 
//		
		System.out.println("DONE");
		
		//System.out.println(city);
		return input;
	}
	
	public String updateUser(String input) throws JSONException, UnknownHostException {
		
		Mongo mongo = new Mongo("localhost", 27017);
		DB db = mongo.getDB("UserManagementDB");
		String status="200";
		DBCollection collection = db.getCollection("UserManagement");
		JSONObject obj = new JSONObject(input);
		BasicDBObject bd = new BasicDBObject();
		bd.put("id", obj.getString("id"));
		DBCursor cursorDoc = collection.find(bd);
		System.out.println("COUNT =" + cursorDoc.count());	
		if(cursorDoc.count()>0) {
		//while (cursorDoc.hasNext()) {  
			 BasicDBObject dbObject =   (BasicDBObject) JSON.parse(input);
			 collection.update(bd , dbObject);
			 //System.out.println(cursorDoc.next());
		//}
		}
		else {
			status = "404";
			return status;
		}
		 return status;
		
	}
	
	public ArrayList<Object> getUser() throws UnknownHostException  {
		// connection to mongodb
		Mongo mongo = new Mongo("localhost", 27017);
		// Getting the connection to MongoDb - the database name is "UserManagementDB"
		DB db = mongo.getDB("UserManagementDB");
		//The table name is UserManagement from which the result is collected as Collection
		DBCollection collection = db.getCollection("UserManagement");
		//collection.find() provides all the data inserted in the database
		DBCursor cursorDoc = collection.find();
		//creating an arraylist to insert the data received from the database and return it to be 
		// displayed as reponse in JSON format.
		ArrayList<Object> ob = new ArrayList<>();
		while(cursorDoc.hasNext()) {
			// adding the data one by one to the arraylist
			ob.add(cursorDoc.next());
		}
		return ob;
	}
}
