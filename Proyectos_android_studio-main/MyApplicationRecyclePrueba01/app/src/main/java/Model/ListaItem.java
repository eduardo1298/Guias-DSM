package Model;

import java.security.PrivateKey;

public class ListaItem {

    private String asignatura;
    private String codigo;
    private Integer uv;
    private String prerequisito;

    public ListaItem(String asignatura, String codigo, int uv, String prerequisito) {
        this.asignatura = asignatura;
        this.codigo = codigo;
        this.uv = uv;
        this.prerequisito = prerequisito;
    }


    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public String getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(String prerequisito) {
        this.prerequisito = prerequisito;
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

    public void setCodigo(String descripcion) {
        this.codigo = descripcion;
    }
}



