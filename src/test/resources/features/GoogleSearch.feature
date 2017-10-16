Feature: GoogleSearchFeature

  Background:
    Given I'm at the google-search page

  Scenario Outline: User searches for fruits
      When I enter <query> in the search bar
        And I submit the form
      Then I see the google-result page
        And I take a screenshot: search/<query>

    Examples:
    | query |
    | Bananas |
    | Oranges |
    | Kiwis |
