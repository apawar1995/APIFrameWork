package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before ("@Deleteplace")
	public void beforeresource() throws IOException
	{
		
		AddPlaceAPIValidation obj = new AddPlaceAPIValidation();
		
		if(AddPlaceAPIValidation.placeID==null)
		{
		obj.add_place_payload_with("Akshay", "Malkapur", "Marathi");
		obj.user_call_with_request("addPlaceAPI", "Post");
		obj.verify_the_place_id_created_maps_to_using_the("Akshay", "getPlaceAPI");
		}
		
		
	}

}
