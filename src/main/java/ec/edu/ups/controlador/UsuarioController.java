package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.EliminarUsuario;
import ec.edu.ups.vista.ListarUsuarios;
import ec.edu.ups.vista.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controlador encargado de gestionar operaciones relacionadas con usuarios.
 * Incluye autenticación, eliminación y listado de usuarios, integrando las vistas
 * {@link LoginView}, {@link EliminarUsuario} y {@link ListarUsuarios}.
 *
 * Se conecta con el {@link UsuarioDAO} para realizar operaciones CRUD
 * y maneja los mensajes internacionalizados mediante {@link MensajeInternacionalizacionHandler}.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class UsuarioController {

    private Usuario usuario;
    private final UsuarioDAO usuarioDAO;
    private final LoginView loginView;
    private final MensajeInternacionalizacionHandler mensajes;
    private EliminarUsuario eliminarUsuarioView;
    private ListarUsuarios listarUsuariosView;

    /**
     * Constructor que inicializa el controlador de usuarios con las vistas y el DAO.
     *
     * @param usuarioDAO DAO para manejar usuarios
     * @param loginView Vista de inicio de sesión
     * @param mensajes Manejador de internacionalización de mensajes
     * @param eliminarUsuarioView Vista para eliminar usuarios
     * @param listarUsuariosView Vista para listar usuarios
     */
    public UsuarioController(UsuarioDAO usuarioDAO, LoginView loginView,MensajeInternacionalizacionHandler mensajes,EliminarUsuario eliminarUsuarioView,ListarUsuarios listarUsuariosView) {
        this.usuarioDAO = usuarioDAO;
        this.loginView = loginView;
        this.usuario = null;
        this.mensajes = mensajes;
        this.eliminarUsuarioView = eliminarUsuarioView;
        this.listarUsuariosView = listarUsuariosView;

        configurarEventosEnVistas();
    }

    public ListarUsuarios getListarUsuariosView() {
        return listarUsuariosView;
    }

    public void setListarUsuariosView(ListarUsuarios listarUsuariosView) {
        this.listarUsuariosView = listarUsuariosView;
    }

    public EliminarUsuario getEliminarUsuarioView() {
        return eliminarUsuarioView;
    }

    public void setEliminarUsuarioView(EliminarUsuario eliminarUsuarioView) {
        this.eliminarUsuarioView = eliminarUsuarioView;
    }
    /**
     * Obtiene los datos de todos los usuarios registrados y los convierte
     * en una matriz para mostrar en una tabla.
     *
     * @return Arreglo bidimensional con los datos: nombre, apellido, usuario, rol.
     */
    public Object[][] obtenerDatosUsuariosParaTabla() {
        var usuarios = usuarioDAO.listarTodos();
        Object[][] datos = new Object[usuarios.size()][4];

        for (int i = 0; i < usuarios.size(); i++) {
            var u = usuarios.get(i);
            datos[i][0] = u.getNombre();
            datos[i][1] = u.getApellido();
            datos[i][2] = u.getUsername();
            datos[i][3] = u.getRol().name();
        }
        return datos;
    }
    /**
     * Configura los eventos (listeners) para las vistas conectadas al controlador.
     * Incluye inicio de sesión, eliminación y listado de usuarios.
     */
    private void configurarEventosEnVistas(){
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar();
            }
        });
        eliminarUsuarioView.getjButtonEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = eliminarUsuarioView.getjTxfEliNombre().getText();
                if (usuarioDAO.buscarPorUsername(username) == null) {
                    eliminarUsuarioView.mostrarMensaje("Usuario no encontrado.");
                } else {
                    usuarioDAO.eliminar(username);
                    eliminarUsuarioView.mostrarMensaje("Usuario eliminado correctamente.");
                }
            }
        });
        listarUsuariosView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] datos = obtenerDatosUsuariosParaTabla();
                listarUsuariosView.cargarUsuarios(datos);
            }
        });


    }
    /**
     * Autentica al usuario a partir de los datos ingresados en la vista de login.
     * Si el usuario es válido, cierra la vista; si no, muestra un mensaje de error.
     */
    private void autenticar(){
        String username = loginView.getTxtUsername().getText();
        String contrasenia = loginView.getTxtContrasenia().getText();

        usuario = usuarioDAO.autenticar(username, contrasenia);
        if(usuario == null){
            loginView.mostrarMensaje(mensajes.get("login.error"));
        }else{
            loginView.dispose();
        }
    }
    /**
     * @return Usuario autenticado actualmente o null si no se ha autenticado.
     */
    public Usuario getUsuarioAutenticado(){
        return usuario;
    }
}
