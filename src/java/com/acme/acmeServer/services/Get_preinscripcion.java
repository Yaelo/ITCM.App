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
@Path("idpreins/{idpre}")
public class Get_preinscripcion {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Get_preinscripcion
     */
    public Get_preinscripcion() {
    }

    /**
     * Retrieves representation of an instance of com.acme.acmeServer.services.Get_preinscripcion
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText(@PathParam("idpre")String idpre) {
        try {
            JsonObject objetoJson = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc = new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs = null;
            cs = connection.prepareCall("{call get_preinscripcion(?)}");
            cs.setString(1, idpre);
            rs = cs.executeQuery();
            rs.next();
            objetoJson = Json.createObjectBuilder()
                    .add("id", rs.getString("ID"))
                    .add("apeidop", rs.getString("ApeidoP"))
                    .add("apeidom", rs.getString("ApeidoM"))
                    .add("nombre", rs.getString("Nombre"))
                    .add("CURP", rs.getString("CURP"))
                    .add("fechan", rs.getString("FechaNacimiento"))
                    .add("calle", rs.getString("Calle"))
                    .add("colonia", rs.getString("Colonia"))
                    .add("estado", rs.getString("Estado"))
                    .add("municipio", rs.getString("Municipio"))
                    .add("cp", rs.getString("CodigoPostal"))
                    .add("tel", rs.getString("Telefono"))
                    .build();
            connection.close();
            return objetoJson.toString();
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Acceso denegado";
    }

    /**
     * PUT method for updating or creating an instance of Get_preinscripcion
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
