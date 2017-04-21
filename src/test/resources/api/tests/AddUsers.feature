Feature: Add users to server

    Scenario Outline: Send sync request with information about user to server
        Given user with first name <f> and last name <l>
        When HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
        Then response should contains HTTP code 200
            And response should contains user ID
            And response should contains user data from request
            And database should contains user from request

    Examples:
    | f         | l        |
    | Frodo     | Baggins  |
    | Gendalf   | Grey     |
    | Bilbo     | Torbins  |
    | Elrond    | Great    |
