Feature: Delete users from server

    Background: prepare user for scenario
        Given user with <firstName> and <lastName>
        When HTTP client send sync request with information about user to server
        Then response should contains HTTP code 200
        Then response should contains user data from request

    Scenario Outline: Send sync request to delete user from server
        When HTTP client send sync request to delete user with <firstName> and <lastName>
        Then response should contains HTTP code 200

    Examples:
    | firstName | lastName |
    | Ben       | Kenobi   |
    | Luke      | Skywalker|
    | Darth     | Vader    |
    | Master    | Yoda     |