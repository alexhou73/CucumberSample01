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

    public boolean userOnPage(String header) {
        Optional<WebElement> pageHeader1 = Optional.ofNullable(
                driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]")));
        return pageHeader1.isPresent();
    }

    public WebElement getWebElementById(String id) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.id(id)));
        return webElement.isPresent() ? webElement.get() : null;
    }

    public WebElement getWebElementByName(String name) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.name(name)));
        return webElement.isPresent() ? webElement.get() : null;
    }

    public WebElement getWebElementByXpath(String xpath) {
        Optional<WebElement> webElement = Optional.ofNullable(
                driver.findElement(By.xpath(xpath)));
        return webElement.isPresent() ? webElement.get() : null;
    }

    public boolean enterInTextBoxById(String id, String text) {
        Optional<WebElement> textBox = Optional.ofNullable(getWebElementById(id));
        if (textBox.isPresent()) {
            textBox.get().sendKeys(text);
            return true;
        }
        return false;
    }

    public boolean enterInTextBoxByName(String name, String password) {
        Optional<WebElement> textBox = Optional.ofNullable(getWebElementByName(name));
        if (textBox.isPresent()) {
            textBox.get().sendKeys(password);
            return true;
        }
        return false;
    }

    public boolean verifyRadioButtonById(String id) {
        Optional<WebElement> radioButton = Optional.ofNullable(getWebElementById(id));
        if (radioButton.isPresent()) {
            return radioButton.get().isSelected();
        }
        return false;
    }

    public boolean verifyRadioButtonByName(String name) {
        Optional<WebElement> radioButton = Optional.ofNullable(getWebElementByName(name));
        if (radioButton.isPresent()) {
            return radioButton.get().isSelected();
        }
        return false;
    }

    public boolean verifyCheckBoxById(String id) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementById(id));
        if (checkbox.isPresent()) {
            return checkbox.get().isSelected();
        }
        return false;
    }

    public boolean verifyCheckBoxByName(String name) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementByName(name));
        if (checkbox.isPresent()) {
            return checkbox.get().isSelected();
        }
        return false;
    }

    public boolean verifyTextBoxById(String id, String text) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementById(id));
        if (checkbox.isPresent()) {
            return checkbox.get().getText().equals(text);
        }
        return false;
    }

    public boolean verifyTextBoxByName(String name, String text) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementByName(name));
        if (checkbox.isPresent()) {
            return checkbox.get().getText().equals(text);
        }
        return false;
    }

    public boolean verifyTextBoxById(String id, String text, boolean ignoreCase) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementById(id));
        if (checkbox.isPresent()) {
            if (ignoreCase) {
                return checkbox.get().getText().equalsIgnoreCase(text);
            }
            return checkbox.get().getText().equals(text);
        }
        return false;
    }

    public boolean verifyTextBoxByName(String name, String text, boolean ignoreCase) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementByName(name));
        if (checkbox.isPresent()) {
            if (ignoreCase) {
                return checkbox.get().getText().equalsIgnoreCase(text);
            }
            return checkbox.get().getText().equals(text);
        }
        return false;
    }

    public boolean verifyEnabledTextBoxById(String id) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementById(id));
        if (checkbox.isPresent()) {
            return checkbox.get().isEnabled();
        }
        return false;
    }

    public boolean verifyEnabledTextBoxByName(String name) {
        Optional<WebElement> checkbox = Optional.ofNullable(getWebElementByName(name));
        if (checkbox.isPresent()) {
            return checkbox.get().isEnabled();
        }
        return false;
    }

}
