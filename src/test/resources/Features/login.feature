Feature: Test login
  Background: the user switches to Web form page
    Given the user is on the 'Hands-On Selenium WebDriver with Java' page

  @Login
  Scenario: Login with correct credentials
    Given the user enters 'user' in 'Login' element
    And the user enters 'user' in 'Password' element
    When the users clicks on 'Submit' button
    Then the user is on the 'Login form' page
    And 'Login successful' message is on the page
