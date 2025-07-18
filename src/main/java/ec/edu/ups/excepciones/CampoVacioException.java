package ec.edu.ups.excepciones;
/**
 * Excepción personalizada que se lanza cuando un campo obligatorio
 * se encuentra vacío durante una validación en el sistema.
 *
 * Esta excepción extiende de {@link RuntimeException}, por lo que
 * es una excepción no verificada (unchecked).
 *
 * Puede ser utilizada para validar entradas de formularios,
 * como nombres, contraseñas, precios, etc.
 *
 * Ejemplo de uso:
 * <pre>
 *     if (nombre == null || nombre.isBlank()) {
 *         throw new CampoVacioException("El nombre no puede estar vacío.");
 *     }
 * </pre>
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 */
public class CampoVacioException extends RuntimeException {
    public CampoVacioException(String message) {
        super(message);
    }
}
