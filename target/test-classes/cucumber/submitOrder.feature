@tag
Feature: Purchase the product from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of sumbmitting the order
  
    Given Logged in with email <email> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | email                   | password       | productName     |
      | mhkanmani1996@gmail.com | HarryPotter@1  | ADIDAS ORIGINAL |
