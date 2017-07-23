/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmeServer.services;

import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Yaelo
 */
@Path("get_areal")
public class Get_areas_libros {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_areas_libros
     */
    public Get_areas_libros() {
    }

    /**
     * Retrieves representation of an instance of com.acme.acmeServer.services.Get_areas_libros
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getXml() {
        try {
            JsonObject objetoJson = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc = new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs = null;
//                rs = st.executeQuery("SELECT * FROM alumno WHERE NumControl="
//                        + "(SELECT user from usuario where user = '" + userID + "' AND pass = " + pass + ")");
            cs = connection.prepareCall("{call get_areas()}");
            rs = cs.executeQuery();
            ArrayList<JsonObject> myList = new ArrayList();
            while (rs.next()) {
                objetoJson = Json.createObjectBuilder()
                        .add("id", rs.getString("IDArea"))
                        .add("nombre", rs.getString("Nombre"))
                        .build();
                myList.add(objetoJson);
            }
            connection.close();
            return myList.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Algo falla";
    }
}
