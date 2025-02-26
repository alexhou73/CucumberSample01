package org.example.StepDefinitions;

import org.example.BaseSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PracticeSteps extends BaseSteps {
    private static String url = "https://bonigarcia.dev/selenium-webdriver-java/";
    private SoftAssert softAssert = new SoftAssert();

    @Before
    public void beforeSuite(){
        driver.get(url);
    }
    @After
    public void afterSuite(){
        driver.quit();
    }

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String header) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user is on the {} page", header);
        WebElement pageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]"));
        softAssert.assertTrue(pageHeader.getText().contains(header),String.format("Failed: Expected title should be '%s' but '%s'", header, driver.getTitle()));
        softAssert.assertAll();
    }
    @Given("the user clicks on {string} button")
    public void the_user_clicks_on_button(String button) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user clicks on {} button", button);
        WebElement webformButton = driver.findElement(By.xpath("//a[contains(text(),'Web form')]"));
        webformButton.click();
    }
    @Given("the user on the {string} page")
    public void the_user_on_the_page(String header) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user on the {} page", header);
        WebElement pageHeader = driver.findElement(By.xpath("//h1[contains(text(),'" + header + "')]"));
        softAssert.assertTrue(pageHeader.getText().equals("Web form"));
        softAssert.assertAll();
    }
    @When("the user enters {string} in Text input field")
    public void the_user_enters_in_text_input_field(String text) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user enters {} in Text input field", text);
        WebElement textBox = driver.findElement(By.id("my-text-id"));
        textBox.sendKeys(text);
    }
    @When("the user enters {string} in Password field")
    public void the_user_enters_in_password_field(String password) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user enters {} in Password field", password);
        WebElement form_pwd = driver.findElement(By.name("my-password"));
        form_pwd.sendKeys(password);
    }
    @When("the user enters {string} in Textareas field")
    public void the_user_enters_in_textares_field(String text) {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user enters {string} in Textareas field", text);
        WebElement form_textarea = driver.findElement(By.name("my-textarea"));
        form_textarea.sendKeys(text);
    }

    @When("the user verifies the radio buttons")
    public void the_user_verifies_the_radio_button() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user verifies the radio buttons");

        WebElement checked_radio = driver.findElement(By.id("my-radio-1"));
        logger.info("radio button {} status: {}","my-radio-1",checked_radio.isSelected());
        WebElement default_radio = driver.findElement(By.id("my-radio-2"));
        logger.info("radio button {} status: {}","my-radio-2",default_radio.isSelected());
    }

    @When("the user verifies the checkboxes")
    public void the_user_verifies_the_checkboxes() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user verifies the checkboxes");
        WebElement checked_checkbox = driver.findElement(By.id("my-check-1"));
        logger.info("radio button {} status: {}","my-check-1",checked_checkbox.isSelected());
        WebElement default_checkbox = driver.findElement(By.id("my-check-2"));
        logger.info("radio button {} status: {}","my-check-1",checked_checkbox.isSelected());
    }
    @When("verify the user interacts with the page")
    public void verify_the_user_interacts_with_the_page() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("verify the user interacts with the page");
        WebElement disabled_element = driver.findElement(By.name("my-disabled"));
        WebElement readonly_element = driver.findElement(By.name("my-readonly"));
        softAssert.assertEquals(disabled_element.getText(), "Disabled input",
                String.format("Failed: expected:{}, actual: {}","Disabled input",disabled_element.getText()));
        softAssert.assertEquals(readonly_element.getText(), "Readonly input",
                String.format("Failed: expected:{}, actual: {}","Readonly input",readonly_element.getText()));

        Select dropdownList = new Select(driver.findElement(By.name("my-select")));
        dropdownList.selectByIndex(2);

        WebElement selectInput = driver.findElement(By.name("my-datalist"));
        WebElement option = driver.findElement(By.xpath("//datalist[@id='my-options']/option[1]"));
        selectInput.clear();
        selectInput.sendKeys(option.getAttribute("value"));

        WebElement fileInput = driver.findElement(By.name("my-file"));
        fileInput.sendKeys("/Users/alex/soapui-settings.xml");


        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        logger.info("Color Picker value: {}", colorPicker.getAttribute("value"));
        colorPicker.sendKeys("#ff0000");

        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.sendKeys("02/26/2025");
        WebElement slider = driver.findElement(By.name("my-range"));
        logger.info("Range min:{}, max:{}",slider.getAttribute("min"), slider.getAttribute("max"));
        logger.info("Range step:{}, value: {}", slider.getAttribute("step"), slider.getAttribute("value"));
        logger.info("Setting value {} to range:");
        int width = slider.getSize().getWidth();
        int x = width /2;
        int newx = (int) (width * 0.2);
        logger.info("Slider with: {}, half:{}", width,width/2);
        new Actions(driver).dragAndDropBy(slider,x,0).dragAndDropBy(slider,-newx,0).perform();
        logger.info("Range value: {}", slider.getAttribute("value"));
        Thread.sleep(3000);
    }
    @When("the user submits the page")
    public void the_user_submits_the_page() {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the user submits the page");
        WebElement form_submit = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        form_submit.click();
    }
    @Then("the form is submitted")
    public void the_form_is_submitted() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        logger.info("the form is submitted");
        WebElement title = driver.findElement(By.xpath("//h1[contains(text(),'Form submitted')]"));
        softAssert.assertTrue(title.getText().equals("Form submitted"));
        Thread.sleep(3000);
    }

}
