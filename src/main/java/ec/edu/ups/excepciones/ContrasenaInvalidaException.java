package ec.edu.ups.excepciones;
/**
 * Excepción personalizada que se lanza cuando una contraseña no cumple
 * con los criterios de validación definidos por el sistema.
 *
 * Esta clase extiende de {@link RuntimeException}, lo que la convierte
 * en una excepción no verificada (unchecked).
 *
 * Puede utilizarse para validar contraseñas vacías, débiles, mal formateadas,
 * o que no cumplan políticas de seguridad.
 *
 * Ejemplo de uso:
 * <pre>
 *     if (!contrasena.matches("^(?=.*[A-Z])(?=.*\\d).{8,}$")) {
 *         throw new ContrasenaInvalidaException("La contraseña debe tener al menos 8 caracteres, una mayúscula y un número.");
 *     }
 * </pre>
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 */
public class ContrasenaInvalidaException extends RuntimeException {
    public ContrasenaInvalidaException(String message) {
        super(message);
    }
}
