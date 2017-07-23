/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmeServer.services;

import com.mysql.jdbc.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.StoredProcedureParameter;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Fernando
 */
@Path("login/{usr}/{pass}")
//@Path("login/{usr}")
public class LoginWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginWS
     */
    public LoginWS() {
    }

    /**
     * Retrieves representation of an instance of
     * com.acme.acmeServer.services.LoginWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getJson(@PathParam("usr") String userID, @PathParam("pass") String pass) {
//    public String getJson(@PathParam("usr") String userId, @PathParam("pass") String userPassword) {
        try {
            JsonObject objetoJson = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myConnectionClass mc=new myConnectionClass();
            Connection connection = (Connection) mc.getConnection();
            Statement st = connection.createStatement();
            CallableStatement cs;
            ResultSet rs;
            int aux;
            if (userID.length() == 8) {//userID 8 letras = NControl alumno
                cs=connection.prepareCall("{call get_user_alumno(?,?)}");
                cs.setString(1, userID);
                cs.setString(2, pass);
                rs = cs.executeQuery();
                aux = 1;
            } else if (userID.length() == 4) {//userID 4 letras = NumFicha maestro
                cs=connection.prepareCall("{call get_user_maestro(?,?)}");
                cs.setString(1, userID);
                cs.setString(2, pass);
                rs = cs.executeQuery();
                aux = 2;
            }else{//en caso contrario es externo
                cs=connection.prepareCall("{call get_user_aspirante(?,?)}");
                cs.setString(1, userID);
                cs.setString(2, pass);
                rs = cs.executeQuery();
                aux = 3;
            }
            if (rs.next()) {
                switch (aux) {
                    case 1:
                        objetoJson = Json.createObjectBuilder()
                                .add("userType", "Alumno")
                                .add("userID", rs.getString("NumControl"))
                                .add("apeidop", rs.getString("ApeidoP"))
                                .add("apeidom", rs.getString("ApeidoM"))
                                .add("nombre", rs.getString("Nombre"))
//                                .add("reticula", rs.getString("Reticula"))
//                                .add("Egresado", rs.getString("Egresado"))
                                .build();
                        break;
                    case 2:
                        objetoJson=Json.createObjectBuilder()
                                .add("userType", "Maestro")
                                .add("userID", rs.getString("NumFicha"))
                                .add("apeidop", rs.getString("ApeidoP"))
                                .add("apeidom", rs.getString("ApeidoM"))
                                .add("nombre", rs.getString("Nombre"))
                                .build();
                        break;
                    case 3:
                        String idpre="";
                        if(rs.getString("IDPreinscripcion")!=null){
                            idpre=rs.getString("IDPreinscripcion");
                        }
                        objetoJson=Json.createObjectBuilder()
                                .add("userType", "Aspirante")
                                .add("userID", rs.getString("CURP"))
                                .add("apeidop", rs.getString("ApeidoP"))
                                .add("apeidom", rs.getString("ApeidoM"))
                                .add("nombre", rs.getString("Nombre"))
                                .add("idpreinscripcion",idpre)
                                .build();
                        break;
                }
                connection.close();
                return objetoJson.toString();
            } else {
                connection.close();
                return "Acceso denegado, no hubo de regreso";
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Acceso denegado";
    }

    /**
     * PUT method for updating or creating an instance of LoginWS
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putJson(String content) {
    }
}
