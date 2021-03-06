Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "ADD_A_PLACE" with "POST" HTTP method
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
#    And Verify the place id created maps to "<name>" using "GET_A_PLACE"
    Examples:
      |name | language | address
      |abc  | English  | Test address
#      |def  | German   | Test address1
