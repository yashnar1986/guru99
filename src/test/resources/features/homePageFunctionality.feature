#Author: yashnarkhanazar@gmail.com
@regression
Feature: Verify home page functionality

  Background: Navigating to the Home page
    Given I am in the home page

  @assingment1
  Scenario Outline: Count of topics in home page
    When I check following "<section>" in Tutorials Library
    Then I should be able to verify the topics <count> in that "<section>"

    Examples: 
      | section     | count |
      | Testing     |     9 |
      | Must Learn! |    30 |

  @assingment2
  Scenario: Count results of search field
    Then I start typing to the search box and press the enter and save the count of result in the excel doc
  
