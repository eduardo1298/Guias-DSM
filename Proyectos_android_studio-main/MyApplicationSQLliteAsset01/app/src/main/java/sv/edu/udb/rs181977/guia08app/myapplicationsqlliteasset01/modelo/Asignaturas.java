package sv.edu.udb.rs181977.guia08app.myapplicationsqlliteasset01.modelo;

public class Asignaturas {
    private int id;
    private String codigo;
    private String nombre;
    private int uv;

    public Asignaturas(int id, String codigo, String nombre, int uv) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.uv = uv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }
}
