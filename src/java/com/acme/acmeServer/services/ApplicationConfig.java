/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmeServer.services;

import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

/**
 *
 * @author Fernando
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.acme.acmeServer.services.Get_areas_libros.class);
        resources.add(com.acme.acmeServer.services.Get_calificacion.class);
        resources.add(com.acme.acmeServer.services.Get_inscripcion.class);
        resources.add(com.acme.acmeServer.services.Get_libro.class);
        resources.add(com.acme.acmeServer.services.Get_listas.class);
        resources.add(com.acme.acmeServer.services.Get_preinscripcion.class);
        resources.add(com.acme.acmeServer.services.LoginWS.class);
        resources.add(com.acme.acmeServer.services.PreinscripcionResource.class);
        resources.add(com.acme.acmeServer.services.Set_calificaciones.class);
        resources.add(com.acme.acmeServer.services.get_grupos_maestro.class);
    }
}
