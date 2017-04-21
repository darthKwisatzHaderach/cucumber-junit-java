package api.tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import http.client.RsClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.Assert;
import api.users.User;
import db.client.DbClient;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
    private String responseBody;
    private RsClient rsClient;
    private DbClient dbClient;
    
    @Given("^user with first name (.*) and last name (.*)")
    public void user_with_firstName_and_lastName(
            String firstName, String lastName) throws Throwable {
        user = new User(null, firstName, lastName);
    }

    @When("^HTTP client send sync POST request with information about user to \"([^\"]*)\"$")
    public void http_client_send_sync_post_request_with_information_about_user_to(
            String endpointUrl) throws Throwable {
        rsClient = new RsClient();
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("firstName", user.firstName));
        params.add(new BasicNameValuePair("lastName", user.lastName));
        response = rsClient.postUserData(user, endpointUrl, params);
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
        rsClient = new RsClient();
        response = rsClient.deleteUser(user, endpointUrl);
        responseBody = EntityUtils.toString(response.getEntity());
    }
    
    @When("^HTTP client send sync GET request with user (.*) (.*) ID to \"([^\"]*)\"$")
    public void http_client_send_sync_GET_request_with_user_ID_to(
            String firstName, String lastName, String endpointUrl) throws Throwable {
        rsClient = new RsClient();
        response = rsClient.getUser(user, endpointUrl);
        responseBody = EntityUtils.toString(response.getEntity());
    }
    
    @Given("^user data was changed to (.*) (.*)$")
    public void user_data_was_changed_to_Ray_Awakining(
            String newFirstName, String newLastName) throws Throwable {
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
    }
    
    @When("^HTTP client send sync PUT request to update user (.*) (.*) on \"([^\"]*)\"$")
    public void http_client_send_sync_PUT_request_to_update_user_on_address(
            String firstName, String lastName, String endpointUrl) throws Throwable {
        rsClient = new RsClient();
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("firstName", user.firstName));
        params.add(new BasicNameValuePair("lastName", user.lastName));
        response = rsClient.updateUser(user, endpointUrl, params);
        responseBody = EntityUtils.toString(response.getEntity());
    }
    
    @Then("^database should contains user from request$")
    public void database_should_contains_user_from_request() throws Throwable {
        dbClient = new DbClient();
        //Для проверки записей в БД нужен другой тип БД (не in-memmory)
        //Для данного типа нужно тесты в том же контейнере запускать
        //In order to access or view the embedded database, the particular “database manager tool” 
        //must start with the same Spring container or JVM, which started the embedded database. 
        //Furthermore, the “database manager tool” must start after the embedded database bean, 
        //best resolve this by observing the Spring’s log to identify loading sequence of the beans.
        //The “HSQL database manager” is a good tool, just start within the same Spring container.
    }
    
    private Integer getUserIdFromResponse(String responseBody){
        return Integer.parseInt(responseBody.substring(
                responseBody.indexOf("=")+1, responseBody.indexOf(",")));
    }
}
