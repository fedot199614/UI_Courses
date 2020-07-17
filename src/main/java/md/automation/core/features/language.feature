Feature: Language changer

  Scenario Outline: Language changer #REQ-101
    Given Main page is opened
    When  Page language was changed on '<lang>'
    Then Check if page was translated correctly via menu '<menu>'
    And Page '<header>' also was changed correctly depending on the language
    Examples:
      | lang    | menu                                                                                    | header                   |
      | Русский | Эволюция курсов, Конвертер, Курсы валют, Банки, Валютные кассы, Валюты                  | Curs.md - Курсы валют    |
      | English | Rates evolution, Convertor, Exchange rates, Banks, Exchange offices, Currencies         | Curs.md - Exchange rates |
      | Română  | Grafic evolutii, Convertor valutar, Curs valutar, Bănci, Case de schimb valutar, Valute | Curs.md - Curs valutar   |

