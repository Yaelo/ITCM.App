/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.appAndroid.model;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Fernando
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String userId;
    protected String password;
    protected String name;
    protected String lastNameP;
    protected String lastNameM;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNameP() {
        return lastNameP;
    }

    public void setLastNameP(String lastNameM) {
        this.lastNameP = lastNameM;
    }
    public String getLastNameM() {
        return lastNameM;
    }

    public void setLastNameM(String lastNameM) {
        this.lastNameM = lastNameM;
    }
    
    public void changePassword(String newPassword){
        
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    private  void setPassword(String password) {
        this.password = password;
    }
    
    
}
