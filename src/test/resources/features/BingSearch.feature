Feature: BingSearchFeature

  Background:
    Given I'm at the bing-search page

  Scenario Outline: User searches for fruits
    When I enter <query> in the search bar
      And I submit the form
    Then I see the bing-result page
      And I take a screenshot: bing-search/<query>

  Examples:
  | query |
  | Bananas |
  | Oranges |
  | Kiwis |
