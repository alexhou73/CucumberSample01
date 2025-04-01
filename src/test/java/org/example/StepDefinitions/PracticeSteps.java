package org.example.StepDefinitions;

import org.example.BaseSteps;
import org.example.commons.Environment;
import org.example.commons.selenium.ByFactory;
import org.example.pages.HomePage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PracticeSteps extends BaseSteps {
    private HomePage homePage = new HomePage(driver);

    @Before
    public void beforeSuite() {
        driver.get(url);
        ByFactory.setLocatorFileName(Environment.INSTANCE.getPropertyByExactKey("locator"));
    }

    @After
    public void afterSuite() {
        driver.quit();
        softAssert.assertAll();
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

        logger.info("Verifying the disabled textbox...");
        softAssert.assertFalse(homePage.verifyEnabledTextBoxByName("my-disabled"),
                String.format("Failed: expected:{}", "Disabled input"));
        logger.info("Verifying the readonly textbox...");
        softAssert.assertTrue(homePage.verifyEnabledTextBoxByName("my-readonly"),
                String.format("Failed: expected:{}", "Readonly input"));

        logger.info("Setting index #2 of the dropdown list...");
        homePage.setDropdownListByIndex("my-select", 2);

        logger.info("Setting index #1 of the datalist...");
        homePage.setDataListByIndex("my-datalist", "my-options", 1);

        logger.info("Uploading File...");
        homePage.uploadFile("my-file", "/Users/alex/soapui-settings.xml");

        logger.info("Setting color: #00FF00...");
        homePage.setColor("my-colors", "#00FF00");

        logger.info("Setting date: 02/26/2025...");
        homePage.datePicker("my-date", "02/26/2025");

        logger.info("Setting value in slider...");
        homePage.setSlider("my-range", 0.2);

        Thread.sleep(5000);
    }

    @When("the user submits the page")
    public void the_user_submits_the_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user submits the page");
        homePage.clickButton("Submit");
    }

    @Then("the form is submitted")
    public void the_form_is_submitted() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the form is submitted");
        softAssert.assertTrue(homePage.userOnPage("Form submitted"),
                "Fail! Form is not submitted");

        softAssert.assertAll();
        Thread.sleep(3000);
    }

}
