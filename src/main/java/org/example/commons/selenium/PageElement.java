package org.example.commons.selenium;

import java.util.List;

public class PageElement {

    private String pageName;
    private List<ElementLocator> elements;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<ElementLocator> getElements() {
        return elements;
    }

    public void setElements(List<ElementLocator> elements) {
        this.elements = elements;
    }
}
