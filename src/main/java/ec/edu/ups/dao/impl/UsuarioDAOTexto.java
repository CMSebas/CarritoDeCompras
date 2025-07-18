package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link UsuarioDAO} que gestiona los usuarios
 * utilizando un archivo de texto como almacenamiento persistente.
 *
 * Cada usuario se guarda en una línea con formato: nombre;apellido;username;contraseña;rol
 * Se asegura de que exista un usuario administrador por defecto ("admin").
 *
 * Esta clase soporta operaciones CRUD y autenticación de usuarios.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class UsuarioDAOTexto implements UsuarioDAO {

    private final String rutaArchivo;
    private List<Usuario> usuarios;

    /**
     * Constructor que recibe la ruta del archivo de texto donde se almacenarán los usuarios.
     * Carga los usuarios existentes y verifica que exista un administrador por defecto.
     *
     * @param rutaArchivo Ruta del archivo (ej. "usuarios.txt")
     */
    public UsuarioDAOTexto(String rutaArchivo) {
        this.rutaArchivo=rutaArchivo;
        usuarios = new ArrayList<>();
        cargarDesdeArchivo();
        verificarAdmin();
    }
    /**
     * Carga los usuarios desde el archivo al inicializar el DAO.
     */
    private void cargarDesdeArchivo() {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 5) {
                    String username = partes[0];
                    String nombre = partes[1];
                    String apellido = partes[2];
                    String contrasena = partes[3];
                    Rol rol = Rol.valueOf(partes[4]);
                    usuarios.add(new Usuario(nombre, apellido, username, contrasena, rol));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios desde archivo: " + e.getMessage());
        }
    }
    /**
     * Verifica si existe un usuario administrador. Si no, crea uno por defecto.
     */
    private void verificarAdmin() {
        boolean existeAdmin = false;
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase("admin")) {
                existeAdmin = true;
                break;
            }
        }

        if (!existeAdmin) {
            Usuario admin = new Usuario("Administrador", "Sistema", "0107529729", "Admin@", Rol.ADMINISTRADOR);
            usuarios.add(admin);

            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                escritor.write(admin.getUsername() + ";" +
                        admin.getNombre() + ";" +
                        admin.getApellido() + ";" +
                        admin.getContrasenia() + ";" +
                        admin.getRol() + "\n");
            } catch (IOException e) {
                System.out.println("Error al guardar usuario admin: " + e.getMessage());
            }
        }
    }
    /**
     * Autentica un usuario por su nombre de usuario y contraseña.
     *
     * @param username Nombre de usuario
     * @param contrasenia Contraseña
     * @return Usuario autenticado o {@code null} si no coincide
     */
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }
    /**
     * Registra un nuevo usuario en el archivo.
     *
     * @param usuario Usuario a crear
     */
    @Override
    public void crear(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(usuario.getNombre() + ";" + usuario.getApellido() + ";" + usuario.getUsername()
                    + ";" + usuario.getContrasenia() + ";" + usuario.getRol().name());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }
    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return Usuario encontrado o {@code null}
     */
    @Override
    public Usuario buscarPorUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5 && partes[2].equals(username)) {
                    return new Usuario(partes[0], partes[1], partes[2], partes[3], Rol.valueOf(partes[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return null;
    }
    /**
     * Elimina un usuario por su nombre de usuario.
     *
     * @param username Usuario a eliminar
     */
    @Override
    public void eliminar(String username) {
        List<Usuario> todos = listarTodos();

        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getUsername().equals(username)) {
                todos.remove(i);
                break;
            }
        }

        guardarTodos(todos);
    }
    /**
     * Actualiza la información de un usuario existente por su nombre de usuario.
     *
     * @param usuario Usuario actualizado
     */
    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getUsername().equals(usuario.getUsername())) {
                todos.set(i, usuario);
                break;
            }
        }
        guardarTodos(todos);
    }
    /**
     * Lista todos los usuarios registrados en el archivo.
     *
     * @return Lista de usuarios
     */
    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 5) {
                    usuarios.add(new Usuario(partes[0], partes[1], partes[2], partes[3], Rol.valueOf(partes[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }
    /**
     * Lista los usuarios que tienen un rol específico.
     *
     * @param rol Rol a filtrar (ej. ADMINISTRADOR, USUARIO)
     * @return Lista de usuarios con ese rol
     */
    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listarTodos()) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
    /**
     * Guarda en el archivo la lista completa de usuarios sobrescribiéndolo por completo.
     *
     * @param usuarios Lista actualizada de usuarios
     */
    private void guardarTodos(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Usuario u : usuarios) {
                writer.write(u.getNombre() + ";" + u.getApellido() + ";" + u.getUsername()
                        + ";" + u.getContrasenia() + ";" + u.getRol().name());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar todos los usuarios: " + e.getMessage());
        }
    }
}
