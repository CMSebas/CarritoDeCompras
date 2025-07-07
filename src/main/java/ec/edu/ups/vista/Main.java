package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.LoginController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    private static final ProductoDAO productoDAO = new ProductoDAOMemoria();
    private static final CarritoDAO carritoDAO = new CarritoDAOMemoria();
    private static final UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
    private static final PreguntaDAO preguntaDAO = new PreguntaDAOMemoria();
    private static final RespuestaDAO respuestaDAO = new RespuestaDAOMemoria();
    public static final MensajeInternacionalizacionHandler mensajes = new MensajeInternacionalizacionHandler("es", "EC");



    public static void main(String[] args) {
        mostrarLogin();
    }

    public static void mostrarLogin() {
        LoginView loginView = new LoginView();
        UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView,mensajes );
        loginView.cambiarIdiomaTexto(Main.mensajes);

        loginView.setVisible(true);
        Locale localizacion = new Locale("es", "EC");





        loginView.getBtnRegistrate().addActionListener(e -> {
            LoginCrearUsuario registroView = new LoginCrearUsuario(preguntaDAO);


            registroView.cambiarIdiomaTexto(Main.mensajes);
            registroView.cargarPreguntasConIdioma(preguntaDAO.listarTodas(), Main.mensajes);

            registroView.setVisible(true);

            registroView.getBtnCrear().addActionListener(ev -> {

                String nombre = registroView.getTxtNombre().getText().trim();
                String apellido = registroView.getTxtApellido().getText().trim();
                String username = registroView.getTxtUsuario().getText().trim();
                String contrasena = registroView.getTxtContrasena().getText().trim();

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    registroView.mostrarMensaje("El usuario ya existe");
                    return;
                }

                Usuario nuevo = new Usuario(nombre, apellido, username, contrasena, Rol.USUARIO);
                usuarioDAO.crear(nuevo);


                for (int i = 1; i <= 3; i++) {
                    int idPregunta = registroView.getIdPreguntaSeleccionada(i);
                    String respuestaTexto = registroView.getRespuestaEscrita(i);
                    respuestaDAO.guardarRespuesta(new Respuesta(idPregunta, username, respuestaTexto));
                }



                registroView.dispose();
                JOptionPane.showMessageDialog(loginView, Main.mensajes.get("main.mensaje"));
            });
        });
        loginView.getBtnRecuperar().addActionListener(e -> {
            LoginRecuperar recuperarView = new LoginRecuperar();
            recuperarView.cambiarIdiomaTexto(Main.mensajes);
            recuperarView.setVisible(true);

            LoginController loginController = new LoginController(usuarioDAO, preguntaDAO, respuestaDAO, recuperarView);
        });


        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                if (usuarioAutenticado != null) {
                    mostrarMenu(usuarioAutenticado);
                }
            }
        });
    }

    public static void mostrarMenu(Usuario usuario) {
        MenuPrincipalView menuView = new MenuPrincipalView(usuario);


        ProductoAnadirView productoAnadirView = new ProductoAnadirView();
        ProductoEliminar productoEliminar = new ProductoEliminar();
        ProductoActualizar productoActualizar = new ProductoActualizar();
        ProductoListaView productoListaView = new ProductoListaView();
        CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
        CarritoBuscar carritoBuscar = new CarritoBuscar();
        CarritoActualizar carritoActualizar = new CarritoActualizar();
        CarritoListarUsuario carritoListarUsuario = new CarritoListarUsuario();
        CarritoListarTodos carritoListarTodos = new CarritoListarTodos();
        CarritoEliminar carritoEliminar = new CarritoEliminar();
        CarritoEliminarItems carritoEliminarItems = new CarritoEliminarItems();
        carritoActualizar.cambiarIdiomaTexto(mensajes);


        ProductoController productoController = new ProductoController(
                productoDAO, productoAnadirView, productoListaView,
                productoActualizar, productoEliminar, carritoAnadirView, carritoDAO,mensajes
        );
        CarritoController carritoController = new CarritoController(
                carritoDAO, productoDAO, carritoAnadirView, usuario,
                carritoBuscar, carritoListarUsuario, carritoActualizar,
                carritoListarTodos, carritoEliminar, carritoEliminarItems,
                mensajes
        );

        // Listeners
        menuView.getMenuItemCrearProducto().addActionListener(e -> {
            if (!productoAnadirView.isVisible()) {
                if (productoAnadirView.getParent() == null) {
                    menuView.getjDesktopPane().add(productoAnadirView);
                }
                productoAnadirView.cambiarIdiomaTexto(Main.mensajes);
                productoAnadirView.setVisible(true);
            }
        });
        menuView.getMenuItemEspanol().addActionListener(e -> {
            carritoController.setMonedaActual("USD");
            Main.mensajes.setLenguaje("es", "EC");
            carritoController.cambiarMoneda(new Locale("es", "EC"));
            mensajes.setLenguaje("es","EC");
            carritoController.getCarritoAnadirView().cambiarIdiomaTexto(Main.mensajes);
            carritoActualizar.cambiarIdiomaTexto(Main.mensajes);
            carritoBuscar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminarItems.cambiarIdiomaTexto(Main.mensajes);
            carritoListarTodos.cambiarIdiomaTexto(Main.mensajes);
            carritoListarUsuario.cambiarIdiomaTexto(Main.mensajes);
            productoActualizar.cambiarIdiomaTexto(Main.mensajes);
            productoAnadirView.cambiarIdiomaTexto(Main.mensajes);
            productoEliminar.cambiarIdiomaTexto(Main.mensajes);
            productoListaView.cambiarIdiomaTexto(Main.mensajes);
            menuView.configurarTextosMenus(Main.mensajes);



            JOptionPane.showMessageDialog(menuView, "Moneda cambiada a Dólares ($)");
        });

        menuView.getMenuItemIngles().addActionListener(e -> {
            carritoController.setMonedaActual("USD");
            Main.mensajes.setLenguaje("en", "US");
            productoController.setMonedaActual("USD");
            carritoController.cambiarMoneda(new Locale("es", "EC"));
            productoController.actualizarVistaLista();
            mensajes.setLenguaje("en", "US"); // cambia el idioma del handler
            carritoController.getCarritoAnadirView().cambiarIdiomaTexto(Main.mensajes); // actualiza textos
            carritoActualizar.cambiarIdiomaTexto(Main.mensajes);
            carritoBuscar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminarItems.cambiarIdiomaTexto(Main.mensajes);
            carritoListarTodos.cambiarIdiomaTexto(Main.mensajes);
            carritoListarUsuario.cambiarIdiomaTexto(Main.mensajes);
            productoActualizar.cambiarIdiomaTexto(Main.mensajes);
            productoAnadirView.cambiarIdiomaTexto(Main.mensajes);
            productoEliminar.cambiarIdiomaTexto(Main.mensajes);
            productoListaView.cambiarIdiomaTexto(Main.mensajes);
            menuView.configurarTextosMenus(Main.mensajes);



            JOptionPane.showMessageDialog(menuView, "Language switched to English");
            JOptionPane.showMessageDialog(menuView, "Currency set to Dollars ($)");


        });

        menuView.getMenuItemFrances().addActionListener(e -> {
            Main.mensajes.setLenguaje("fr", "FR");
            carritoController.setMonedaActual("EUR");
            productoController.setMonedaActual("EUR");
            productoController.actualizarVistaLista();
            mensajes.setLenguaje("fr", "FR");
            carritoController.getCarritoAnadirView().cambiarIdiomaTexto(Main.mensajes);



            carritoController.cambiarMoneda(new Locale("fr", "FR"));
            carritoActualizar.cambiarIdiomaTexto(Main.mensajes);
            carritoBuscar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminar.cambiarIdiomaTexto(Main.mensajes);
            carritoEliminarItems.cambiarIdiomaTexto(Main.mensajes);
            carritoListarTodos.cambiarIdiomaTexto(Main.mensajes);
            carritoListarUsuario.cambiarIdiomaTexto(Main.mensajes);
            productoActualizar.cambiarIdiomaTexto(Main.mensajes);
            productoAnadirView.cambiarIdiomaTexto(Main.mensajes);
            productoEliminar.cambiarIdiomaTexto(Main.mensajes);
            productoListaView.cambiarIdiomaTexto(Main.mensajes);
            menuView.configurarTextosMenus(Main.mensajes);




            JOptionPane.showMessageDialog(menuView, "Devise changée en Euro (€)");

            MenuPrincipalView.setIdioma("FR");
            for (JInternalFrame frame : menuView.getjDesktopPane().getAllFrames()) {
                frame.setVisible(false); // en lugar de dispose()
            }

        });

        menuView.getMenuItemCrearUsuario().addActionListener(e -> {
            LoginCrearUsuario crearView = new LoginCrearUsuario(preguntaDAO);
            crearView.cambiarIdiomaTexto(Main.mensajes); // ya debes tener este
            crearView.cargarPreguntasConIdioma(preguntaDAO.listarTodas(), Main.mensajes);

            crearView.setVisible(true);

            crearView.getBtnCrear().addActionListener(ev -> {
                String nombre = crearView.getTxtNombre().getText().trim();
                String apellido = crearView.getTxtApellido().getText().trim();
                String username = crearView.getTxtUsuario().getText().trim();
                String contrasena = crearView.getTxtContrasena().getText().trim();

                if (usuarioDAO.buscarPorUsername(username) != null) {
                    crearView.mostrarMensaje("⚠️ El usuario ya existe");
                    return;
                }

                Usuario nuevo = new Usuario(nombre, apellido, username, contrasena, Rol.USUARIO);
                usuarioDAO.crear(nuevo);

                for (int i = 1; i <= 3; i++) {
                    int idPregunta = crearView.getIdPreguntaSeleccionada(i);
                    String respuestaTexto = crearView.getRespuestaEscrita(i);
                    respuestaDAO.guardarRespuesta(new Respuesta(idPregunta, username, respuestaTexto));
                }

                crearView.dispose();
                JOptionPane.showMessageDialog(menuView, Main.mensajes.get("main.mensaje"));
            });
        });


        menuView.getMenuItemBuscarCarrito().addActionListener(e -> {
            if (!carritoBuscar.isVisible()) {
                if (carritoBuscar.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoBuscar);
                }
                carritoBuscar.cambiarIdiomaTexto(Main.mensajes);

                carritoBuscar.setVisible(true);
            }
        });

        menuView.getMenuItemEliminarCarrito().addActionListener(e -> {
            if (!carritoEliminar.isVisible()) {
                if (carritoEliminar.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoEliminar);
                }
                 carritoEliminar.cambiarIdiomaTexto(Main.mensajes);

                carritoEliminar.setVisible(true);
                carritoController.cargarTodosCarritosEnEliminar();
            }
        });

        menuView.getMenuItemEliminarProducto().addActionListener(e -> {
            if (!productoEliminar.isVisible()) {
                if (productoEliminar.getParent() == null) {
                    menuView.getjDesktopPane().add(productoEliminar);
                }
                productoEliminar.cambiarIdiomaTexto(Main.mensajes);

                productoEliminar.setVisible(true);
            }
        });

        menuView.getMenuItemListarTodosCarritos().addActionListener(e -> {
            if (!carritoListarTodos.isVisible()) {
                if (carritoListarTodos.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoListarTodos);
                }
                carritoListarTodos.cambiarIdiomaTexto(Main.mensajes);
                carritoListarTodos.setVisible(true);
            }
        });

        menuView.getMenuItemCrearCarrito().addActionListener(e -> {
            if (!carritoAnadirView.isVisible()) {
                if (carritoAnadirView.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoAnadirView);
                }
                carritoAnadirView.cambiarIdiomaTexto(Main.mensajes);

                carritoAnadirView.setVisible(true);
            }
        });
        menuView.getMenuItemEliminarCarritoItems().addActionListener(e -> {
            if (!carritoEliminarItems.isVisible()) {
                if (carritoEliminarItems.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoEliminarItems);
                }
                carritoEliminarItems.cambiarIdiomaTexto(Main.mensajes);

                carritoEliminarItems.setVisible(true);
            }
        });

        menuView.getMenuItemActualizarCarrito().addActionListener(e -> {
            if (!carritoActualizar.isVisible()) {
                if (carritoActualizar.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoActualizar);
                }
                carritoActualizar.cambiarIdiomaTexto(Main.mensajes);

                carritoActualizar.setVisible(true);
            }
        });

        menuView.getMenuItemListarCarritosUsuario().addActionListener(e -> {
            if (!carritoListarUsuario.isVisible()) {
                if (carritoListarUsuario.getParent() == null) {
                    menuView.getjDesktopPane().add(carritoListarUsuario);
                }
                carritoListarUsuario.cambiarIdiomaTexto(Main.mensajes);

                carritoListarUsuario.setVisible(true);
                carritoController.cargarMisCarritos();
            }
        });

        menuView.getMenuItemBuscarProducto().addActionListener(e -> {
            if (!productoListaView.isVisible()) {
                if (productoListaView.getParent() == null) {
                    menuView.getjDesktopPane().add(productoListaView);
                }
                productoListaView.cambiarIdiomaTexto(Main.mensajes);
                productoListaView.setVisible(true);
            }
        });
        menuView.getMenuItemActualizarProducto().addActionListener(e -> {
            if (!productoActualizar.isVisible()) {
                if (productoActualizar.getParent() == null) {
                    menuView.getjDesktopPane().add(productoActualizar);
                }
                productoActualizar.cambiarIdiomaTexto(Main.mensajes);
                productoActualizar.setVisible(true);
            }
        });


        menuView.getMenuItemCerrarSesion().addActionListener(e -> {
            menuView.dispose();
            mostrarLogin();
        });
    }
}
