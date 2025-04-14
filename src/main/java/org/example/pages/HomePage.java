package org.example.pages;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Environment;
import org.example.commons.selenium.ByFactory;
import org.example.commons.selenium.SearchWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private Logger logger = LoggerFactory.getLogger(HomePage.class);
    private static final String HOME_URL = "URL";
    private static final String PAGE = "HomePage";
    protected final static String homepageURL =
            StringUtils.isNotEmpty(System.getProperty(HOME_URL))
                    ? System.getProperty(HOME_URL)
                    : Environment.INSTANCE.getPropertyByExactKey(HOME_URL);
    @SearchWith(pageName = PAGE, elementName = "Title")
    WebElement PageTitle;

    public HomePage(WebDriver webDriver) {
        driver = webDriver;
        driver.get(homepageURL);
        searchWithHandler.initElement(driver, this);
        wait = new WebDriverWait(driver, 600, 5000);
        logger.info("PageTitle: {}", PageTitle.getText());
    }


    public boolean userOnPage(String header) {
        By byH1 = ByFactory.createBy(PAGE, "H1ByText", header);
        WebElement header1 = wait.until(ExpectedConditions.presenceOfElementLocated(byH1));
        return header1.isEnabled();
    }

    public String getPageHeader(String header) {

        By byH1 = ByFactory.createBy(PAGE, "H1ByText", header);
        WebElement header1 = wait.until(ExpectedConditions.presenceOfElementLocated(byH1));
        return header1.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement getButtonLink(String name) {
        By byAnchor = ByFactory.createBy(PAGE, "AnchorByText", name);
        WebElement anchor = wait.until(ExpectedConditions.presenceOfElementLocated(byAnchor));
        return anchor;
    }

    public void clickButtonLink(String name) {
        By byAnchor = ByFactory.createBy(PAGE, "AnchorByText", name);
        wait.until(ExpectedConditions.presenceOfElementLocated(byAnchor)).click();
    }


}
