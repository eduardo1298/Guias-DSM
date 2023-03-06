package sv.edu.udb.evaluacion_practica_ii_dsm_rs181977_pv181965.datos;

import java.io.Serializable;

public class Pregunta implements Serializable {
    private String Pregunta;
    private String Opcion1;
    private String Opcion2;
    private String Opcion3;
    private String RespuestaCorrecta;
    private String Tipo;
    private String RespuestaSeleccionada;

    String key;
    public Pregunta() {
    }

    public Pregunta(String pregunta, String opcion1, String opcion2, String opcion3, String respuestaCorrecta, String tipo) {
        Pregunta = pregunta;
        Opcion1 = opcion1;
        Opcion2 = opcion2;
        Opcion3 = opcion3;
        RespuestaCorrecta = respuestaCorrecta;
        Tipo = tipo;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }

    public String getOpcion1() {
        return Opcion1;
    }

    public void setOpcion1(String opcion1) {
        Opcion1 = opcion1;
    }

    public String getOpcion2() {
        return Opcion2;
    }

    public void setOpcion2(String opcion2) {
        Opcion2 = opcion2;
    }

    public String getOpcion3() {
        return Opcion3;
    }

    public void setOpcion3(String opcion3) {
        Opcion3 = opcion3;
    }

    public String getRespuestaCorrecta() {
        return RespuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        RespuestaCorrecta = respuestaCorrecta;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getRespuestaSeleccionada() {
        return RespuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(String respuestaSeleccionada) {
        RespuestaSeleccionada = respuestaSeleccionada;
    }
}
