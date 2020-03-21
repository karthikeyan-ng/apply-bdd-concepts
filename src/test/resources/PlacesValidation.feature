Feature: Validating Place APIs


  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with POST HTTP method
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Examples:
      |name | language | address
      |abc  | English  | Test address
      |def  | German   | Test address1
