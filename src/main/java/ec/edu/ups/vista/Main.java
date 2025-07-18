package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.LoginController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.excepciones.CampoVacioException;
import ec.edu.ups.excepciones.ContrasenaInvalidaException;
import ec.edu.ups.dao.impl.RespuestaDAOBin;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Clase principal que arranca la aplicación del sistema de carrito de compras.
 * Permite seleccionar el tipo de almacenamiento (memoria o archivos),
 * configura los DAOs respectivos, y gestiona el flujo de ventanas
 * como login, registro, menú principal, e internacionalización.
 *
 * La clase también administra las vistas según el rol del usuario (ADMINISTRADOR o USUARIO).
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class Main {
    private static  CarritoDAO carritoDAO ;
    private static UsuarioDAO usuarioDAO;
    private static ProductoDAO productoDAO;
    private static File carpetaArchivos = null;
    private static String carpetaSeleccionada = "";

    private static  PreguntaDAO preguntaDAO ;
    private static  RespuestaDAO respuestaDAO ;

    public static final MensajeInternacionalizacionHandler mensajes = new MensajeInternacionalizacionHandler("es", "EC");


    /**
     * Método principal que inicia la aplicación.
     * Muestra la vista de selección de almacenamiento y configura los controladores iniciales.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SelecionarAlmView selector = new SelecionarAlmView();

        selector.setVisible(true);

        selector.getBtnMemoria().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selector.dispose();
                configurarDAOs("Memoria");
                mostrarLogin();
            }
        });

        selector.getBtnSeleccionarCarpeta().addActionListener(new ActionListener() {
            @Override
                    public void actionPerformed(ActionEvent e) {
                File carpeta = selector.mostrarSelectorCarpeta();
                if (carpeta != null) {
                    carpetaSeleccionada = carpeta.getAbsolutePath();
                    selector.dispose();
                    configurarDAOs("Texto");
                    mostrarLogin();
                } else {
                    JOptionPane.showMessageDialog(selector, "No se seleccionó carpeta.");
                }
            }
        });
    }
    /**
     * Configura las instancias de los DAO según el tipo de almacenamiento seleccionado (Texto o Memoria).
     *
     * @param tipo Tipo de almacenamiento, puede ser "Texto" o "Memoria".
     */
    private static void configurarDAOs(String tipo) {
        if (tipo.equals("Texto")) {
            usuarioDAO = new UsuarioDAOTexto(carpetaSeleccionada + File.separator + "usuarios.txt");
            productoDAO = new ProductoDAOTexto(carpetaSeleccionada + File.separator + "productos.txt");
            carritoDAO = new CarritoDAOBin(carpetaSeleccionada + File.separator + "carritos.dat");
            respuestaDAO = new RespuestaDAOBin(carpetaSeleccionada + File.separator + "respuestas.dat");
            preguntaDAO = new PreguntaDAOBin(carpetaSeleccionada + File.separator + "preguntas.dat");
        } else {
            usuarioDAO = new UsuarioDAOMemoria();
            productoDAO = new ProductoDAOMemoria();
            carritoDAO = new CarritoDAOMemoria();
            respuestaDAO = new RespuestaDAOMemoria();
            preguntaDAO = new PreguntaDAOMemoria();
        }
    }
    /**
     * Muestra la ventana de login y prepara el controlador de usuario.
     * También permite acceder a las vistas de recuperación y registro de usuario.
     */
    public static void mostrarLogin() {
        LoginView loginView = new LoginView();
        EliminarUsuario eliminarUsuario = new EliminarUsuario();
        ListarUsuarios listarUsuarios = new ListarUsuarios();
        UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView, mensajes, eliminarUsuario, listarUsuarios);
        loginView.cambiarIdiomaTexto(Main.mensajes);

        loginView.setVisible(true);
        Locale localizacion = new Locale("es", "EC");






        loginView.getBtnRegistrate().addActionListener(new ActionListener() {
            @Override
                    public void actionPerformed(ActionEvent e) {
                LoginCrearUsuario registroView = new LoginCrearUsuario(preguntaDAO);


                registroView.cambiarIdiomaTexto(Main.mensajes);
                registroView.cargarPreguntasConIdioma(preguntaDAO.listarTodas(), Main.mensajes);

                registroView.setVisible(true);

                registroView.getBtnCrear().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        try {
                            String nombre = registroView.getTxtNombre().getText();
                            String apellido = registroView.getTxtApellido().getText();
                            String username = registroView.getTxtUsuario().getText();
                            String contrasena = registroView.getTxtContrasena().getText();


                            if (usuarioDAO.buscarPorUsername(username) != null) {
                                throw new Exception("El usuario ya existe.");
                            }

                            if(nombre.isEmpty() || apellido.isEmpty() || username.isEmpty() || contrasena.isEmpty()) {
                                throw new CampoVacioException("Ningun campo puede estar vacio");
                            }


                            Usuario nuevo = new Usuario(nombre, apellido, username, contrasena, Rol.USUARIO);


                            nuevo.validarCedula();
                            nuevo.validarContrasena();


                            usuarioDAO.crear(nuevo);

                            if (usuarioDAO instanceof UsuarioDAOTexto) {
                                try {
                                    FileWriter archivo = new FileWriter("usuarios.txt", true); // true = append
                                    BufferedWriter escritor = new BufferedWriter(archivo);

                                    escritor.write("Usuario: " + nuevo.getUsername() + "\n");
                                    escritor.write("Nombre: " + nuevo.getNombre() + "\n");
                                    escritor.write("Apellido: " + nuevo.getApellido() + "\n");
                                    escritor.write("Contraseña: " + nuevo.getContrasenia() + "\n");
                                    escritor.write("--------------------------\n");

                                    escritor.close();
                                    archivo.close();
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog(null, "Error al guardar en archivo de texto.");
                                }
                            }


                            for (int i = 1; i <= 3; i++) {
                                int idPregunta = registroView.getIdPreguntaSeleccionada(i);
                                String respuestaTexto = registroView.getRespuestaEscrita(i);
                                Pregunta pregunta = preguntaDAO.buscarPorId(idPregunta);
                                respuestaDAO.guardarRespuesta(new Respuesta(pregunta, nuevo, respuestaTexto));
                            }

                            registroView.dispose();
                            JOptionPane.showMessageDialog(registroView, Main.mensajes.get("main.mensaje"));

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(registroView, ex.getMessage());
                        }




                    }
                });
            }
        });
        loginView.getBtnRecuperar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginRecuperar recuperarView = new LoginRecuperar();
                recuperarView.cambiarIdiomaTexto(Main.mensajes);
                recuperarView.setVisible(true);

                LoginController loginController = new LoginController(usuarioDAO, preguntaDAO, respuestaDAO, recuperarView);
            }
        });

        loginView.getBtnRegresar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginView.dispose();
                String[] args = {};
                Main.main(args);
            }
        });


        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                if (usuarioAutenticado != null) {
                    mostrarMenu(usuarioAutenticado, usuarioController);
                }
            }
        });
    }
    /**
     * Muestra la ventana principal (menú) del sistema después de un login exitoso.
     * Configura controladores de productos, carritos y usuarios, así como la internacionalización y acceso según el rol.
     *
     * @param usuario Usuario autenticado.
     * @param usuarioController Instancia del controlador de usuario para acceso a vistas.
     */
    public static void mostrarMenu(Usuario usuario,UsuarioController usuarioController ) {
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
        SelecionarAlmView selecionarAlmView = new SelecionarAlmView();
        carritoActualizar.cambiarIdiomaTexto(mensajes);
        ListarUsuarios usuarioListarView = usuarioController.getListarUsuariosView();



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




        menuView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productoAnadirView.isVisible()) {
                    if (productoAnadirView.getParent() == null) {
                        menuView.getjDesktopPane().add(productoAnadirView);
                    }
                    productoAnadirView.cambiarIdiomaTexto(Main.mensajes);
                    productoAnadirView.setVisible(true);
                }
            }
        });


        menuView.getMenuItemEspanol().addActionListener(new ActionListener() {
            @Override
                    public void actionPerformed(ActionEvent e) {
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
                selecionarAlmView.cambiarIdiomaTexto(Main.mensajes);
                usuarioController.getEliminarUsuarioView().cambiarIdiomaTexto(Main.mensajes);
                usuarioController.getListarUsuariosView().cambiarIdiomaTexto(Main.mensajes);





                JOptionPane.showMessageDialog(menuView, "Moneda cambiada a Dólares ($)");
            }
        });

        menuView.getMenuItemIngles().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                selecionarAlmView.cambiarIdiomaTexto(Main.mensajes);
                productoListaView.cambiarIdiomaTexto(Main.mensajes);
                menuView.configurarTextosMenus(Main.mensajes);
                usuarioController.getEliminarUsuarioView().cambiarIdiomaTexto(Main.mensajes);
                usuarioController.getListarUsuariosView().cambiarIdiomaTexto(Main.mensajes);



                JOptionPane.showMessageDialog(menuView, "Language switched to English");
                JOptionPane.showMessageDialog(menuView, "Currency set to Dollars ($)");
            }
        });

        menuView.getMenuItemFrances().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                selecionarAlmView.cambiarIdiomaTexto(Main.mensajes);
                menuView.configurarTextosMenus(Main.mensajes);
                usuarioController.getEliminarUsuarioView().cambiarIdiomaTexto(Main.mensajes);
                usuarioController.getListarUsuariosView().cambiarIdiomaTexto(Main.mensajes);




                JOptionPane.showMessageDialog(menuView, "Devise changée en Euro (€)");

                MenuPrincipalView.setIdioma("FR");
                for (JInternalFrame frame : menuView.getjDesktopPane().getAllFrames()) {
                    frame.setVisible(false);
                }
            }
        });

        menuView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCrearUsuario crearView = new LoginCrearUsuario(preguntaDAO);
                crearView.cambiarIdiomaTexto(Main.mensajes);
                crearView.cargarPreguntasConIdioma(preguntaDAO.listarTodas(), Main.mensajes);

                crearView.setVisible(true);

                crearView.getBtnCrear().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombre = crearView.getTxtNombre().getText().trim();
                        String apellido = crearView.getTxtApellido().getText().trim();
                        String username = crearView.getTxtUsuario().getText().trim();
                        String contrasena = crearView.getTxtContrasena().getText().trim();

                        if (usuarioDAO.buscarPorUsername(username) != null) {
                            crearView.mostrarMensaje("El usuario ya existe");
                            return;
                        }

                        Usuario nuevo = new Usuario(nombre, apellido, username, contrasena, Rol.USUARIO);
                        usuarioDAO.crear(nuevo);

                        for (int i = 1; i <= 3; i++) {
                            int idPregunta = crearView.getIdPreguntaSeleccionada(i);
                            String respuestaTexto = crearView.getRespuestaEscrita(i);

                            Pregunta pregunta = preguntaDAO.buscarPorId(idPregunta);
                            Respuesta respuesta = new Respuesta(pregunta, nuevo, respuestaTexto);
                            respuestaDAO.guardarRespuesta(respuesta);
                        }

                        crearView.dispose();
                        JOptionPane.showMessageDialog(menuView, Main.mensajes.get("main.mensaje"));
                    }
                });
            }
        });


        menuView.getMenuItemBuscarCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoBuscar.isVisible()) {
                    if (carritoBuscar.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoBuscar);
                    }
                    carritoBuscar.cambiarIdiomaTexto(Main.mensajes);

                    carritoBuscar.setVisible(true);
                }
            }
        });

        menuView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoEliminar.isVisible()) {
                    if (carritoEliminar.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoEliminar);
                    }
                    carritoEliminar.cambiarIdiomaTexto(Main.mensajes);

                    carritoEliminar.setVisible(true);
                    carritoController.cargarTodosCarritosEnEliminar();
                }
            }
        });

        menuView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productoEliminar.isVisible()) {
                    if (productoEliminar.getParent() == null) {
                        menuView.getjDesktopPane().add(productoEliminar);
                    }
                    productoEliminar.cambiarIdiomaTexto(Main.mensajes);

                    productoEliminar.setVisible(true);
                }
            }
        });

        menuView.getMenuItemListarTodosCarritos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoListarTodos.isVisible()) {
                    if (carritoListarTodos.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoListarTodos);
                    }
                    carritoListarTodos.cambiarIdiomaTexto(Main.mensajes);
                    carritoListarTodos.setVisible(true);
                }
            }
        });

        menuView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoAnadirView.isVisible()) {
                    if (carritoAnadirView.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoAnadirView);
                    }
                    carritoAnadirView.cambiarIdiomaTexto(Main.mensajes);

                    carritoAnadirView.setVisible(true);
                }
            }
        });
        menuView.getMenuItemEliminarCarritoItems().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoEliminarItems.isVisible()) {
                    if (carritoEliminarItems.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoEliminarItems);
                    }
                    carritoEliminarItems.cambiarIdiomaTexto(Main.mensajes);

                    carritoEliminarItems.setVisible(true);
                }
            }
        });

        menuView.getMenuItemActualizarCarrito().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoActualizar.isVisible()) {
                    if (carritoActualizar.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoActualizar);
                    }
                    carritoActualizar.cambiarIdiomaTexto(Main.mensajes);

                    carritoActualizar.setVisible(true);
                }
            }
        });

        menuView.getMenuItemListarCarritosUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!carritoListarUsuario.isVisible()) {
                    if (carritoListarUsuario.getParent() == null) {
                        menuView.getjDesktopPane().add(carritoListarUsuario);
                    }
                    carritoListarUsuario.cambiarIdiomaTexto(Main.mensajes);

                    carritoListarUsuario.setVisible(true);
                    carritoController.cargarMisCarritos();
                }
            }
        });

        menuView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productoListaView.isVisible()) {
                    if (productoListaView.getParent() == null) {
                        menuView.getjDesktopPane().add(productoListaView);
                    }
                    productoListaView.cambiarIdiomaTexto(Main.mensajes);
                    productoListaView.setVisible(true);
                }
            }
        });
        menuView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productoActualizar.isVisible()) {
                    if (productoActualizar.getParent() == null) {
                        menuView.getjDesktopPane().add(productoActualizar);
                    }
                    productoActualizar.cambiarIdiomaTexto(Main.mensajes);
                    productoActualizar.setVisible(true);
                }
            }
        });

        menuView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usuarioController.getEliminarUsuarioView().isVisible()) {
                    if (usuarioController.getEliminarUsuarioView().getParent() == null) {
                        menuView.getjDesktopPane().add(usuarioController.getEliminarUsuarioView());
                    }
                    usuarioController.getEliminarUsuarioView().cambiarIdiomaTexto(Main.mensajes);
                    usuarioController.getEliminarUsuarioView().setVisible(true);
                }
            }
        });

        menuView.getMenuItemListarUsuarios().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usuarioListarView.isVisible()) {
                    if (usuarioListarView.getParent() == null) {
                        menuView.getjDesktopPane().add(usuarioListarView);
                    }

                    usuarioController.getListarUsuariosView().cambiarIdiomaTexto(Main.mensajes);
                    usuarioListarView.cargarUsuarios(usuarioController.obtenerDatosUsuariosParaTabla());

                    usuarioListarView.setVisible(true);
                }
            }

        });


        menuView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuView.dispose();
                mostrarLogin();
            }
        });
    }
}
