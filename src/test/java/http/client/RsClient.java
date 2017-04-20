/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import support.User;

/**
 *
 * @author dmitriy
 */
public class RsClient {
    
    public HttpResponse postUserData(User user, String url)
            throws UnsupportedEncodingException, IOException{
        
        org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("firstName", user.firstName));
        params.add(new BasicNameValuePair("lastName", user.lastName));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        
        return response;
    }
    
    public HttpResponse deleteUser(User user, String url)
            throws UnsupportedEncodingException, IOException{
        
        org.apache.http.client.HttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpdelete = new HttpDelete(String.format(url, user.id));
        
        HttpResponse response = httpclient.execute(httpdelete);
        
        return response;
    }
}
