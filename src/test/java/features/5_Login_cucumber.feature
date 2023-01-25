#Feature: Login scenarios include valid login and invalid login with combination of wrong credentials
#
#  @Continental
#  Scenario Outline: Login valid user
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username                                        |  password     | testType |  expectedText  | attributeType |
#      | continental.automation+sale@gmail.com           |Sale_The_Man_1 | positive | ALL MY LICENCES| value |


#  @Continental
#  Scenario Outline: Login invalid user(wrong password)
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username                                        |  password     | testType |  expectedText                                  | attributeType |
#      | continental.automation+sale@gmail.com           | kdldkdokd     | negative | The username or password you entered is invalid| value         |
#
#  @Continental
#  Scenario Outline: Login invalid user(wrong username)
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username      |  password          | testType |  expectedText                                  | attributeType |
#      | eieueiueueueue| Sale_The_Man_1     | negative | The username or password you entered is invalid| value         |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field username)
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username             |  password  | testType |  expectedText | attributeType |
#      || Sale_The_Man_1     | negativeI | Please fill out this field.| validationMessage         |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field password)
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username             |  password  | testType |  expectedText | attributeType |
#      |continental.automation+sale@gmail.com|| negativeII | Please fill out this field.| validationMessage         |
#
#  @Continental
#  Scenario Outline: Login invalid user(empty field username & password)
#
#    Given I am logged in "<username>" AND "<password>"
#    Then user should be verified login action "<testType>" AND "<expectedText>" AND "<attributeType>"
#
#    Examples:
#      | username             |  password  | testType |  expectedText | attributeType |
#      ||| negativeI | Please fill out this field.| validationMessage         |