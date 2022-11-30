Feature: There are no any licence and it will be tested only functions of header

  @Continental
  Scenario Outline: Check visibility of menu items

    Given a user reads test data from "sindri" "Licences" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    Then user should be verify all menu items

    Examples:
      | TC_ID  |
      | SI_001 |

  @Continental
  Scenario Outline: Sign out

    Given a user reads test data from "sindri" "Licences" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks signout button
    Then user should be verify logout action

    Examples:
      | TC_ID  |
      | SI_001 |

