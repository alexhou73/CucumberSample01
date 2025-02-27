package org.example.pages;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Optional;

public class HomePage extends BasePage {
    private static final String HOME_URL = "url";
    protected final static String homepageURL =
            StringUtils.isNotEmpty(System.getProperty(HOME_URL))
                    ? System.getProperty(HOME_URL)
                    : Environment.INSTANCE.getPropertyByExactKey(HOME_URL);

    public HomePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public static String getPageHeader(String header) {
        Optional<WebElement> pageHeader = Optional.ofNullable(
                driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]")));
        return pageHeader.isPresent() ? pageHeader.get().getText() : "";
    }

    public static String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement getButtonLink(String name) {
        Optional<WebElement> buttonLink = Optional.ofNullable(
                driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]")));
        return buttonLink.isPresent() ? buttonLink.get() : null;
    }

    public boolean clickButtonLink(String name) {
        Optional<WebElement> buttonLink = Optional.ofNullable(
                driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]")));
        if (buttonLink.isPresent()) {
            buttonLink.get().click();
            return true;
        }
        return false;
    }


}
