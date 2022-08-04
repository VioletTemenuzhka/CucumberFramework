Feature: Validation of logion scenarios

  Background:
   # Given user is navigated to HRMS Application
     #the step is commented because we don't need it anymore; we're using Hooks class

  @smoke @dashboard @test62722 @latest
  Scenario: Admin login
    #Given user is navigated to HRMS Application
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

  @regression @test62722
  Scenario: ESS login
    #ESS is normal user, employee
    #Given user is navigated to HRMS Application
    When user enters valid ess credentials
    And user clicks on login button
    Then ess user is successfully logged in

  @regression @sprint12
  Scenario: Invalid login
    #Given user is navigated to HRMS Application
    When user enters invalid credentials
    And user clicks on login button
    Then user see error message on the screen

  @hw @task1
  Scenario: Invalid username
    When user enters invalid username and valid password
    And user clicks on login button
    Then user see error message on the screen

  @hw @task2
  Scenario: Invalid password
    When user enters valid username and invalid password
    And user clicks on login button
    Then user see error message on the screen

  @hw @task3
  Scenario: Blank username
    When user enters blank username and valid password
    And user clicks on login button
    Then user see error message on the screen

  @hw @task4
  Scenario: Blank password
    When user enters valid username and blank password
    And user clicks on login button
    Then user see error message on the screen


