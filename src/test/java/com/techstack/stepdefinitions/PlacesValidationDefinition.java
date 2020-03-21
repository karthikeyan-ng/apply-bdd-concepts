package com.techstack.stepdefinitions;

import com.sun.tools.javac.util.List;
import com.techstack.api.AddPlace;
import com.techstack.api.Location;
import com.techstack.utils.ApiSpecification;
import com.techstack.utils.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PlacesValidationDefinition {

    private RequestSpecification reqSepc;
    private ResponseSpecification resSpec;
    private Response response;
    private JsonPath jsonPath;
    private String placeId;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload_with(String name, String language, String address) throws FileNotFoundException {
        reqSepc = given().spec(ApiSpecification.getRestContextPath()).body(createPlace(name, language, address));
    }

//    @When("User calls {string} with POST HTTP method")
//    public void user_calls_with_POST_HTTP_method(String resourceHint) {
//        resSpec = new ResponseSpecBuilder()
//                .expectStatusCode(200).expectContentType(ContentType.JSON).build();
//
//        response = reqSepc.when()
//                .post(ResourcePath.valueOf(resourceHint).getResource())
//                .then().spec(resSpec).extract().response();
//    }

    @When("User calls {string} with {string} HTTP method")
    public void user_calls_with_HTTP_method(String resourceHint, String httpVerb) {
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(httpVerb.equals("POST")) {
            response = reqSepc.when()
                    .post(ResourcePath.valueOf(resourceHint).getResource())
                    .then().spec(resSpec).extract().response();
        } else if(httpVerb.equals("GET")) {
            response = reqSepc.when()
                    .get(ResourcePath.valueOf(resourceHint).getResource())
                    .then().spec(resSpec).extract().response();
        }
    }

    @Then("The API call is success with status code {int}")
    public void the_API_call_is_success_with_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        String responseString = response.asString();
        jsonPath = JsonUtils.getJsonPathFromResponse(responseString);
        String actualStatus = jsonPath.getString(key);
        assertEquals(value, actualStatus);
        placeId = jsonPath.getString("place_id");
    }

    @Then("Verify the place id created maps to {string} using {string}")
    public void verify_the_place_id_created_maps_to_using(String placeName, String resourceHint) throws FileNotFoundException {
        reqSepc = given().spec(ApiSpecification.getRestContextPath()).queryParam("place_id", placeId);
        user_calls_with_HTTP_method(resourceHint, "GET");

        String responseString = response.asString();
        jsonPath = JsonUtils.getJsonPathFromResponse(responseString);
        String name = jsonPath.getString("name");
        assertEquals(placeName, name);
    }

    private AddPlace createPlace(final String name, final String language, final String address) {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setName(name);
        addPlace.setPhone_number("(02) 9374 4000");
        addPlace.setWebsite("http://www.google.com.au/");
        addPlace.setTypes(List.of("shoe_store"));
        Location location = new Location();
        location.setLat(-33.866971);
        location.setLng(151.195875);
        addPlace.setLocation(location);
        return addPlace;
    }
}
