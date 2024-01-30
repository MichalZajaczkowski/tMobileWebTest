Feature: Selecting a device from the list of offers

  Scenario: Open the T-Mobile homepage, select a device, and add it to the basket.
    Given I open the T-Mobile homepage
    And I accept cookies if they appear
    When I go to Devices from the top bar
    And I select "No subscription" from the 'Smartwatches and Wristbands' column
    And I click on the first item from the list
    And I get the prices of the product
    And I add the device to the basket
    Then I get the prices of the product after adding to the basket
    And I compare prices before and after adding to the basket
    And I return to the T-Mobile home page
    And I check and compare the basket after adding the product
