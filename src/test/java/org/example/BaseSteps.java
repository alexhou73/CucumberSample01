package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Environment;
import org.example.commons.WebDrivers;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseSteps {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver = WebDrivers.getChromeDriver();

    protected static final String url = StringUtils.isNotEmpty(System.getProperty("URL"))
            ? System.getProperty("URL")
            : Environment.INSTANCE.getPropertyByExactKey("URL");
    protected SoftAssert softAssert = new SoftAssert();

}
