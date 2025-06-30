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
import ec.edu.ups.modelo.Carrito;
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
                        Usuario usuarioAuntenticado =usuarioController.getUsuarioAutenticado();
                        if(usuarioAuntenticado != null){
                            //instanciamos DAO (Singleton)
                            ProductoDAO productoDAO = new ProductoDAOMemoria();
                            CarritoDAO  carritoDAO = new CarritoDAOMemoria();

                            //instancio Vistas
                            MenuPrincipalView menuView = new MenuPrincipalView();
                            ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                            ProductoActualizarEliminar productoActualizarEliminar = new ProductoActualizarEliminar();
                            ProductoListaView productoListaView = new ProductoListaView();
                            CarritoAnadirView carritoAnadirView = new CarritoAnadirView();


                            ProductoController productoController = new ProductoController(productoDAO,
                                    productoAnadirView, productoListaView, productoActualizarEliminar,carritoAnadirView,carritoDAO);
                            // añadir producto
                            CarritoController carritoController=new CarritoController(carritoDAO,productoDAO,carritoAnadirView);




                            menuView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoAnadirView.isVisible()){
                                        productoAnadirView.setVisible(true);
                                        menuView.getjDesktopPane().add(productoAnadirView);
                                    }
                                }
                            });

                            menuView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(!productoActualizarEliminar.isVisible()){
                                        productoActualizarEliminar.setVisible(true);
                                        menuView.getjDesktopPane().add(productoActualizarEliminar);
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