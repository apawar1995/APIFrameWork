package resources;

public enum APIresources {
	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource ;
	
	
	APIresources(String resource)
	{
		this.resource = resource;
	}
	
	public String getresources()
	{
		return resource;
		
	}
	

}
