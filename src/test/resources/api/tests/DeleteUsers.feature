Feature: Delete users from server

    Background: prepare user for scenario
        Given user with first name <f> and last name <l>
        When HTTP client send sync request with information about user to "http://localhost:28080/rs/users"
        Then response should contains HTTP code 200
        And response should contains user ID

    Scenario Outline: Send sync request to delete user from server
        When HTTP client send sync request to delete user <f> <l> to "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200

    Examples:
    | f         | l        |
    | Ben       | Kenobi   |
    | Luke      | Skywalker|
    | Darth     | Vader    |
    | Master    | Yoda     |