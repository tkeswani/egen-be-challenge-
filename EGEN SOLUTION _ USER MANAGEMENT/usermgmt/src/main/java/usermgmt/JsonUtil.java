package usermgmt;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import spark.ResponseTransformer;


// class will convert the Object into JSON format.
public class JsonUtil {
	
	// COnverts an object to JSON using GSON 
	public static String toJson(Object object) {
		
		return new Gson().toJson(object);
		}
		
	//returns a ResponseTransformer instance
	public static ResponseTransformer json() {
		return JsonUtil::toJson;
		}
}
