
Feature: Warehouse Test
  @smoke1
  Scenario Outline: Get Warehouse test
    Given  "<id>" of a warehouse
    When request to get the warehouse
    Then status_code equals <statusCode>
    And  warehouse name is "<name>"
    Examples:
      | id   |  statusCode |  name                    |
      | 13	 |  200        |  Canadian Warehouse      |
      | 20   |  200        |  Mexican Warehouse       |