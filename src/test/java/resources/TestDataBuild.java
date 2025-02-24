package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace addPlacePayload(String name , String address , String language)
	{
		
		AddPlace add = new AddPlace();
		add.setAccuracy(100);
		add.setName(name);
		add.setAddress(address);
		add.setPhone_number("9767880561");
		add.setWebsite("https://ankushpawar.com");
		add.setLanguage(language);
		
		// Creating the list object and passing the data
		ArrayList<String> list = new ArrayList<String>();
		list.add("Last Home");
		list.add("White House");
		add.setTypes(list);
		
		//Creating the objetct for the Location class
		
		Location ln = new Location();
		ln.setLat(-38.383494);
		ln.setLng(33.427362);
		add.setLocation(ln);
		
		return add ;
		
		
	}
	
	public String deleteAPIPayload(String placeID)
	{
		
		
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}\r\n"
				+ "" ;

	}

}
