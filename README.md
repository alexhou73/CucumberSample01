**CucumberSample01**<br>
**CAUTION**
It's Demo project only 30% completed yet but can work well as a simple Demo.<br>

This demo project defined a Web UI test framekwork using Java, Selenium and Cucumber. The target is to show how to create cucumber test framework and test cases step by step. <br>

**WebDriver Managerment**: Completed.<br>
**SearchWith**: Completd.<br>
**Multiple Page Objects**: In progress<br>
**Cucumber Test Cases**: In progress<br>


*Settings*<br>
Make sure you need prepare the latest version of ChromeDriver is in correct path before you try this demo:<br>
*Path: src/main/resources/environment.properties*
```
Default_Chrome_Driver=/Users/alex/env/chromedriver/chromedriver
URL=https://bonigarcia.dev/selenium-webdriver-java/
```
*Test*<br>
mvn clean test<br>
OR<br>
mvn clean test -DURL=https://bonigarcia.dev/selenium-webdriver-java/ -Dwebdriver.chrome.driver=<web_driver_path>
