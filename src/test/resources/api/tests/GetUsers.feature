Feature: Get users from server by ID

    Scenario Outline: Send sync request to get user from server by ID
        Given user with first name <f> and last name <l>
            And HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
            And response should contains HTTP code 200
            And response should contains user ID
        When HTTP client send sync GET request with user <f> <l> ID to "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200
            And response should contains user data from request

    Examples:
    | f         | l        |
    | Harry     | Potter   |
    | Severus   | Snape    |
    | Professor | Dambldor |
    | Sirius    | Black    |
