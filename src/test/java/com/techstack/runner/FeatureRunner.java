package com.techstack.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:PlacesValidation.feature",
        glue = "classpath:com.techstack.stepdefinitions",
        plugin = "html:target/cucumber-reports.html",
        strict = true,
        tags = {"@AddPlace"}
)
public class FeatureRunner {
}
