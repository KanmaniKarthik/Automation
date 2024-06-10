@tag
Feature: Login to Ecommerce Website
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Loging with wrong password and captures the error message
  
    Given I landed on Ecommerce Page
    When Logged in with email <email> and password <password>
    Then "Incorrect email / password." message is displayed

    Examples: 
      | email                 | password        |
      | ranimurugan@gmail.com | ChickenStall@11 |
