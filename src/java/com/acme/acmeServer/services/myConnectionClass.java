/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmeServer.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yaelo
 */
public class myConnectionClass {
    Connection connection;
    public myConnectionClass() throws SQLException {
        connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/itcm_db", "root", "root");
    }
    public Connection getConnection(){
        return connection;
    }
}
