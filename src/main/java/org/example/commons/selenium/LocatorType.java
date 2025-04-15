package org.example.commons.selenium;

public enum LocatorType {
    id,
    name,
    className,
    css,
    tagName,
    linkText,
    partialLinkText,
    xpath;

    public static LocatorType convertLocatorType(String locateUsing) {
        switch (locateUsing){
            case "id":
                return id;
            case "name":
                return name;
            case "className":
                return className;
            case "css":
                return css;
            case "tagName":
                return tagName;
            case "linkText":
                return linkText;
            case "partialLinkText":
                return partialLinkText;
            case "xpath":
                return xpath;
            default:
                throw new UnsupportedOperationException("The locator '" + locateUsing + "' is NOT Supported.");
        }
    }
}
