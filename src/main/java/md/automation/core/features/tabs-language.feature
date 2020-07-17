Feature: Tabs language

  Scenario Outline: Tabs language #REQ-104
    Given Main page is opened
    When  Page language was changed on '<lang>'
    Then  We should click on '<menu>' option and check if '<pageTabHeader>' and '<tabPage>' was translated correctly
    Examples:
      | lang    | menu                                | tabPage                                                                                                         |pageTabHeader                                                                                    |
      | Русский | Эволюция курсов, Курсы валют        | График эволюции валюты Евро в Banca Nationala относительно Молдавский лей, Curs.md - Курсы валют                |График эволюции валюты Евро в Banca Nationala относительно Молдавский лей, официальный курс      |
      | English | Rates evolution, Exchange rates     | Graph of Euro rates evolution in Banca Nationala relative to Moldovan Leu, Curs.md - Exchange rates             |Graph of Euro rates evolution in Banca Nationala relative to Moldovan Leu, official exchange rate|
      | Română  | Grafic evolutii, Curs valutar       | Graficul evolutiei valutei Euro la Banca Nationala raportat la Leu moldovenesc, Curs.md - Curs valutar          |Graficul evolutiei valutei Euro la Banca Nationala raportat la Leu moldovenesc, curs oficial     |
