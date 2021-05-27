package com.rishabh.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rishabh.context.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;

public class RestAssuredSteps {
    private TestContext testContext;
	private RequestSpecification request;
	private Response response;
    public RestAssuredSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    /**
    * initialises new request and sets base uri
    */
    @Given("I am a cmc pro api consumer")
    public void initrequest() {
        request = given();
        request.baseUri("https://pro-api.coinmarketcap.com/v1");
    }
    /**
     * sets a header
     * @param key
     * @param value
     */
    @Given("I set the header {string} as {string}")
    public void setHeader(String key, String value) {
        request.header(key, value);
    }
    /**
     * sets query parameters, checks for previously saved values denoted by {{var}}
     * @param parameters query parameters
     */
    @Given("I set the query parameters as follows:")
    public void setQueryParameters(Map<String, String> parameters) {
        Pattern pattern = Pattern.compile(".*\\{\\{(.*?)\\}\\}.*");
        for(Entry<String, String> query : parameters.entrySet())
        {
            String value = query.getValue();
            Matcher matcher = pattern.matcher(value);
            if(matcher.find())
                value = (String) testContext.get(matcher.group(1));
            request.queryParam(query.getKey(), value);
        }
        
    }
    /**
     * performs a REST api call for given method and path
     * @param method
     * @param path
     */
    @When("I request {string} for {string}")
    public void performRequest(String method, String path) {
        response = request.request(Method.valueOf(method), path);
    }
    /**
     * checks for status code from response
     * @param statusCode
     */
    @Then("the status code is {int}")
    public void checkStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }
    /**
     * saves a jsonPath value to testContext 
     * @param path
     * @param key
     */
    @Then("I save {string} as {string}")
    public void savePath(String path, String key) {
        testContext.put(key, response.body().jsonPath().getString(path));
    }
    /**
     * prints a jsonPath value to StdOut
     * @param path
     */
    @Then("I output {string} to the console")
    public void outputToConsole(String path) {
        System.out.println(response.body().jsonPath().getString(path));
    }
    /**
     * asserts a jsonpath value to an expected value
     * @param path
     * @param value
     */
    @Then("The element {string} should be {string}")
    public void elementShouldBe(String path, String value)
    {
        assertEquals(response.body().jsonPath().getString(path),value);
    }
    /**
     * checks if a jsonpath list contains a value
     * @param path
     * @param value
     */
    @Then("The element {string} should contain {string}")
    public void elementShouldContain(String path, String value)
    {
        assertTrue(response.body().jsonPath().getList(path).contains(value));
    }
    /**
     * checks if a jsonpath list does not contain a value
     * @param path
     * @param value
     */
    @Then("The element {string} should not contain {string}")
    public void elementShouldNotContain(String path, String value)
    {
        assertTrue(!response.body().jsonPath().getList(path).contains(value));
    }
}
