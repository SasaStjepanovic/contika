Feature: Header scenarios include checking and verification menu items, deep testing of Account settings and Logout

  @Continental
  Scenario Outline: Check visibility and verification of menu items

    Given a user reads test data from "sindri" "Licences" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user click on menu and user management item
#    Then user should be verify all menu items

    Examples:
      | TC_ID  |
      | SI_001 |


