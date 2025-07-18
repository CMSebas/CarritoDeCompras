package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * La clase {@code MensajeInternacionalizacionHandler} se encarga de manejar la
 * carga y el acceso a los mensajes internacionalizados desde un archivo de propiedades
 * llamado {@code mensajes.properties}.
 *
 * <p>Permite cambiar dinámicamente el idioma y país de la localización durante la ejecución.</p>
 *
 * Ejemplo de uso:
 * <pre>
 *     MensajeInternacionalizacionHandler handler = new MensajeInternacionalizacionHandler("es", "EC");
 *     String mensaje = handler.get("saludo");
 * </pre>
 *
 * @author Sebastian Ceron
 * @version 1.0
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle bundle;
    private Locale locale;
    /**
     * Constructor que inicializa el manejador de mensajes con un idioma y país específicos.
     *
     * @param lenguaje El código del lenguaje (por ejemplo, "es" para español, "en" para inglés).
     * @param pais     El código del país (por ejemplo, "EC" para Ecuador, "US" para Estados Unidos).
     */
    public MensajeInternacionalizacionHandler(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }
    /**
     * Retorna el mensaje asociado a la clave especificada desde el archivo de propiedades.
     *
     * @param key La clave del mensaje.
     * @return El mensaje localizado correspondiente a la clave.
     */
    public String get(String key) {
        return bundle.getString(key);
    }
    /**
     * Cambia el lenguaje y país de la localización en tiempo de ejecución.
     *
     * @param lenguaje Nuevo código de lenguaje.
     * @param pais     Nuevo código de país.
     */
    public void setLenguaje(String lenguaje, String pais) {
        this.locale = new Locale(lenguaje, pais);
        this.bundle = ResourceBundle.getBundle("mensajes", locale);
    }

    public Locale getLocale() {
        return locale;
    }
}