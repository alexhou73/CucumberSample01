package org.example.commons.selenium;

import org.apache.commons.lang3.StringUtils;
import org.example.commons.Util;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class ByFactory {
    private final static Logger logger = LoggerFactory.getLogger(ByFactory.class);

    private static class LocatorInfo {
        private String locator;
        private Locator locateUsing;

        public LocatorInfo(String locator, String locateUsing) {
            this.locator = locator;
            switch (locateUsing.toLowerCase()) {
                case "id":
                    this.locateUsing = Locator.id;
                    break;
                case "name":
                    this.locateUsing = Locator.name;
                    break;
                case "classname":
                    this.locateUsing = Locator.className;
                    break;
                case "css":
                    this.locateUsing = Locator.css;
                    break;
                case "tagname":
                    this.locateUsing = Locator.tagName;
                    break;
                case "linktext":
                    this.locateUsing = Locator.linkText;
                    break;
                case "partiallinktext":
                    this.locateUsing = Locator.partialLinkText;
                    break;
                case "xpath":
                    this.locateUsing = Locator.xpath;
                    break;
                default:
                    break;
            }
        }

        public String getLocator() {
            return locator;
        }

        public Locator getLocateUsing() {
            return locateUsing;
        }
    }

    private static String locatorFileName;
    public static HashMap<String,LocatorInfo> locators = new HashMap<>();
    public static JsonArray locatorArray = new JsonArray();


    public static void setLocatorFileName(String fileName) {
        if(!locatorArray.isJsonNull()){
            locatorArray = new JsonArray();
        }
        logger.info("Setting locator file: {}." + fileName);
        locatorFileName = fileName;
        File file = Util.getCurrentThreadResource(locatorFileName);
        Preconditions.checkArgument(file.exists(), "Unable to locate " + locatorFileName);
        try {
            locatorArray = JsonParser.parseReader(new FileReader(file)).getAsJsonArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getLocatorFileName() {
        return locatorFileName;
    }

    public static By createBy(String pageName, String elementName, String... replaceValues) {
        return getBy(getLocator(pageName, elementName, replaceValues));
    }

    private static By getBy(LocatorInfo locatorInfo) {
        switch (locatorInfo.locateUsing) {
            case id:
                return new By.ById(locatorInfo.getLocator());
            case name:
                return new By.ByName(locatorInfo.getLocator());
            case className:
                return new By.ByClassName(locatorInfo.getLocator());
            case css:
                return new By.ByCssSelector(locatorInfo.getLocator());
            case tagName:
                return new By.ByTagName(locatorInfo.getLocator());
            case linkText:
                return new By.ByLinkText(locatorInfo.getLocator());
            case partialLinkText:
                return new By.ByPartialLinkText(locatorInfo.getLocator());
            case xpath:
                return new By.ByXPath(locatorInfo.getLocator());
            default:
                throw new UnsupportedOperationException("The locator " + locatorInfo.getLocateUsing().name() + "is NOT Supported.");
        }
    }

    private static LocatorInfo getLocator(String pageName, String elementName, String... replaceValues) {
//        String key = pageName + "_" + elementName + "_" + String.join("_", replaceValues);
//        key = key.replaceAll("\\s","_");

        String key = String.join("_",pageName,elementName,String.join("_",replaceValues)).replaceAll(" ","_");
        logger.info("key: {}",key);
        if(locators.containsKey(key)){
            logger.info("Cache hit: {}!!!", key);
            return locators.get(key);
        }
        Preconditions.checkArgument(StringUtils.isNotEmpty(pageName));
        Preconditions.checkArgument(StringUtils.isNotEmpty(elementName));

//        File file = Util.getCurrentThreadResource(locatorFileName);
//        Preconditions.checkArgument(file.exists(), "Unable to locate " + locatorFileName);
        JsonObject foundOjbect = findElementByPageName(pageName, elementName);

        Preconditions.checkState(foundOjbect != null,
                "No entry found for the page[" + pageName + "] and element[" + elementName +
                        "] in the locators file [" + locatorFileName + "]");
        String locatorUsing = foundOjbect.get("locateUsing").getAsString();
        String locator = foundOjbect.get("locator").getAsString();
        if (replaceValues != null && replaceValues.length > 0) {
            locator = String.format(locator, replaceValues);
        }
        Preconditions.checkArgument(StringUtils.isNotEmpty(locator), "Locator cannot be null (or) empty.");
        LocatorInfo locatorInfo = new LocatorInfo(locator,locatorUsing);
        locators.put(key, locatorInfo);
        logger.info("Locator: {} created.",key);
        return locatorInfo;
    }

    private static JsonObject findElementByPageName(String pageName, String elementName) {
        for (JsonElement outerElement : locatorArray) {
            JsonObject jsonObject = outerElement.getAsJsonObject();
            if (pageName.equalsIgnoreCase(jsonObject.get("pageName").getAsString())) {
                JsonArray elements = jsonObject.get("elements").getAsJsonArray();
                for (JsonElement element : elements) {
                    JsonObject innerElement = element.getAsJsonObject();
                    if (elementName.equalsIgnoreCase(innerElement.get("elementName").getAsString())) {
                        return innerElement;
                    }
                }
            }
        }

        return null;
    }

}
