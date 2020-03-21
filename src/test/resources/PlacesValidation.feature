Feature: Validating Place APIs


  Scenario: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with POST HTTP method
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"