package com.rishabh;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features"}, 
    plugin = {"pretty","json:target/cucumber.json"},
    tags = "not @ignore",
    glue = {"com.rishabh.steps"}
)
public class RunCucumberTest {
    
}
