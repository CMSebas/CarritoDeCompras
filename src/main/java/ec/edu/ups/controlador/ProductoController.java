package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    public void setMonedaActual(String monedaActual) {
        this.monedaActual = monedaActual;
    }
    public void actualizarVistaLista() {
        listarProductos(); // sigue siendo privado
    }

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








    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje(mensajes.get("producto.guardadoCorrectamente"));
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados,monedaActual);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos,monedaActual);
    }


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