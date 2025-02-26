package org.example.pages;

import org.example.commons.WebDrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static WebDriver driver = WebDrivers.getChromeDriver();

    public static boolean userOnPage(String header) {
        Optional<WebElement> pageHeader1 = Optional.ofNullable(
                driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]")));
        return pageHeader1.isPresent();
    }

    public static WebElement getWebElementById(String id) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.id(id)));
        return webElement.isPresent() ? webElement.get() : null;
    }

    public static WebElement getWebElementByName(String name) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.name(name)));
        return webElement.isPresent() ? webElement.get() : null;
    }

    public static WebElement getWebElementByXpath(String xpath) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.xpath(xpath)));
        return webElement.isPresent() ? webElement.get() : null;
    }

}
