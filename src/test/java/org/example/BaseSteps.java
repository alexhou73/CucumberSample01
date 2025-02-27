package org.example;

import org.example.commons.WebDrivers;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseSteps {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver = WebDrivers.getChromeDriver();
}
