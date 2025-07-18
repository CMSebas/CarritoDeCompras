package ec.edu.ups.modelo;

import java.io.Serializable;

public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String texto;

    public Pregunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getClaveInternacionalizada() {
        return "pregunta." + id;
    }

    @Override
    public String toString() {
        return texto;
    }


}
