Feature: Checklist Management scenarios include valid login and invalid login with combination of wrong credentials

  @Continental
  Scenario Outline: Login valid user

    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and checklist management item
    And user verify that checklist management page is opened
    When user enters checklist data


    Examples:
      | TC_ID  |
      | SI_001 |

