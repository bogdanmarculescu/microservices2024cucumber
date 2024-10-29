Feature: The test endpoint does stuff

  Scenario: I call the endpoint and get back an outcome
    Given a new user Bogdan
    When he calls the any endpoint to test
    Then he gets a test outcome

  Scenario: Others call the endpoint
    Given a new user Kris
    When she calls the ongoing endpoint to test
    Then she gets a test outcome