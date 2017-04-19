package api.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import http.client.RsClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import support.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy
 */
public class UsersOperationsSteps {
    
    private User user;
    private HttpResponse response;
    private RsClient client;
    private String responseBody;
    
    @Given("^user with (.*) and (.*)")
    public void entity_user_with_firstName_and_lastName(
            String firstName, String lastName) throws Throwable {
        user = new User(null, firstName, lastName);
    }

    @When("^HTTP client send sync request with information about user to server$")
    public void http_client_send_sync_request_with_information_about_user_to_server(
    ) throws Throwable {
        client = new RsClient();
        response = client.postUserData(user);
    }

    @Then("^response should contains HTTP code 200$")
    public void response_should_contain_HTTP_code() throws Throwable {
        Assert.assertThat(response.getStatusLine().toString(), containsString("200 OK"));
    }
    
    @Then("^response should contains user data from request$")
    public void response_should_contains_user_data_from_request() throws Throwable {
        responseBody = EntityUtils.toString(response.getEntity());
        user.setId(Integer.parseInt(responseBody.substring(responseBody.indexOf("=")+1, responseBody.indexOf(","))));        
        
        Assert.assertThat(responseBody, containsString(user.firstName));
        Assert.assertThat(responseBody, containsString(user.lastName));
    }
    
    @When("^HTTP client send sync request to delete user with (.*) and (.*)")
    public void http_client_send_sync_request_to_delete_user_with_firstName_and_lastName(
        String firstName, String lastName) throws Throwable {
        response = client.deleteUser(user);
    }
}
