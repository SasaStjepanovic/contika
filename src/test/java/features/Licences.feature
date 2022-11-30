Feature: Login scenarious include valid login and invalid login with combination of wrong credentials

  @Continental
  Scenario Outline: Login valid user

    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
    And the landing-page is opened
    And user clicks login button
    When user enters username and password
    Then user should be verified successfully login

    Examples:
      | TC_ID  |
      | SI_001 |

  @Continental
  Scenario Outline: Login invalid user(wrong password)

    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
    And the landing-page is opened
    And user clicks login button
    When user enters username and password
    Then user should be verified unsuccessfully login


    Examples:
      | TC_ID  |
      | SI_002 |

  @Continental
  Scenario Outline: Login invalid user(wrong username)

    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
    And the landing-page is opened
    And user clicks login button
    When user enters username and password
    Then user should be verified unsuccessfully login


    Examples:
      | TC_ID  |
      | SI_003 |

#  @Continental
#  Scenario Outline: Login sa HOVWERRRRRRRRR
#
##    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And user clicks signin button
#    And signin again
#    And hover
#
#
#    Examples:
#      | TC_ID  |
#      | SI_001 |
##      | SI_002 |