package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos;

public class Examen {
    private String Nombre;
    private String Codigo;
    String key;

    public Examen() {
    }

    public Examen(String nombre, String codigo) {
        Nombre = nombre;
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
