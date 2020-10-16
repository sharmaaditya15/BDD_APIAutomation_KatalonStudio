Feature: Display list of users and fetch the user from ListUsersAPI and update it via UpdateUserAPI

  Scenario Outline: Verify the user information and response code
    Given I get the user information
    Then The email of one of the users is <email>
    And The response code is <responseCode>

    Examples: 
      | email                    | responseCode |
      | george.edwards@reqres.in |          200 |

  Scenario Outline: Update the user information and verify the response code
    Given I have the user information fetched from ListUsersAPI
    When I update the user information through UpdateUserAPI
    Then The response code is <responseCode>

    Examples: 
      | responseCode |
      |          200 |
