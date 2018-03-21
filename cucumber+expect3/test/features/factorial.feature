Feature: Factorial computation

  Scenario: Compute factorial for natural numbers
    Given The function factorial is callable
    When I try to compute 2!
    Then I should get result 2
    When I try to compute 3!
    Then I should get result 6
    When I try to compute 10!
    Then I should get result 3628800


  Scenario: Compute factorial for negative numbers
    Given The function factorial is callable
    When I try to compute -1!
    Then the IllegalArgumentException should be thrown
