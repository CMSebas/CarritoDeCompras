package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Controlador que gestiona la lógica relacionada con los productos.
 * Coordina entre las vistas y el DAO para realizar operaciones como crear,
 * actualizar, eliminar, buscar y listar productos.
 *
 * También se integra con la vista de carrito para la selección de productos.
 * Este controlador forma parte del patrón MVC.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoActualizar productoActualizar;
    private ProductoListaView productoListaView;
    private CarritoAnadirView carritoAnadirView;
    private ProductoEliminar productoEliminar;
    private final ProductoDAO productoDAO;
    private final CarritoDAO carritoDAO;
    private String monedaActual = "USD";
    private final MensajeInternacionalizacionHandler mensajes;
    /**
     * Constructor que inicializa el controlador con las vistas, DAOs e internacionalización.
     * También configura los eventos de las vistas.
     *
     * @param productoDAO DAO para acceder a datos de productos
     * @param productoAnadirView Vista para agregar productos
     * @param productoListaView Vista para listar y buscar productos
     * @param productoActualizar Vista para actualizar productos
     * @param productoEliminar Vista para eliminar productos
     * @param carritoAnadirView Vista para añadir productos al carrito
     * @param carritoDAO DAO para acceder a datos del carrito
     * @param mensajes Manejador de internacionalización de mensajes
     */


    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoActualizar productoActualizar,
                              ProductoEliminar productoEliminar,
                              CarritoAnadirView carritoAnadirView,CarritoDAO carritoDAO,MensajeInternacionalizacionHandler mensajes) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoActualizar= productoActualizar;
        this.carritoAnadirView = carritoAnadirView;
        this.productoEliminar=productoEliminar;
        this.carritoDAO = carritoDAO;
        this.mensajes = mensajes;
        this.carritoAnadirView.cambiarIdiomaTexto(mensajes);
        this.configurarEventosEnVistas();
    }
    /**
     * Establece la moneda actual utilizada para mostrar precios.
     *
     * @param monedaActual Código de la moneda (ej. "USD", "EUR")
     */
    public void setMonedaActual(String monedaActual) {
        this.monedaActual = monedaActual;
    }


    /**
     * Actualiza la vista de lista de productos con todos los productos disponibles.
     */
    public void actualizarVistaLista() {
        listarProductos();
    }

    /**
     * Configura todos los eventos (listeners) en las vistas conectadas a este controlador.
     */
    private void configurarEventosEnVistas() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });


        productoActualizar.getjButtonActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        productoEliminar.getjButtonEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {eliminarProducto();}
        });

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
    }







    /**
     * Guarda un nuevo producto a partir de los datos ingresados en la vista.
     * Valida el formato del precio y muestra mensajes de confirmación o error.
     */
    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio;
        try {
            precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productoAnadirView, "Por favor, ingrese un valor numérico válido para el precio.");
            return;
        }

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje(mensajes.get("producto.guardadoCorrectamente"));
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }
    /**
     * Busca productos por nombre desde la vista de lista.
     * Muestra los resultados en la tabla usando la moneda actual.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados,monedaActual);
    }
    /**
     * Lista todos los productos y los muestra en la vista de listado.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos,monedaActual);
    }

    /**
     * Actualiza un producto existente con los nuevos datos ingresados en la vista.
     * Muestra mensajes de éxito o error si el producto no existe.
     */
    private void actualizarProducto() {
        int codigo = Integer.parseInt(productoActualizar.getjTxfCodigo().getText());
        String nombre = productoActualizar.getjTxfNombre().getText();
        double precio = Double.parseDouble(productoActualizar.getjTxfPrecio().getText());

        Producto productoExistente = productoDAO.buscarPorCodigo(codigo);
        if (productoExistente != null) {
            Producto nuevoProducto = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(nuevoProducto);
            productoActualizar.mostrarMensaje(mensajes.get("producto.actualizadoCorrectamente"));
        } else {
            productoActualizar.mostrarMensaje(mensajes.get("producto.noEncontrado"));
        }
    }

    /**
     * Elimina un producto cuyo código es ingresado en la vista correspondiente.
     * Muestra mensaje de confirmación o de error si no se encuentra el producto.
     */
    private void eliminarProducto() {
        int codigo = Integer.parseInt(productoEliminar.getjTxfEliCodigo().getText());

        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoDAO.eliminar(codigo);
            productoEliminar.mostrarMensaje(mensajes.get("producto.eliminadoCorrectamente"));
        } else {
            productoEliminar.mostrarMensaje("Producto no encontrado");
        }
    }

    /**
     * Busca un producto por su código desde la vista de carrito y muestra sus datos
     * si existe, o muestra un mensaje de error si no se encuentra.
     */
    public void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje(mensajes.get("producto.noExisteBuscar"));
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }








}