Feature: CatFacts

  @BDD @BDD_CAT
  Scenario: Get random cat fact
    Given I get a random cat fact
    When the API responds to the get request
    Then it should respond with a 200 status code