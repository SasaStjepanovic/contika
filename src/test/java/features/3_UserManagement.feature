Feature: User management scenarios include checking and

  @Continental
  Scenario Outline: Check visibility and verification of menu items

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with random data
##    When user deletes first technician
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_001 |


