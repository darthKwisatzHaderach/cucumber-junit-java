/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

/**
 *
 * @author dmitriy
 */
public class User {
    
    public Integer id;
    public String firstName;
    public String lastName;

    public User(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
}
