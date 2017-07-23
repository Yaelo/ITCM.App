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
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Yaelo
 */
@Path("get_libro/{tipo}/{data}/{area}")
public class Get_libro {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_libro
     */
    public Get_libro() {
    }

    /**
     * Retrieves representation of an instance of com.acme.acmeServer.services.Get_libro
     * @param tipo resource URI parameter
     * @param data resource URI parameter
     * @param area resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText(@PathParam("tipo") int tipo, @PathParam("data") String data, @PathParam("area") String area) {
        try {
            JsonObject objetoJson = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc = new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs = null;
            if(tipo==1){//La busqueda es por el titulo del libro
                cs = connection.prepareCall("{call get_libro_by_tit(?,?)}");
                cs.setString(1, data);
                cs.setString(2, area);
            }
            else{
                cs = connection.prepareCall("{call get_libro_by_aut(?,?)}");
                cs.setString(1, data);
                cs.setString(2, area);
            }
            rs = cs.executeQuery();
            ArrayList<JsonObject> myList = new ArrayList();
            while (rs.next()) {
                objetoJson = Json.createObjectBuilder()
                        .add("titulo", rs.getString("Titulo"))
                        .add("autor", rs.getString("Autor"))
                        .build();
                myList.add(objetoJson);
            }
            connection.close();
            return myList.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }
}
