/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.appAndroid.model;

/**
 *
 * @author Yaelo
 */
public class CalifMaterias {
    public String Materia;
    public String Calificacion;
    public CalifMaterias(String mat, String calif){
        Materia=mat;
        Calificacion=calif;
    }
    public String getMateria(){
        return Materia;
    }
    public String getCalif(){
        return Calificacion;
    }
}
