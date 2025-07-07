package ec.edu.ups.modelo;

public class Respuesta {
    private int idPregunta;
    private String texto;
    private String usuario;

    public Respuesta(int idPregunta, String texto, String usuario) {
        this.idPregunta = idPregunta;
        this.texto = texto;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
