Feature: GitHub API tests

  @smoketest
  Scenario: Check the GitHub API entry point
    Given GitHub is accessible
    When I access the API endpoint /
    Then I should receive response with 200 status code

  Scenario: Check the user search feature
    Given GitHub is accessible
    When I search for user with nick torvalds
    Then I should receive response with 200 status code
     And I should receive proper JSON response
     And I should find the user with full name Linus Torvalds
     And I should find that the user works for company Linux Foundation

