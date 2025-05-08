@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test to submit the order
    Given I logged in with username <username> and password <password>
    When I add the product <product> to Cart
    And checkout the <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | username  				| password 		| product 			|
      | tst1020@gmail.com | Abcdefgh@10 |ADIDAS ORIGINAL|
