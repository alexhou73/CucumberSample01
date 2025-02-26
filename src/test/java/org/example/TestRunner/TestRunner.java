package org.example.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"org.example.StepDefinitions"},
        tags = "@Smoke or @General or @Special",
        monochrome = true,
        plugin = {"pretty", "html:target/html/testreport.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
