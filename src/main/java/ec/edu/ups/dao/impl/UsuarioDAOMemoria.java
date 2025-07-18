package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de {@link UsuarioDAO} que gestiona objetos {@link Usuario}
 * utilizando una lista en memoria.
 *
 * Esta clase es útil para pruebas o entornos donde no se requiere persistencia en disco.
 * Al iniciarse, precarga dos usuarios por defecto: un administrador y un usuario común.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class UsuarioDAOMemoria implements UsuarioDAO {

    private List<Usuario> usuarios;
    /**
     * Constructor que inicializa la lista de usuarios.
     * Crea dos usuarios por defecto: "admin" y "user".
     */
    public UsuarioDAOMemoria() {
        usuarios = new ArrayList<Usuario>();
        crear(new Usuario("Sebastian","Ceron", "0107529729", "Rafa@q", Rol.ADMINISTRADOR));
        crear(new Usuario("DEFECTO","DEFECTO","user", "1234", Rol.USUARIO));
    }
    /**
     * Autentica un usuario verificando su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario
     * @param contrasenia Contraseña
     * @return El usuario autenticado o {@code null} si no coincide
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if(usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)){
                return usuario;
            }
        }
        return null;
    }



    /**
     * Crea un nuevo usuario y lo agrega a la lista.
     *
     * @param usuario Usuario a registrar
     */
    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }
    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return Usuario encontrado o {@code null} si no existe
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }
    /**
     * Elimina un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUsername().equals(username)) {
                iterator.remove();
                break;
            }
        }
    }
    /**
     * Actualiza los datos de un usuario existente (por nombre de usuario).
     *
     * @param usuario Usuario actualizado
     */
    @Override
    public void actualizar(Usuario usuario) {
        for(int i = 0; i < usuarios.size(); i++){
            Usuario usuarioAux = usuarios.get(i);
            if(usuarioAux.getUsername().equals(usuario.getUsername())){
                usuarios.set(i, usuario);
                break;
            }
        }
    }
    /**
     * Devuelve la lista completa de usuarios registrados en memoria.
     *
     * @return Lista de usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }
    /**
     * Devuelve todos los usuarios que tengan un rol específico.
     *
     * @param rol Rol a filtrar (por ejemplo: ADMINISTRADOR, USUARIO)
     * @return Lista de usuarios con el rol indicado
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals(rol)) {
                usuariosEncontrados.add(usuario);
            }
        }

        return usuariosEncontrados;
    }
}
