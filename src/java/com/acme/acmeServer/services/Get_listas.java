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
@Path("get_listas/{idgrupo}")
public class Get_listas {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of get_listas_maestro
     */
    public Get_listas() {
    }

    /**
     * Retrieves representation of an instance of com.acme.acmeServer.services.Get_listas
     * @param nficha resource URI parameter
     * @param idgrupo resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@PathParam("idgrupo") String idgrupo) {
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
            cs = connection.prepareCall("{call get_listas(?)}");
            cs.setString(1, idgrupo);
            rs = cs.executeQuery();
            ArrayList<JsonObject> myList=new ArrayList();
            String calif;
            if (rs.next()) {
                do{
                    if(rs.getString("Calificacion")==null){
                        calif="";
                    }
                    else{
                        calif=rs.getString("Calificacion");
                    }
                    objetoJson = Json.createObjectBuilder()
                            .add("ncontrol", rs.getString("NumControl"))
                            .add("nombre", rs.getString("Alumno"))
                            .add("calificacion",calif)
                            .build();
                    myList.add(objetoJson); 
                }while(rs.next());
                connection.close();
                return myList.toString();

            } else {
                connection.close();
                return "Acceso denegado, no hubo de regreso";
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Acceso denegado, nu se";
    }

    /**
     * PUT method for updating or creating an instance of Get_listas
     * @param nficha resource URI parameter
     * @param idgrupo resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(@PathParam("nficha") String nficha, @PathParam("idgrupo") String idgrupo, String content) {
    }
}
