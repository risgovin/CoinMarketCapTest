@selenium
Feature: Selenium Tests

    @selenium1
    Scenario: View 100 Rows
        Given I am a browser user
        And I navigate to "https://coinmarketcap.com"
        When I click on the element "//div[text()='100']"
        And I click on the element "//button[text()='100']"
        Then There should be 101 occurences of "//tr"

    @selenium2
    Scenario: view market cap and price filters
        Given I am a browser user
        And I navigate to "https://coinmarketcap.com"
        When I click on the element "//button[text()='Filters']"
        And I click on the element "//li/button[last()]"
        And I click on the element "//button[text()='Market Cap']"
        And I click on the element "//button[text()='$1B - $10B']"
        And I click on the element "//button[text()='Apply Filter']"
        And I click on the element "//button[text()='Price']"
        And I click on the element "//button[text()='$101 - $1,000']"
        And I click on the element "//button[text()='Apply Filter']"
        And I click on the element "//button[text()='Show results']"
        And I wait 10 seconds
        Then the following elements should be in "//table/tbody/tr/td[3]/div/a/div/div/p"
            | Aave        |
            | Monero      |
            | Bitcoin SV  |
            | Kusama      |
            | Compound    |
            | Dash        |
            | Elrond      |
            | Decred      |
            | Zcash       |
            | Wrapped BNB |
            | Counos X    |
            | Horizen     | 