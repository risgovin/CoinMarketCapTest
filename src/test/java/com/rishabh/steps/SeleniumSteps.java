package com.rishabh.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
public class SeleniumSteps {

    private static WebDriver driver;
    
    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        
    }
    @After
    public void tearDown()
    {
        if(driver != null)
            driver.quit();
    }
    /**
     * initialises driver as a chrome driver
     */
    @Given("I am a browser user")
    public void setupWebDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * goes to specified URL
     * @param url
     */
    @Given("I navigate to {string}")
    public void gotoPage(String url) {
        driver.get(url);
    }
    /**
     * clicks on element by xpath
     * @param xpath
     */
    @When("I click on the element {string}")
    public void clickxpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }
    /**
     * explicit wait in seconds
     * @param seconds
     * @throws InterruptedException
     */
    @When("I wait {int} seconds")
    public void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }
    /**
     * asserts count of elements by xpath
     * @param count
     * @param xpath
     */
    @Then("There should be {int} occurences of {string}")
    public void countOccurences(int count, String xpath) {
        assertEquals(count, driver.findElements(By.xpath(xpath)).size());
    }
    /**
     * asserts that a list of elements' text matches a list of Values (Order Agnostic)
     * @param xpath
     * @param names
     */
    @Then("the following elements should be in {string}")
    public void verifyListofElements(String xpath, List<String> names) {
        List<WebElement> elementList = driver.findElements(By.xpath(xpath)); 
        List<String> elementsText = new ArrayList<String>();
        for(WebElement element : elementList)
        {
            elementsText.add(element.getText());
        }
        assertTrue("expected: " +names.toString() 
                   + " Actual: " + elementsText.toString(), elementsText.size() == names.size() 
                                                            && elementsText.containsAll(names)
                                                            && names.containsAll(elementsText)); 
    }
}