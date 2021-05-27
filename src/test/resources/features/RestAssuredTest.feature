@restassured
Feature: Rest Assured Test

    @rest1
    Scenario Outline: Price of currency in Bolivian Boliviano
        Given I am a cmc pro api consumer
        And I set the header "X-CMC_PRO_API_KEY" as "f6022717-1702-4c61-8343-6c97738f2166"
        And I set the query parameters as follows:
            | symbol | <Symbol> |
        When I request "GET" for "/cryptocurrency/map"
        Then the status code is 200
        Then I save "data[0].id" as "id"
        #use ID Saved above to convert currency
        Given I am a cmc pro api consumer
        And I set the header "X-CMC_PRO_API_KEY" as "f6022717-1702-4c61-8343-6c97738f2166"
        And I set the query parameters as follows:
            | id      | {{id}}     |
            | convert | <Currency> |
            | amount  | 1          |
        When I request "GET" for "/tools/price-conversion"
        Then I output "data.quote.<Currency>.price" to the console

        Examples:
            | Symbol | Currency |
            | BTC    | BOB      |
            | USDT   | BOB      |
            | ETH    | BOB      |
    @rest2
    Scenario: Ethereum Validation
        Given I am a cmc pro api consumer
        And I set the header "X-CMC_PRO_API_KEY" as "f6022717-1702-4c61-8343-6c97738f2166"
        And I set the query parameters as follows:
            | id | 1027 |
        When I request "GET" for "/cryptocurrency/info"
        Then The element "data.1027.logo" should be "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png"
        Then The element "data.1027.urls.technical_doc" should be "[https://github.com/ethereum/wiki/wiki/White-Paper]"
        Then The element "data.1027.symbol" should be "ETH"
        Then The element "data.1027.date_added" should be "2015-08-07T00:00:00.000Z"
        Then The element "data.1027.tags" should contain "mineable"
    @rest3
    Scenario Outline: Check if Cryptocurrency is Mineable
        Given I am a cmc pro api consumer
        And I set the header "X-CMC_PRO_API_KEY" as "f6022717-1702-4c61-8343-6c97738f2166"
        And I set the query parameters as follows:
            | id | <id> |
        When I request "GET" for "/cryptocurrency/info"
        Then The element "data.<id>.tags" <ShouldOrNot> contain "mineable"

        Examples:
            | id | ShouldOrNot |
            | 1  | should      |
            | 2  | should      |
            | 3  | should      |
            | 4  | should      |
            | 5  | should      |
            | 6  | should      |
            | 7  | should      |
            | 8  | should      |
            | 9  | should      |
            | 10 | should      |