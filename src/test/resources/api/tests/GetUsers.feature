Feature: Get users from server by ID

    Background: prepare user for scenario
        Given user with first name <f> and last name <l>
        When HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
        Then response should contains HTTP code 200
        And response should contains user ID

    Scenario Outline: Get users from server by ID
        When HTTP client send sync GET request with user <f> <l> ID to "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200
        And response should contains user data from request

    Examples:
    | f         | l        |
    | Harry     | Potter   |
    | Severus   | Snape    |
    | Professor | Dambldor |
    | Sirius    | Black    |
