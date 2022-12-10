Feature: User management scenarios include add/edit/delete technicians from the list

  @Continental
  Scenario Outline: Go to the User Management section, add one technician with random data and delete it

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with data
    When user delete all technicians
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_001 |

  @Continental
  Scenario Outline: Go to the User Management section, add more technicians with random data and delete them

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with data
    When user delete all technicians
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_003 |

  @Continental
  Scenario Outline: Go to the User Management section, try to add two technicians with the same email address (negative scenario)

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates two technicians with same data
    Then user should verify that is not posible to create user with the same data
    When user delete all technicians
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_004 |

  @Continental
  Scenario Outline: Go to the User Management section, try to add technician with the invalid email address (negative scenario)

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with invalid email address
    Then user should verify that is not posible to create user with the invalid email address

    Examples:
      | TC_ID  |
      | SI_005 |

  @Continental
  Scenario Outline: Go to the User Management section, edit Personal information of existing one technician with random data

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with data
    When user edit personal information of existing technician
    Then user should verify that technician is edited
    When user delete all technicians
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_001 |

  @Continental
  Scenario Outline: Go to the User Management section, check licence allocated of existing one technician

    Given a user reads test data from "sindri" "UserManagement" by id "<TC_ID>"
    And user clicks login button
    When user enters username and password
    And user clicks on menu and user management item
    And user verify that user management page is opened
    When user creates technician with data
    When user edit licence allocated of existing technician
    Then user should verify clickable of remove allocation button
    When user delete all technicians
    Then user should verify that all technicians are deleted

    Examples:
      | TC_ID  |
      | SI_001 |