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
@Path("get_grupos/{nficha}")
public class get_grupos_maestro {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of get_grupos_maestro
     */
    public get_grupos_maestro() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.acmeServer.services.get_grupos_maestro
     *
     * @param nficha resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getJson(@PathParam("nficha") String nficha) {
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
            cs = connection.prepareCall("{call get_grupos_maestro(?)}");
            cs.setString(1, nficha);
            rs = cs.executeQuery();
            ArrayList<JsonObject> myList = new ArrayList();
            while (rs.next()) {
                objetoJson = Json.createObjectBuilder()
                        .add("id", rs.getString("ID"))
                        .add("nombre", rs.getString("Nombre"))
                        .add("horario", rs.getString("Horario"))
                        .build();
                myList.add(objetoJson);
            }
            connection.close();
            return myList.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Acceso denegado, nu se";
    }

    /**
     * PUT method for updating or creating an instance of get_grupos_maestro
     *
     * @param nficha resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putXml(@PathParam("nficha") String nficha, String content) {
    }
}
