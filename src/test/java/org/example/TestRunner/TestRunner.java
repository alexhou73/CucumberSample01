package org.example.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"org.example.StepDefinitions"},
        tags = "@Special",
        monochrome = true,
        dryRun = false,
        plugin = {"pretty", "html:target/html/testreport.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
