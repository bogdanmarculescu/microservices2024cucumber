Feature: Both test endpoints do stuff

  Scenario Outline: User tests endpoint
    Given a new user "<name>"
    When he calls the "<endpoint>" endpoint to test
    Then he gets a test outcome with id <id>

    Examples:
      | name    | endpoint        | id      |
      | Bogdan  | test            | 42      |
      | Shepard | ongoing         | 42      |
      | Bogdan2 | anythingelse!   | 42      |