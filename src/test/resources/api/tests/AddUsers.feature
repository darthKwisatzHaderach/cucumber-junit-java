Feature: Add users to server

    Scenario Outline: Send sync request with information about user to server
        Given user with <firstName> and <lastName>
        When HTTP client send sync request with information about user to server
        Then response should contains HTTP code 200
        Then response should contains user data from request

    Examples:
    | firstName | lastName |
    | Alexander | Great    |
    | Harry     | Potter   |
    | Bilbo     | Torbins  |
    | Gordon    | Freeman  |
