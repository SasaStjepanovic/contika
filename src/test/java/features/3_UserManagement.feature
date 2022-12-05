Feature: User management scenarios include add/edit/delete technicians from the list

  @Continental
  Scenario Outline: Go to the User Management section, add one technician with random data and delete it

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with random data
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_001 |

  @Continental
  Scenario Outline: Go to the User Management section, add mor technicians with random data and delete them

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with random data
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_003 |

