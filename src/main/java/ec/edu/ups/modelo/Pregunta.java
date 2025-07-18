package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Representa una pregunta de seguridad seleccionada por el usuario
 * durante el proceso de registro o recuperación de contraseña.
 *
 * Esta clase implementa {@link Serializable} para permitir su almacenamiento
 * en archivos binarios o su uso en sesiones persistentes.
 *
 * También permite obtener una clave internacionalizable para traducir el texto
 * según el idioma activo del sistema.
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String texto;
    /**
     * Constructor que permite inicializar la pregunta con un ID y su texto.
     *
     * @param id    Identificador único de la pregunta
     * @param texto Texto descriptivo de la pregunta
     */
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
