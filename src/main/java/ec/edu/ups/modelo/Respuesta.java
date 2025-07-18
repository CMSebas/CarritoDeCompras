package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Representa una respuesta a una pregunta de seguridad asociada a un usuario.
 *
 * Esta clase es utilizada en el sistema para permitir la recuperación de contraseña
 * o validaciones de seguridad mediante preguntas personalizadas.
 * Implementa {@link Serializable} para facilitar su almacenamiento en archivos binarios.
 *
 */
public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Pregunta pregunta;
    private Usuario usuario;
    private String texto;

    /**
     * Crea una nueva respuesta asociada a una pregunta y a un usuario.
     *
     * @param pregunta La pregunta de seguridad respondida
     * @param usuario El usuario que respondió la pregunta
     * @param texto La respuesta escrita por el usuario
     */
    public Respuesta(Pregunta pregunta, Usuario usuario, String texto) {
        this.pregunta = pregunta;
        this.usuario = usuario;
        this.texto = texto;
    }
    /**
     * Obtiene la pregunta de seguridad asociada a esta respuesta.
     *
     * @return La pregunta correspondiente
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta de seguridad asociada a esta respuesta.
     *
     * @param pregunta La nueva pregunta a asociar
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    /**
     * Obtiene el usuario que proporcionó esta respuesta.
     *
     * @return El usuario que respondió
     */
    public Usuario getUsuario() {
        return usuario;
    }
    /**
     * Establece el usuario que proporcionó esta respuesta.
     *
     * @param usuario El nuevo usuario a asociar
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Obtiene el texto de la respuesta.
     *
     * @return El texto escrito como respuesta
     */
    public String getTexto() {
        return texto;
    }
    /**
     * Establece el texto de la respuesta.
     *
     * @param texto El nuevo texto de la respuesta
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
