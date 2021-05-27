# CoinMarketCap FrontEnd and Backend Tests
## Created by Rishabh Govindraj

#### to run all tests
>mvn clean test

#### to run front end tests
>mvn clean test -Dcucumber.filter.tags="@selenium"

#### to run back end tests
>mvn clean test -Dcucumber.filter.tags="@restassured"