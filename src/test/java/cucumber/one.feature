Feature: Color Filter

  Scenario: testing http://automationpractice.com/index.php
    Given I am at http://automationpractice.com/index.php
    When I click "Contact us"
    Then The page title should start with "Contact us"