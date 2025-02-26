package org.example.pages;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class HomePage extends BasePage {
    private static final String HOME_URL = "url";
    protected final static String homepageURL =
            StringUtils.isNotEmpty(System.getProperty(HOME_URL))
                    ? System.getProperty(HOME_URL)
                    : Environment.INSTANCE.getPropertyByExactKey(HOME_URL);

    public HomePage() {
        driver.get(homepageURL);
    }

    public static WebElement getPageHeader(String header) {
        Optional<WebElement> pageHeader = Optional.ofNullable(
                driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]")));
        return pageHeader.isPresent() ? pageHeader.get() : null;
    }

    public static String getPageTitle() {
        return driver.getTitle();
    }

    public static WebElement getButtonLink(String name) {
        Optional<WebElement> buttonLink = Optional.ofNullable(
                driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]")));
        return buttonLink.isPresent() ? buttonLink.get() : null;
    }

}
