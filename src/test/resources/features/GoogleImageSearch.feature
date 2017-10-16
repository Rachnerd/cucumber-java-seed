Feature: GoogleImageSearchFeature

  Background:
    Given I'm at the google-image-search page

  Scenario Outline: User searches for images of fruit
    When I enter <query> in the search bar
      And I submit the form
    Then I take a screenshot: image-search/<query>

  Examples:
  | query |
  | Bananas |
  | Oranges |
  | Kiwis |

