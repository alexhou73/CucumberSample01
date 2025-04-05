package org.example.commons.selenium;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LocatorUtil {
    private List<PageElement> pageElements;
    private List<ElementLocator> elementLocators;

    public LocatorUtil(String jsonFilePath){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            pageElements = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<PageElement>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PageElement getPageElementByName(String pageName){
        return pageElements.stream()
                .filter(locator->locator.getPageName().equals(pageName))
                .findFirst()
                .orElse(null);
    }

    public ElementLocator getElementLocatorByName(PageElement pageElement, String elementName){
        return pageElement.getElements().stream()
                .filter(e->e.getElementName().equals(elementName))
                .findFirst()
                .orElse(null);
    }
}
