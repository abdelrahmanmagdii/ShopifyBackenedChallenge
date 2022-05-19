
Feature: Product Test
  @smoke1
  Scenario Outline: Get product test
    Given  "<sku>" of a product
    When request to get the product
    Then status_code equals <statusCode>
    And  product name is "<name>"
    Examples:
      |sku   |  statusCode |  name                    |
      | 11	 |  200        |  XYZ Headphones          |
      | 28   |  200        |  My Mechanical Keyboard  |