package org.example.StepDefinitions;

import org.example.BaseSteps;
import org.example.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PracticeSteps extends BaseSteps {
    private static String url = "https://bonigarcia.dev/selenium-webdriver-java/";
    private SoftAssert softAssert = new SoftAssert();
    private HomePage homePage = new HomePage(driver);

    @Before
    public void beforeSuite() {
        driver.get(url);
    }

    @After
    public void afterSuite() {
        driver.quit();
//        softAssert.assertAll();
    }

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String header) {
        logger.info("the user is on the {} page", header);
        softAssert.assertTrue(homePage.getPageHeader(header).contains(header),
                String.format("Failed: Expected title should be '%s' but '%s'", header, driver.getTitle()));
    }

    @Given("the user clicks on {string} button")
    public void the_user_clicks_on_button(String button) {
        logger.info("the user clicks on {} button", button);
        softAssert.assertTrue(homePage.clickButtonLink(button),
                String.format("Failed: Web element '%s' not found", button));
    }

    @Given("the user on the {string} page")
    public void the_user_on_the_page(String header) {
        logger.info("the user on the {} page", header);
        softAssert.assertTrue(homePage.userOnPage(header),
                String.format("Failed: Web element %s not found", header));
    }

    @When("the user enters {string} in Text input field")
    public void the_user_enters_in_text_input_field(String text) {
        logger.info("the user enters {} in Text input field", text);
        softAssert.assertTrue(homePage.enterInTextBoxById("my-text-id", text),
                String.format("Failed: Web element id: %s not found", "my-text-id"));
    }

    @When("the user enters {string} in Password field")
    public void the_user_enters_in_password_field(String password) {
        logger.info("the user enters {} in Password field", password);
        softAssert.assertTrue(homePage.enterInTextBoxByName("my-password", password),
                String.format("Failed: Web element name: %s not found", password));
    }

    @When("the user enters {string} in Textareas field")
    public void the_user_enters_in_textares_field(String text) {
        logger.info("the user enters {string} in Textareas field", text);
        softAssert.assertTrue(homePage.enterInTextBoxByName("my-textarea", text),
                String.format("Failed: Web element name: %s not found", text));
    }

    @When("the user verifies the radio buttons")
    public void the_user_verifies_the_radio_button() {
        logger.info("the user verifies the radio buttons");
        softAssert.assertTrue(homePage.verifyRadioButtonById("my-radio-1"),
                String.format("Failed: Web element id: %s not found or value is not correct", "my-radio-1"));
        softAssert.assertFalse(homePage.verifyRadioButtonById("my-radio-2"),
                String.format("Failed: Web element id: %s not found or value is not correct", "my-radio-2"));
    }

    @When("the user verifies the checkboxes")
    public void the_user_verifies_the_checkboxes() {
        logger.info("the user verifies the checkboxes");
        softAssert.assertTrue(homePage.verifyCheckBoxById("my-check-1"),
                String.format("Failed: Web element id: %s not found or value is not correct", "my-check-1"));
        softAssert.assertFalse(homePage.verifyCheckBoxById("my-check-2"),
                String.format("Failed: Web element id: %s not found or value is not correct", "my-check-2"));

    }

    @When("verify the user interacts with the page")
    public void verify_the_user_interacts_with_the_page() throws InterruptedException {
        logger.info("verify the user interacts with the page");

        softAssert.assertFalse(homePage.verifyEnabledTextBoxByName("my-disabled"),
                String.format("Failed: expected:{}", "Disabled input"));
        softAssert.assertTrue(homePage.verifyEnabledTextBoxByName("my-readonly"),
                String.format("Failed: expected:{}", "Readonly input"));

        Select dropdownList = new Select(homePage.getWebElementByName("my-select"));
        dropdownList.selectByIndex(2);

        WebElement selectInput = homePage.getWebElementByName("my-datalist");
        WebElement option = homePage.getWebElementByXpath("//datalist[@id='my-options']/option[1]");
        selectInput.clear();
        selectInput.sendKeys(option.getAttribute("value"));

        WebElement fileInput = homePage.getWebElementByName("my-file");
        fileInput.sendKeys("/Users/alex/soapui-settings.xml");


        WebElement colorPicker = homePage.getWebElementByName("my-colors");
        logger.info("Color Picker value: {}", colorPicker.getAttribute("value"));
        colorPicker.sendKeys("#ff0000");

        WebElement datePicker = homePage.getWebElementByName("my-date");
        datePicker.sendKeys("02/26/2025");
        WebElement slider = homePage.getWebElementByName("my-range");
        logger.info("Range min:{}, max:{}", slider.getAttribute("min"), slider.getAttribute("max"));
        logger.info("Range step:{}, value: {}", slider.getAttribute("step"), slider.getAttribute("value"));
        logger.info("Setting value {} to range:");
        int width = slider.getSize().getWidth();
        int x = width / 2;
        int newx = (int) (width * 0.2);
        logger.info("Slider with: {}, half:{}", width, width / 2);
        new Actions(driver).dragAndDropBy(slider, x, 0).dragAndDropBy(slider, -newx, 0).perform();
        logger.info("Range value: {}", slider.getAttribute("value"));
        Thread.sleep(3000);
    }

    @When("the user submits the page")
    public void the_user_submits_the_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user submits the page");
        WebElement form_submit = homePage.getWebElementByXpath("//button[contains(text(),'Submit')]");
        form_submit.click();
    }

    @Then("the form is submitted")
    public void the_form_is_submitted() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the form is submitted");
        WebElement title = homePage.getWebElementByXpath("//h1[contains(text(),'Form submitted')]");
        softAssert.assertTrue(title.getText().equals("Form submitted"));
        softAssert.assertAll();
        Thread.sleep(3000);
    }

}
