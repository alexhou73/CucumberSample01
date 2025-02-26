package org.example.commons;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class WebDrivers {
    private static Logger logger = LoggerFactory.getLogger(WebDrivers.class);
    private static final String CHROME_DRIVER_EXECUTABLE_KEY = "webdriver.chrome.driver";
    protected final static String CHROME_DRIVER_EXECUTABLE =
            StringUtils.isNotEmpty(System.getProperty(CHROME_DRIVER_EXECUTABLE_KEY))
                    ? System.getProperty(CHROME_DRIVER_EXECUTABLE_KEY)
                    : Environment.INSTANCE.getPropertyByExactKey("Default_Chrome_Driver");
    public static WebDriver chromeDriver = getChromeDriver(true);

    public static WebDriver getChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--enable-automation");
        File f = new File(CHROME_DRIVER_EXECUTABLE);
        if (!f.exists()) {
            WebDriverManager.chromedriver().setup();
        } else {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_EXECUTABLE);
        }
        try {
            chromeDriver = new ChromeDriver(chromeOptions);
        } catch (SessionNotCreatedException sessionNotCreatedException) {
            logger.error("SessionNotCreatedExecption raised: {}", sessionNotCreatedException.getMessage());
            logger.info("Try again to download the latest version...");
            chromeDriver = getChromeDriver(true);
        }
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return chromeDriver;
    }


    public static WebDriver getChromeDriver(boolean download) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--enable-automation");
        if (download) {
            WebDriverManager.chromedriver().setup();
        }
        chromeDriver = new ChromeDriver(chromeOptions);
        return chromeDriver;
    }
}
