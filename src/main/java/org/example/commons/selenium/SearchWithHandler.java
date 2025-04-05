package org.example.commons.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class SearchWithHandler {
    private LocatorUtil locatorUtil;
    private Logger logger = LoggerFactory.getLogger(SearchWith.class);

    public SearchWithHandler(LocatorUtil locatorUtil){
        this.locatorUtil = locatorUtil;
    }

    public void initElement(WebDriver webDriver, Object page){
        Class<?> clazz = page.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            if(field.isAnnotationPresent(SearchWith.class)){
                SearchWith searchWith = field.getAnnotation(SearchWith.class);
                PageElement pageElement = locatorUtil.getPageElementByName(searchWith.pageName());
                logger.info("PageElement Name: {}, elements: {}", pageElement.getPageName(), pageElement.getElements().size());
                if(pageElement!=null){
                    WebElement webElement = locateElement(webDriver, locatorUtil.getElementLocatorByName(pageElement,searchWith.elementName()));
                    logger.info("WebElement: {}", webElement.getText());
                    field.setAccessible(true);
                    try {
                        field.set(webDriver, webElement);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private WebElement locateElement(WebDriver driver, ElementLocator elementLocator) {
        switch (elementLocator.getLocateUsing()) {
            case "id":
                return driver.findElement(By.id(elementLocator.getLocator()));
            case "name":
                return driver.findElement(By.name(elementLocator.getLocator()));
            case "xpath":
                return driver.findElement(By.xpath(elementLocator.getLocator()));
            case "css":
                return driver.findElement(By.cssSelector(elementLocator.getLocator()));
            default:
                throw new IllegalArgumentException("Unknown locator type: " + elementLocator.getLocateUsing());
        }
    }

}
