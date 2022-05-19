
Feature: Message Test
  @smoke1
  Scenario Outline: post process message
    Given message to send "<sendMsg>"
    And with prefix "<prefix>"
    When request is sent via post
    Then status_code equals <statusCode>
    And  response message equals "<responseMessage>"
    Examples:
      |sendMsg     	| prefix      | statusCode |  responseMessage      |
      | Java	  	| I Love      |  200      |  I Love Java          |
      | Cucumber 	| I Love      |  200      |  I Love Cucumber      |