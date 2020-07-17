Feature: Сurrency Format

  Scenario Outline: Сurrency Format #REQ-102
    Given Main page is opened
    When  Page language was changed on '<lang>'
    Then  Open currency list
    Then  Check if currency list was translated correctly based on language '<lang>'
    Examples:
      | lang    |
      | Русский |
      | English |
      | Română  |

