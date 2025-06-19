package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.modelo.Carrito;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

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
        });
    }
}