Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Today either is or is not Friday
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<answer>"

  Examples:
    | day             | answer    |
    | Friday          | TGIF      |
    | Monday          | Nope      |
    | Sunday          | Nope      |
    | anything else!  | Nope      |
