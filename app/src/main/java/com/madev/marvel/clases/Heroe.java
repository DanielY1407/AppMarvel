package com.madev.marvel.clases;

public class Heroe {


    private String id;
    private String nombre;
    private String descripcion;
    private String modificacion;
    private String miniatura;

    public Heroe(String id, String nombre, String descripcion, String modificacion, String miniatura) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.modificacion = modificacion;
        this.miniatura = miniatura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModificacion() {
        return modificacion;
    }

    public void setModificacion(String modificacion) {
        this.modificacion = modificacion;
    }

    public String getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(String miniatura) {
        this.miniatura = miniatura;
    }
}