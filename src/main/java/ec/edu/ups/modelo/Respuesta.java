package ec.edu.ups.modelo;

import java.io.Serializable;

public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pregunta pregunta;
    private Usuario usuario;
    private String texto;


    public Respuesta(Pregunta pregunta, Usuario usuario, String texto) {
        this.pregunta = pregunta;
        this.usuario = usuario;
        this.texto = texto;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
