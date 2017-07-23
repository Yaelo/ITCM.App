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
@Path("get_inscripcion/{ncontrol}")
public class Get_inscripcion {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of getInscripcion
     */
    public Get_inscripcion() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.acmeServer.services.Get_inscripcion
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getJson(@PathParam("ncontrol") String ncontrol) {
        try {
            JsonObject objetoJson = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc = new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs = null;
            cs = connection.prepareCall("{call get_periodo_inscripcion(?)}");
            cs.setString(1, ncontrol);
            rs = cs.executeQuery();
            rs.next();
            objetoJson = Json.createObjectBuilder()
                    .add("fecha", rs.getString("Fecha"))
                    .add("hora", rs.getString("Hora"))
                    .build();
            connection.close();
            return objetoJson.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Acceso denegado";
    }

    /**
     * PUT method for updating or creating an instance of Get_inscripcion
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
