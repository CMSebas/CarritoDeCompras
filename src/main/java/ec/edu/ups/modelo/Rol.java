package ec.edu.ups.modelo;
/**
 * Enum {@code Rol} representa los diferentes tipos de roles
 * que puede tener un usuario en el sistema.
 *
 * Los roles definen los permisos y accesos que cada usuario tiene.
 * Por defecto, se incluyen:
 * <ul>
 *   <li>{@link #ADMINISTRADOR}: tiene acceso completo a todas las funcionalidades.</li>
 *   <li>{@link #USUARIO}: tiene acceso limitado a las funcionalidades básicas del sistema.</li>
 * </ul>
 *
 * Este enum es utilizado en la clase {@link ec.edu.ups.modelo.Usuario}.
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public enum Rol {
    /**
     * Rol con privilegios de administración total.
     */
    ADMINISTRADOR,
    /**
     * Rol estándar con permisos limitados.
     */
    USUARIO
}

