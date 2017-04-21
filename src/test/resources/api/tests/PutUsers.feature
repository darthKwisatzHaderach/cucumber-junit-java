Feature: Delete users from server

    Scenario Outline: Send sync request to update user on server
        Given user with first name <f> and last name <l>
            And HTTP client send sync POST request with information about user to "http://localhost:28080/rs/users"
            And response should contains HTTP code 200
            And response should contains user ID  
            And user data was changed to <nf> <nl>
        When HTTP client send sync PUT request to update user <f> <l> on "http://localhost:28080/rs/users/%d"
        Then response should contains HTTP code 200

    Examples:
    | f         | l         | nf        | nl         |
    | Ben       | Kenobi    | Obi       | Van        |
    | Luke      | Skywalker | Ben       | Solo       |
    | Darth     | Vader     | Ray       | Awakining  |
    | Master    | Yoda      | Yoda      | Master     |
