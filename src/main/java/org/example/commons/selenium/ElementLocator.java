package org.example.commons.selenium;

public class ElementLocator {
    private String elementName;
    private String locateUsing;
    private String locator;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getLocateUsing() {
        return locateUsing;
    }

    public void setLocateUsing(String locateUsing) {
        this.locateUsing = locateUsing;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }
}
