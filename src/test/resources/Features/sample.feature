Feature: Test web controls
  Background: the user switches to Web form page
    Given the user is on the 'Hands-On Selenium WebDriver with Java' page
    And the user clicks on 'Web form' button

  @Smoke
  Scenario: Check basic web controls
    Given the user is on the 'Web form' page
    And the user enters 'AAAAA' in Text input field
    And the user enters 'password' in Password field
    When the user submits the page
    Then the form is submitted

  @General
  Scenario: Check the common web controls
    Given the user is on the 'Web form' page
    And the user enters 'Multiple \r\nlines' in Textareas field
    And the user verifies the radio buttons
    And the user verifies the checkboxes
    When the user submits the page
    Then the form is submitted

  @Special
  Scenario: Check the common web controls
    Given the user is on the 'Web form' page
    And verify the user interacts with the page
    When the user submits the page
    Then the form is submitted
