/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmeServer.services;

import com.mysql.jdbc.Connection;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Yaelo
 */
@Path("preinscripcion")
public class PreinscripcionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PreinscripcionResource
     */
    public PreinscripcionResource() {
    }

    /**
     * PUT method for updating or creating an instance of PreinscripcionResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    public String putJson(JsonObject inJson) {
        try {
            JsonObject objetoJson=null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc = new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs = null;
            cs = connection.prepareCall("{call set_preinscripcion(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, inJson.getString("apeidop"));
            cs.setString(2, inJson.getString("apeidom"));
            cs.setString(3, inJson.getString("nombre"));
            cs.setString(4, inJson.getString("CURP"));
            cs.setString(5, inJson.getString("fechan"));
            cs.setString(6, inJson.getString("calle"));
            cs.setString(7, inJson.getString("colonia"));
            cs.setString(8, inJson.getString("estado"));
            cs.setString(9, inJson.getString("municipio"));
            cs.setString(10, inJson.getString("cp"));
            cs.setString(11, inJson.getString("tel"));
            rs = cs.executeQuery();
            if( rs.next()){
                objetoJson = Json.createObjectBuilder()
                        .add("id", rs.getString("ID"))
                        .build();
                connection.close();
            }
            return objetoJson.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
            return "Error"+ex.toString();
        }
    }
}
