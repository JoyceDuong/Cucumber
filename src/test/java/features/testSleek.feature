@Sleek
Feature: Sleek Test Cases

	@Sceranio01
  Scenario: Clicking the Pricing link from the Header menu navigates user to the Pricing page
    Given I went to the Sleek SG Home page
    When I click on the Pricing link
    Then I should be navigated to the Sleek SG Pricing page
    And I quit browser

  @Sceranio02
    Scenario Outline: Correct corporate secretary details should display after updating accounting progress line
    Given I went to the Sleek SG Home page
    When I click on the Pricing link
    And I click on LEARN MORE  button for Corporate secretary
    And I click on accounting line by <No>
    Then Verify by <No> noShareholders with <noShareholders> and pricePerYear with <pricePerYear>
    And I quit browser
    
    Examples: Data Table
      |No | noShareholders     | pricePerYear | 
      | 1 | 2 Shareholders     | $360/year    | 
      | 2 | 6 - 9 Shareholders | $540/year    | 
      | 3 |> 30 Shareholders  | $1,140/year   |
    
    
    
    
    
    

