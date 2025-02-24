Feature: Validating the AddPlace API

@Addplace
Scenario Outline:
Verify if the place is getting added by using the addPalce API
Given Add place payload with "<name>" "<address>" "<language>"
When user call "addPlaceAPI" with "POST" request
Then the API call got success with status code 200
And the "status" in the response body is "OK"
And the "scope" in the response body is "APP"
And verify the place_id created maps to "<name>" using the "getPlaceAPI"

Examples: 
				 | name   | address   | language   |
				 | Ankush | Gunji			| English		 | 
#				 | Pawar  | Dmn				| French		| 


@Deleteplace
Scenario: Verify if the Delete placeAPI is working
Given DeletePlace Payload
When user call "deletePlaceAPI" with "POST" request
Then the "status" in the response body is "OK"