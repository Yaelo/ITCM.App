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
public class Libro {
    private String Isbn;
    private String Titulo;
    private String Autor;
    private String Editorial;
    public Libro(){}
    public Libro(String tit,String aut){
        Titulo=tit;
        Autor=aut;
    }
    public Libro(String isbn,String titulo, String autor, String editorial){
        Isbn = isbn;
        Titulo=titulo;
        Autor=autor;
        Editorial=editorial;
    }
    public void setIsbn(String in){
        this.Isbn= in;
    }
    public void setTitulo(String in){
        this.Titulo= in;
    }
    public void setAutor(String in){
        this.Autor=in;
    }
    public void setEditorial(String in){
        this.Editorial=in;
    }
    public String getIsbn(){
        return Isbn;
    }
    public String getTitulo(){
        return Titulo;
    }
    public String getAutor(){
        return Autor;
    }
    public String getEditorial(){
        return Editorial;
    }
}
