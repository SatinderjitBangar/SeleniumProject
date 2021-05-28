@CheckIfUpperCase
Feature: Check that all Order references contain only letters and is capitalised

@Scenario1
  Scenario: Test that all Order references contain only letters and is capitalised
    Given User logIn to system
    When User goes to orders history
    Then I test that all Order references contain only letters and is capitalised

