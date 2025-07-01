package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoBuscar carritoBuscar;
    private final CarritoListarUsuario carritoListarUsuario;
    private final CarritoActualizar carritoActualizar;
    private final CarritoListarTodos carritoListarTodos;
    private Carrito carritoSeleccionado;
    private Usuario usuario;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             Usuario usuario,
                             CarritoBuscar carritoBuscar,
                             CarritoListarUsuario carritoListarUsuario,
                             CarritoActualizar carritoActualizar,
                             CarritoListarTodos carritoListarTodos) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.usuario = usuario;
        this.carrito = new Carrito(usuario);
        this.carritoBuscar = carritoBuscar;
        this.carritoActualizar = carritoActualizar;
        this.carritoListarUsuario = carritoListarUsuario;
        this.carritoListarTodos = carritoListarTodos;
        configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProducto();
            }
        });

        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCarrito();
            }
        });
        carritoListarTodos.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carrito> todos = carritoDAO.listarTodos();
                carritoListarTodos.cargarCarritos(todos);
            }
        });
        carritoBuscar.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarritosPorUsuario();
            }
        });

        carritoActualizar.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCarritoDesdeTabla();
            }
        });

        carritoActualizar.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarritosPorUsuarioParaActualizar(); // ✅
            }
        });
        carritoListarUsuario.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMisCarritos();
            }
        });
    }

    private void guardarCarrito() {
        carritoDAO.crear(carrito); // Guarda el actual
        carritoAnadirView.mostrarMensaje("✅ Carrito guardado");

        // Crea un nuevo carrito vacío para seguir trabajando
        carrito = new Carrito(usuario);

        // Limpia tabla y totales visuales
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setRowCount(0);
        carritoAnadirView.getTxtSubtotal().setText("");
        carritoAnadirView.getTxtIva().setText("");
        carritoAnadirView.getTxtTotal().setText("");
    }

    private void anadirProducto() {

        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        int cantidad =  Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
        carrito.agregarProducto(producto, cantidad);

        cargarProductos();
        mostrarTotales();

    }

    private void cargarProductos(){

        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{ item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getProducto().getPrecio() * item.getCantidad() });
        }
    }

    private void mostrarTotales(){
        String subtotal = String.valueOf(carrito.calcularSubtotal());
        String iva = String.valueOf(carrito.calcularIVA());
        String total = String.valueOf(carrito.calcularTotal());

        carritoAnadirView.getTxtSubtotal().setText(subtotal);
        carritoAnadirView.getTxtIva().setText(iva);
        carritoAnadirView.getTxtTotal().setText(total);
    }

    private void actualizarCarritoDesdeTabla() {
        DefaultTableModel modelo = carritoActualizar.getModelo();

        // Paso 1: recorremos todos los carritos del usuario
        String username = carritoActualizar.getTxtBuscar().getText();
        List<Carrito> carritos = carritoDAO.buscarPorUsuario(username);

        // Paso 2: por cada carrito, limpiamos sus ítems
        for (Carrito c : carritos) {
            c.obtenerItems().clear();
        }

        // Paso 3: recorremos la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            int codCarrito = Integer.parseInt(modelo.getValueAt(i, 0).toString());
            int codProducto = Integer.parseInt(modelo.getValueAt(i, 1).toString());
            String nombre = modelo.getValueAt(i, 2).toString();
            double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 4).toString());

            // Buscamos el carrito correspondiente
            for (Carrito c : carritos) {
                if (c.getCodigo() == codCarrito) {
                    Producto producto = new Producto(codProducto, nombre, precio);
                    c.agregarProducto(producto, cantidad);
                    break;
                }
            }
        }

        // Paso 4: actualizamos cada carrito individualmente
        for (Carrito c : carritos) {
            carritoDAO.actualizar(c);
        }

        carritoActualizar.mostrarMensaje("✅ Carritos actualizados correctamente");
    }

    private void buscarCarritosPorUsuario() {
        String nombre = carritoBuscar.getTxtBuscar().getText();

        List<Carrito> carritosEncontrados = carritoDAO.buscarPorUsuario(nombre);

        if (carritosEncontrados.isEmpty()) {
            carritoBuscar.mostrarMensaje("❌ No se encontraron carritos para ese usuario.");
        } else {
            carritoBuscar.cargarDatos(carritosEncontrados);
        }
    }

    private void buscarCarritosPorUsuarioParaActualizar() {
        String username = carritoActualizar.getTxtBuscar().getText();
        List<Carrito> carritos = carritoDAO.buscarPorUsuario(username);

        if (carritos.isEmpty()) {
            carritoActualizar.mostrarMensaje("❌ No se encontraron carritos.");
        } else {
            carritoActualizar.cargarCarritos(carritos); // Mostrar todos los carritos del usuario
        }
    }




    public void cargarMisCarritos() {
        List<Carrito> misCarritos = carritoDAO.buscarPorUsuario(usuario.getUsername());
        carritoListarUsuario.cargarCarritos(misCarritos);
    }


}
