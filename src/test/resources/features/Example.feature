Feature: Example

  Background:
    Given I'm at the example page

  Scenario: User logs in with wrong credentials

    When I submit the following query: What is google?
    Then I see the results of the query
