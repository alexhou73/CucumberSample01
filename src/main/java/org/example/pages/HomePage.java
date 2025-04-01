package org.example.pages;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Environment;
import org.example.commons.WebDrivers;
import org.example.commons.selenium.ByFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

public class HomePage extends BasePage {
    private static final String HOME_URL = "url";
    private static final String PAGE = "HomePage";
    protected final static String homepageURL =
            StringUtils.isNotEmpty(System.getProperty(HOME_URL))
                    ? System.getProperty(HOME_URL)
                    : Environment.INSTANCE.getPropertyByExactKey(HOME_URL);

    public HomePage(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(driver,600,5000);
    }


    public boolean userOnPage(String header) {
        By byH1 = ByFactory.createBy(PAGE,"H1Text", header);
        WebElement header1 = wait.until(ExpectedConditions.presenceOfElementLocated(byH1));
        return header1.isEnabled();
    }
    public String getPageHeader(String header) {
        By byH1 = ByFactory.createBy(PAGE, "H1Text", header);
        WebElement header1 = wait.until(ExpectedConditions.presenceOfElementLocated(byH1));
        return header1.getText();
    }

    public String getPageTitle() {
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
