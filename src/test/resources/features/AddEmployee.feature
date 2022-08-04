Feature: Adding the employees in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM options
    And user clicks on add employee option

  @regression
  Scenario: Adding one employee from feature file
   # Given user is navigated to HRMS Application
    #the step is commented because we don't need it anymore; we're using Hooks class
    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee added successfully

  @regressin
  Scenario: Adding one employee using cucumber feature file
    And user enters "dasir" "hamilia" and "tolom"
    And user clicks on save button
    Then employee added successfully

  @test @examplestable @scenariooutline
 Scenario Outline: Adding multiple employees
   #here we are providing the keys
   And user provides "<firstName>" "<middleName>" and "<lastName>"
   And user clicks on save button
   Then employee added successfully
   Examples:
     |firstName|middleName|lastName|
     |asel     |MS        |tolga   |
     |yazgul   |MS        |kishan  |
     |tarik    |MS        |mujeeb  |
     |nassir   |MS        |volkan  |

   @test @datatable
  Scenario: Add employee using data table
    When user provides multiple employee data and verify they are added
     |firstName|middleName|lastName|
     |asel     |MS        |tolga   |
     |yazgul   |MS        |kishan  |
     |tarik    |MS        |mujeeb  |

  @excel
  Scenario: Adding multiple employees from excel
    When user adds multiple employees from excel file using "EmployeeData" sheet and verify the user added



