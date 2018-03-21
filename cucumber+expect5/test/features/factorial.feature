Feature: Factorial computation

  Scenario: Compute factorial for natural numbers
    Given The function factorial is callable
    When I try to compute 2!
    Then I should get result 2
    When I try to compute 3!
    Then I should get result 6
    When I try to compute 10!
    Then I should get result 3628800


  Scenario Outline: Compute more factorials for natural numbers
    Given The function factorial is callable
    When I try to compute <n>!
    Then I should get result <result>

    Examples:
        |  n |  result |
        |  1 |       1 |
        |  2 |       2 |
        |  3 |       6 |
        |  4 |      24 |
        | 10 | 3628800 |


  Scenario: Compute factorial for negative numbers
    Given The function factorial is callable
    When I try to compute -1!
    Then the IllegalArgumentException should be thrown


  Scenario Outline: Compute more factorials for negative numbers
    Given The function factorial is callable
    When I try to compute <n>!
    Then the IllegalArgumentException should be thrown

    Examples:
        | n |
        |-1 |
        |-2 |
        |-3 |
        |-1000 |
