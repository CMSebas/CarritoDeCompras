package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {

    private final String rutaArchivo;
    private List<Usuario> usuarios;


    public UsuarioDAOTexto(String rutaArchivo) {
        this.rutaArchivo=rutaArchivo;
        usuarios = new ArrayList<>();
        cargarDesdeArchivo();
        verificarAdmin();
    }

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

    private void verificarAdmin() {
        boolean existeAdmin = false;
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase("admin")) {
                existeAdmin = true;
                break;
            }
        }

        if (!existeAdmin) {
            Usuario admin = new Usuario("Administrador", "Sistema", "admin", "Admin@", Rol.ADMINISTRADOR);
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

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        Usuario usuario = buscarPorUsername(username);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

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
