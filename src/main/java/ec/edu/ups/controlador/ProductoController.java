package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.ProductoActualizarEliminar;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoListaView;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private ProductoAnadirView productoAnadirView;
    private ProductoActualizarEliminar productoActualizarEliminar;
    private ProductoListaView productoListaView;
    private CarritoAnadirView carritoAnadirView;
    private final ProductoDAO productoDAO;
    private final CarritoDAO carritoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ProductoActualizarEliminar productoActualizarEliminar,
                              CarritoAnadirView carritoAnadirView,CarritoDAO carritoDAO) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.productoActualizarEliminar = productoActualizarEliminar;
        this.carritoAnadirView = carritoAnadirView;
        this.carritoDAO = carritoDAO;
        this.configurarEventosEnVistas();
    }



    private void configurarEventosEnVistas() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });


        productoActualizarEliminar.getjButtonActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
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
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }


    private void actualizarProducto() {
        int codigo = Integer.parseInt(productoActualizarEliminar.getjTxfCodigo().getText());
        String nombre = productoActualizarEliminar.getjTxfNombre().getText();
        double precio = Double.parseDouble(productoActualizarEliminar.getjTxfPrecio().getText());

        Producto productoExistente = productoDAO.buscarPorCodigo(codigo);
        if (productoExistente != null) {
            Producto nuevoProducto = new Producto(codigo, nombre, precio);
            productoDAO.actualizar(nuevoProducto);
            productoActualizarEliminar.mostrarMensaje("Producto actualizado correctamente");
        } else {
            productoActualizarEliminar.mostrarMensaje("Producto no encontrado");
        }
    }

    private void eliminarProducto() {
        int codigo = Integer.parseInt(productoActualizarEliminar.getjTxfEliCodigo().getText());

        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto != null) {
            productoDAO.eliminar(codigo);
            productoActualizarEliminar.mostrarMensaje("Prodcuto eliminado correctamente");
        } else {
            productoActualizarEliminar.mostrarMensaje("Producto no encontrado");
        }
    }

    public void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }








}