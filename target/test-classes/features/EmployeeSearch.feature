Feature: US-12345 Search an employee in HRMS

  Background:
   # Given user is navigated to HRMS Application
    #the step is commented because we don't need it anymore; we're using Hooks class
    When user enters valid admin credentials
    And user clicks on login button
    And user navigates to employee list page

  @regression @background
  Scenario: Search an employee by id
    #* user is navigated to HRMS Application
    #* user enters valid admin credentials
    #* user clicks on login button
    #* user navigates to employee list page
    * user enters valid employee id
    * clicks on search button
    * user is able to see employee information

  #We are writing a comment using #

  @smoke @background
  Scenario: Search an employee by name
    #Given user is navigated to HRMS Application
    #When user enters valid admin credentials
    #And user clicks on login button
    #And user navigates to employee list page
    When user enters valid employee name
    And clicks on search button
    Then user is able to see employee information


