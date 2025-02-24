package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req ;
	ResponseSpecification res ;
	
	public String globaldata(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream("D:\\Ankush\\Ankush Automation Workplace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		String keydata = prop.getProperty(key);
		return keydata;
		
	}
	
	public RequestSpecification requestspecification() throws IOException
	{
		
		if(req==null)
		{
		
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(globaldata("baseUrl")).addQueryParam("key", "qaclick123")
									.addFilter(RequestLoggingFilter.logRequestTo(log))
									.addFilter(ResponseLoggingFilter.logResponseTo(log))
									.setContentType(ContentType.JSON).build();
		return req;
		}
		
		return req;
	}

	public ResponseSpecification responsespecification()
	{
		
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res; 
		
	}
	
	public String getJosnPath(Response response , String key)
	{
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String keyvalue = js.get(key);
		return keyvalue;
	}
	
	
	
}
