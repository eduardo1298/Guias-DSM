package sv.edu.udb.rs181977.myapplicationfirebase08_marzo_2022.Modelo;

import java.sql.Timestamp;

public class Blog {
    public String titulo;
    public String descripcion;
    public String imagen;
    public String fechahora;
    public String idusuario;

    public Blog(String titulo, String descripcion, String imagen, String fechahora, String idusuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechahora = fechahora;
        this.idusuario = idusuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
}
