package com.edu.sv.guia10.models;

import com.google.gson.annotations.SerializedName;

public class Producto {
    @SerializedName("codigo")
    private String codigo;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("precio")
    private float precio;

    public Producto(String codigo, String descripcion, float precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
