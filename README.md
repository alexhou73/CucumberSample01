**CucumberSample01**

*Settings*
Make sure the latest version of ChromeDriver is in correct path:

Path: src/main/resources/environment.properties

Default_Chrome_Driver=/Users/alex/env/chromedriver/chromedriver
URL=https://bonigarcia.dev/selenium-webdriver-java/

*Test*

mvn clean test

mvn clean test -DURL=https://bonigarcia.dev/selenium-webdriver-java/ -Dwebdriver.chrome.driver=/Users/alex/env/chromedriver/chromedriver

