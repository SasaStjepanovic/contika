Feature: Login scenarios include valid login and invalid login with combination of wrong credentials

  @Continental
  Scenario Outline: Login valid user

    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
    And the landing-page is opened
    And user clicks login button
    When user enters username and password
    Then user should be verified login action

    Examples:
      | TC_ID  |
      | SI_001 |

#  @Continental
#  Scenario Outline: Login invalid user(wrong password)
#
#    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And the landing-page is opened
#    And user clicks login button
#    When user enters username and password
#    Then user should be verified login action
#
#    Examples:
#      | TC_ID  |
#      | SI_002 |
#
#  @Continental
#  Scenario Outline: Login invalid user(wrong username)
#
#    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And the landing-page is opened
#    And user clicks login button
#    When user enters username and password
#    Then user should be verified login action
#
#    Examples:
#      | TC_ID  |
#      | SI_003 |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field username)
#
#    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    Then user should be verified login action
#
#    Examples:
#      | TC_ID  |
#      | SI_004 |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field password)
#
#    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    Then user should be verified login action
#
#    Examples:
#      | TC_ID  |
#      | SI_005 |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field username & password)
#
#    Given a user reads test data from "sindri" "login" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    Then user should be verified login action
#
#    Examples:
#      | TC_ID  |
#      | SI_006 |