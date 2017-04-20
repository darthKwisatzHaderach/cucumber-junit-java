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
    
    @Given("^user with first name (.*) and last name (.*)")
    public void entity_user_with_firstName_and_lastName(
            String firstName, String lastName) throws Throwable {
        user = new User(null, firstName, lastName);
    }

    @When("^HTTP client send sync POST request with information about user to \"([^\"]*)\"$")
    public void http_client_send_sync_post_request_with_information_about_user_to(
            String endpointUrl) throws Throwable {
        client = new RsClient();
        response = client.postUserData(user, endpointUrl);
        responseBody = EntityUtils.toString(response.getEntity());
    }

    @Then("^response should contains HTTP code 200$")
    public void response_should_contain_HTTP_code() throws Throwable {
        Assert.assertThat(response.getStatusLine().toString(), containsString("200 OK"));
    }
    
    @Then("^response should contains user ID$")
    public void response_should_contains_user_ID() throws Throwable {        
        user.setId(getUserIdFromResponse(responseBody));  
    }
    
    @Then("^response should contains user data from request$")
    public void response_should_contains_user_data_from_request() throws Throwable {      
        Assert.assertThat(responseBody, containsString(user.firstName));
        Assert.assertThat(responseBody, containsString(user.lastName));
    }
    
    @When("^HTTP client send sync DELETE request to delete user (.*) (.*) to \"([^\"]*)\"$")
    public void http_client_send_sync_delete_request_to_delete_user_f_l_to(
        String firstName, String lastName, String endpointUrl) throws Throwable {
        client = new RsClient();
        response = client.deleteUser(user, endpointUrl);
        responseBody = EntityUtils.toString(response.getEntity());
    }
    
    @When("^HTTP client send sync GET request with user (.*) (.*) ID to \"([^\"]*)\"$")
    public void http_client_send_sync_GET_request_with_user_ID_to(
            String firstName, String lastName, String endpointUrl) throws Throwable {
        client = new RsClient();
        response = client.getUser(user, endpointUrl);
        responseBody = EntityUtils.toString(response.getEntity());
    }
    
    private Integer getUserIdFromResponse(String responseBody){
        return Integer.parseInt(responseBody.substring(
                responseBody.indexOf("=")+1, responseBody.indexOf(",")));
    }
}
