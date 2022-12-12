#Feature: Header scenarios include checking and verification menu items, deep testing of Account settings and Logout
#
#  @Continental
#  Scenario Outline:Go to the Header: Check visibility of menu items
#
#    Given a user reads test data from "sindri" "Header" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    Then user should be verify all menu items
#
#    Examples:
#      | TC_ID  |
#      | SI_001 |
#
#  @Continental
#  Scenario Outline: Go to the Header section: Sign out
#
#    Given a user reads test data from "sindri" "Header" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks signout button
#    Then user should be verify logout action
#
#    Examples:
#      | TC_ID  |
#      | SI_001 |
#
#  @Continental
#  Scenario Outline: Go to the Header section: Account Settings, change random first and last name and than verify changes
#
#    Given a user reads test data from "sindri" "Header" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks account settings button
#    When user changes first and last name
#    And user clicks save button
#    And user clicks OK button in confirmation window
#    And user closes Account setting window
#    And user clicks account settings button
#    Then user should be verify random changed credentials
#
#    Examples:
#      | TC_ID  |
#      | SI_001 |
#
#  @Continental
#  Scenario Outline: Go to the Heade section: Account Settings, change NOT random first and last name and than verify changes
#
#    Given a user reads test data from "sindri" "Header" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks account settings button
#    When user changes first and last name
#    And user clicks save button
#    And user clicks OK button in confirmation window
#    And user closes Account setting window
#    And user clicks account settings button
#    Then user should be verify not random changed credentials
#
#    Examples:
#      | TC_ID  |
#      | SI_003 |
#
#  @Continental
#  Scenario Outline: Go to the Header section: Account Settings, when first and last name remains empty
#
#    Given a user reads test data from "sindri" "Header" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks account settings button
#    When user changes first and last name
#    Then user should be verify first and last name is empty and save button is disabled
#
#    Examples:
#      | TC_ID  |
#      | SI_002 |
#
