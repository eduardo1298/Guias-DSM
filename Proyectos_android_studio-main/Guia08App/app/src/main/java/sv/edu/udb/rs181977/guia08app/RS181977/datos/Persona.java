package sv.edu.udb.rs181977.guia08app.RS181977.datos;

public class Persona {
    private String dui;
    private String nombre;
    private String fecha;
    private String peso;
    private String altura;
    private String genero;
    String key;

    public Persona() {
    }

    public Persona(String dui, String nombre, String fecha, String peso, String altura, String genero) {
        this.dui = dui;
        this.nombre = nombre;
        this.fecha = fecha;
        this.peso = peso;
        this.altura = altura;
        this.genero = genero;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
