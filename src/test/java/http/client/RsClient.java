/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import api.users.User;

/**
 *
 * @author dmitriy
 */
public class RsClient {
    
    public HttpResponse postUserData(User user, String url, List<NameValuePair> params)
            throws UnsupportedEncodingException, IOException{
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

       return httpclient.execute(httpPost);
    }
    
    public HttpResponse deleteUser(User user, String url)
            throws UnsupportedEncodingException, IOException{
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(String.format(url, user.id));
        
        return httpclient.execute(httpDelete);
    }
    
    public HttpResponse getUser(User user, String url)
            throws UnsupportedEncodingException, IOException{
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format(url, user.id));
        
        return httpclient.execute(httpGet);
    }
            
    public HttpResponse updateUser(User user, String url, List<NameValuePair> params)
            throws UnsupportedEncodingException, IOException{
        
        HttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(String.format(url, user.id));
        
        httpPut.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        
        return httpclient.execute(httpPut);
    }
}
