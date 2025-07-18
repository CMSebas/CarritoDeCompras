package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;

import java.util.List;
/**
 * Interfaz que define las operaciones para gestionar objetos de tipo {@link Respuesta},
 * utilizadas en el sistema como parte del mecanismo de recuperación de contraseña.
 *
 * Forma parte del patrón DAO (Data Access Object) y puede ser implementada
 * en almacenamiento en memoria, archivos binarios, bases de datos, etc.
 *
 * Cada respuesta está asociada a una {@link ec.edu.ups.modelo.Pregunta} y a un {@link Usuario}.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public interface RespuestaDAO {
    void guardarRespuesta(Respuesta respuesta);
    List<Respuesta> buscarPorUsuario(Usuario usuario);  // ✅ ahora recibe Usuario
}
