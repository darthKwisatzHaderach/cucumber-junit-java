Feature: Delete users from server

    Scenario Outline: Send sync request to delete user from server
        Given user with first name <f> and last name <l>
            And HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
            And response should contains HTTP code 200
            And response should contains user ID
        When HTTP client send sync DELETE request to delete user <f> <l> to "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200

    Examples:
    | f         | l        |
    | Ben       | Kenobi   |
    | Luke      | Skywalker|
    | Darth     | Vader    |
    | Master    | Yoda     |