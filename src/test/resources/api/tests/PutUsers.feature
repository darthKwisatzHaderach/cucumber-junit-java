Feature: Delete users from server

    Background: prepare user for scenario
        Given user with first name <f> and last name <l>
        When HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
        Then response should contains HTTP code 200
        And response should contains user ID

    Scenario Outline: Send sync request to delete user from server
        When HTTP client send sync PUT request to update user <f> <l> to <nf> <nl> on address "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200

    Examples:
    | f         | l         | nf        | nl         |
    | Ben       | Kenobi    | Obi       | Van        |
    | Luke      | Skywalker | Ben       | Solo       |
    | Darth     | Vader     | Ray       | Awakining  |
    | Master    | Yoda      | Yoda      | Master     |
