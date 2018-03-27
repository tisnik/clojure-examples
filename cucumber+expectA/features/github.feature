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

  Scenario Outline: Check the user search feature, perform the search for more users
    Given GitHub is accessible
    When I search for user with nick <nick>
    Then I should receive response with 200 status code
     And I should receive proper JSON response
     And I should find the user with full name <fullname>
     And I should find that the user works for company <company>

     Examples: users
     |nick|fullname|company|
     |torvalds|Linus Torvalds|Linux Foundation|
     |brammool|Bram Moolenaar|Zimbu Labs|
     |tisnik|Pavel Tišnovský|Red Hat, Inc.|

