package ec.edu.ups.modelo;

public class Pregunta {
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
