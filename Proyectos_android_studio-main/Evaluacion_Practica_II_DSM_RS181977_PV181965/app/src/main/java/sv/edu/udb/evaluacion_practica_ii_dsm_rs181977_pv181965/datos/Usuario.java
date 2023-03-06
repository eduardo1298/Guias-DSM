package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos;

public class Usuario {
    private String Nombre;
    private String Correo;
    private String Rol;
    String key;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String rol) {
        Nombre = nombre;
        Correo = correo;
        Rol = rol;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
