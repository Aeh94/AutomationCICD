
@tag
Feature: Error validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error validation
    Given I landed on Ecommerce page
    When I logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
     	| username  						| password 			 | 
      | tst78552@gmaedfil.com | Abdfdcdefgh@10 |

  