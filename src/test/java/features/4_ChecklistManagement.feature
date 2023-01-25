#Feature: Checklist Management scenarios include create check lists with headers and items, edit, delete and verification any popup message
#
#  @Continental
#  Scenario Outline: Go to the Checklist Management section, create more check lists with more headers/items and delete all of them at the end
#
#    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks on menu and checklist management item
#    And user verify that checklist management page is opened
#    When user enters checklist data
#    When user delete all checklists
#    Then user should verify that all checklists are deleted
#
#    Examples:
#      | TC_ID  |
#      | SI_001 |
#
#  @Continental
#  Scenario Outline: Go to the Checklist Management section, create and edit check list
#
#    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks on menu and checklist management item
#    And user verify that checklist management page is opened
#    When user enters checklist data
#    When user edits check list data
#    When user delete all checklists
#    Then user should verify that all checklists are deleted
#
#    Examples:0
#      | TC_ID  |
#      | SI_002 |
#
#
#  @Continental
#  Scenario Outline: Go to the Checklist Management section, create check list without header (negative scenario)
#
#    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks on menu and checklist management item
#    And user verify that checklist management page is opened
#    When user enters checklist data
#    And user press save button
#    Then user should verify that checklist can not without header
#
#    Examples:
#      | TC_ID  |
#      | SI_004 |
#
#  @Continental
#  Scenario Outline: Go to the Checklist Management section, create check list without item (negative scenario)
#
#    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks on menu and checklist management item
#    And user verify that checklist management page is opened
#    When user enters checklist data
#    And user press save button
#    Then user should verify that checklist can not be without item
#
#    Examples:
#      | TC_ID  |
#      | SI_005 |
#
#  @Continental
#  Scenario Outline: Go to the Checklist Management section, create check list and than new version of check list
#
#    Given a user reads test data from "sindri" "ChecklistManagement" by id "<TC_ID>"
#    And user clicks login button
#    When user enters username and password
#    And user clicks on menu and checklist management item
#    And user verify that checklist management page is opened
#    When user enters checklist data
#    When user creates new version checklist
#    Then user should verify version number
#    When user delete all checklists
#    Then user should verify that all checklists are deleted
#
#    Examples:0
#      | TC_ID  |
#      | SI_006 |
