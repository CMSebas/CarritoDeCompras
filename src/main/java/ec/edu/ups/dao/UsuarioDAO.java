package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;
/**
 * Interfaz que define las operaciones de acceso a datos para entidades de tipo {@link Usuario}.
 *
 * Es parte del patrón DAO (Data Access Object) y permite abstraer el acceso a diferentes
 * mecanismos de almacenamiento como archivos, memoria o bases de datos.
 *
 * Incluye operaciones de autenticación, búsqueda, creación, actualización, eliminación y
 * filtrado por rol.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public interface UsuarioDAO {

    Usuario autenticar(String username, String contrasenia);

    void crear(Usuario usuario);

    Usuario buscarPorUsername(String username);

    void eliminar(String username);

    void actualizar(Usuario usuario);

    List<Usuario> listarTodos();

    List<Usuario> listarPorRol(Rol rol);

}
