package sv.edu.udb.tareainvestigacion2_viewbinding.datos;

public class Estudiante {
    private String Nombres;
    private String Apellidos;
    private String Carnet;
    private String ProgramaEstudio;
    private String Correo;
    private String Telefono;
    String key;

    public Estudiante() {
    }

    public Estudiante(String nombres, String apellidos, String carnet, String programaEstudio, String correo, String telefono) {
        Nombres = nombres;
        Apellidos = apellidos;
        Carnet = carnet;
        ProgramaEstudio = programaEstudio;
        Correo = correo;
        Telefono = telefono;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String carnet) {
        Carnet = carnet;
    }

    public String getProgramaEstudio() {
        return ProgramaEstudio;
    }

    public void setProgramaEstudio(String programaEstudio) {
        ProgramaEstudio = programaEstudio;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
