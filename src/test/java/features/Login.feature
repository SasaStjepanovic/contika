Feature:

  @Continental
  Scenario Outline: Login valid user

    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
    And the landing-page is opened
    And user clicks login button
    When user enters username and password


    Examples:
      | TC_ID  |
      | SI_001 |
      | SI_002 |