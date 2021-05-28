@CalculateTheTotal
Feature: Calculate the total of all wire transfers and cheque amounts

@Scenario1
  Scenario: Calculate total amount of all wire transfers and cheque amounts
    Given I login to system
    When I go to orders history
    Then I calculate the total by going through each row and totaling them individually based on the payment type

