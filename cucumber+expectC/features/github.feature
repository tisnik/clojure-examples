Feature: GitHub API tests

  @smoketest
  Scenario: Check the GitHub API entry point
    Given REST API for GitHub service is accessible on URL https://api.github.com
    When I access the API endpoint /
    Then I should receive response with 200 status code

  Scenario: Check the user search feature
    Given REST API for GitHub service is accessible on URL https://api.github.com
    When I search for user with nick torvalds
    Then I should receive response with 200 status code
     And I should receive proper JSON response
     And I should find the user with full name Linus Torvalds
     And I should find that the user works for company Linux Foundation

  Scenario Outline: Check the user search feature, perform the search for more users
    Given REST API for GitHub service is accessible on URL https://api.github.com
    When I search for user with nick <nick>
    Then I should receive response with 200 status code
     And I should receive proper JSON response
     And I should find the user with full name <fullname>
     And I should find that the user works for company <company>

     Examples: users
     |nick|fullname|company|
     |torvalds|Linus Torvalds|Linux Foundation|
     |brammool|Bram Moolenaar|Zimbu Labs|
     |richhickey|Rich Hickey||
     |tisnik|Pavel Tišnovský|Red Hat, Inc.|

