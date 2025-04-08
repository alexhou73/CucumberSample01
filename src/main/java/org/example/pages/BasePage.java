package org.example.pages;

import org.example.commons.selenium.ByFactory;
import org.example.commons.selenium.LocatorUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class BasePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String PAGE = "HomePage";

    protected WebDriver driver;
    protected WebDriverWait shortWait = null;
    protected WebDriverWait wait = null;
    protected LocatorUtil locatorUtil = new LocatorUtil("src/main/resources/locators/locators-common.json");

    public BasePage() {
    }


    public WebElement getWebElementBy(By by) {
        Optional<WebElement> webElement = Optional.ofNullable(driver.findElement(by));
        return webElement.isPresent() ? webElement.get() : null;
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

    public void setDropdownListByIndex(String name, int index) {
        Optional<Select> dropdownList = Optional.ofNullable(new Select(getWebElementByName(name)));
        if (dropdownList.isPresent()) {
            dropdownList.get().selectByIndex(index);
        }
    }

    public void setDataListByIndex(String name, String dataListId, Integer index) {
        Optional<WebElement> selectInput = Optional.ofNullable(getWebElementByName(name));
        By byOption = ByFactory.createBy(PAGE, "DataListById_Index", dataListId, String.valueOf(index));
        Optional<WebElement> option = Optional.ofNullable(getWebElementBy(byOption));
        if (selectInput.isPresent() && option.isPresent()) {
            selectInput.get().clear();
            selectInput.get().sendKeys(option.get().getAttribute("value"));
        }
    }

    public void uploadFile(String name, String fileName) {
        WebElement fileInput = getWebElementByName(name);
        fileInput.sendKeys(fileName);
    }

    public void setColor(String name, String value) {
        WebElement colorPicker = getWebElementByName(name);
        colorPicker.sendKeys(value);
    }

    public void datePicker(String name, String value) {
        WebElement datePicker = getWebElementByName(name);
        datePicker.sendKeys(value);
    }

    public void setSlider(String name, double value) {
        WebElement slider = getWebElementByName(name);
        logger.info("Range min:{}, max:{}", slider.getAttribute("min"), slider.getAttribute("max"));
        logger.info("Range step:{}, value: {}", slider.getAttribute("step"), slider.getAttribute("value"));
        logger.info("Setting value {} to range:");
        int width = slider.getSize().getWidth();
        int x = width / 2;
        int newx = (int) (width * value);
        logger.info("Slider with: {}, half:{}", width, width / 2);
        new Actions(driver).dragAndDropBy(slider, x, 0).dragAndDropBy(slider, -newx, 0).perform();
        logger.info("Range value: {}", slider.getAttribute("value"));
    }

    public void clickButton(String name) {
        By byButton = ByFactory.createBy(PAGE, "ButtonByText", name);
        Optional<WebElement> button = Optional.ofNullable(getWebElementBy(byButton));
        if (button.isPresent()) {
            button.get().click();
        }
    }
}
