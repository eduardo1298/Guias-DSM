package Model;

public class ListItem {
    private String asignatura;
    private String codigo;

    public ListItem(String asignatura, String codigo) {
        this.asignatura = asignatura;
        this.codigo = codigo;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
