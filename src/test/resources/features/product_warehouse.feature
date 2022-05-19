
Feature: Warehouse Test
  @smoke1
  Scenario Outline: Get Product_Warehouse test
    Given  "<id>" of a warehouse
    And "<sku>" of a product
    When request to get the product_warehouse
    Then status_code equals <statusCode>
    And  product_warehouse quantity is "<quantity>"
    Examples:
      | id   |  sku     | statusCode |  quantity     |
      | 20	 |   11     | 200      |       5       |
      | 20   |   66     |200      |       5       |