package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {


                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);

                UsuarioController usuarioController = new UsuarioController(usuarioDAO, loginView);

                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e){
                        Usuario usuarioAutenticado =usuarioController.getUsuarioAutenticado();
                        if(usuarioAutenticado != null){
                            //instanciamos DAO (Singleton)
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO  carritoDAO = new CarritoDAOMemoria();

                            //instancio Vistas
                            MenuPrincipalView menuView = new MenuPrincipalView(usuarioAutenticado);

                            ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                            ProductoEliminar productoEliminar = new ProductoEliminar();
                            ProductoActualizar productoActualizar = new ProductoActualizar();
                            ProductoListaView productoListaView = new ProductoListaView();
                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                            CarritoBuscar carritoBuscar = new CarritoBuscar();
                            CarritoActualizar carritoActualizar = new CarritoActualizar();
                            CarritoListarUsuario carritoListarUsuario = new CarritoListarUsuario();
                            CarritoListarTodos carritoListarTodos = new CarritoListarTodos();



                            ProductoController productoController = new ProductoController(productoDAO,
                                    productoAnadirView, productoListaView, productoActualizar,productoEliminar,carritoAnadirView,carritoDAO);
                            // añadir producto
                            CarritoController carritoController=new CarritoController(carritoDAO,productoDAO,carritoAnadirView,usuarioAutenticado,carritoBuscar,carritoListarUsuario,carritoActualizar,carritoListarTodos);




                            menuView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoAnadirView.isVisible()){
                                        productoAnadirView.setVisible(true);
                                        menuView.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });
                            menuView.getMenuItemBuscarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoBuscar.isVisible()) {
                                        carritoBuscar.setVisible(true);
                                        menuView.getjDesktopPane().add(carritoBuscar);
                                    }
                                }
                            });
                            menuView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoEliminar.isVisible()){
                                        productoEliminar.setVisible(true);
                                        menuView.getjDesktopPane().add(productoEliminar);
                                    }
                                }
                            });
                            menuView.getMenuItemListarTodosCarritos().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoListarTodos.isVisible()) {
                                        carritoListarTodos.setVisible(true);
                                        menuView.getjDesktopPane().add(carritoListarTodos);
                                    }
                                }
                            });

                            menuView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!carritoAnadirView.isVisible()){
                                        carritoAnadirView.setVisible(true);
                                        menuView.getjDesktopPane().add(carritoAnadirView);
                                    }
                                }
                            });
                            menuView.getMenuItemActualizarCarrito().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoActualizar.isVisible()) {
                                        carritoActualizar.setVisible(true);
                                        menuView.getjDesktopPane().add(carritoActualizar);
                                    }
                                }
                            });

                            menuView.getMenuItemListarCarritosUsuario().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (!carritoListarUsuario.isVisible()) {
                                        carritoListarUsuario.setVisible(true);
                                        menuView.getjDesktopPane().add(carritoListarUsuario);
                                        carritoController.cargarMisCarritos(); // mostrar al abrir
                                    }
                                }
                            });

                            menuView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoListaView.isVisible()){
                                        productoListaView.setVisible(true);
                                        menuView.getjDesktopPane().add(productoListaView);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }

}