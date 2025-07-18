package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final CarritoBuscar carritoBuscar;
    private final CarritoListarUsuario carritoListarUsuario;
    private final CarritoActualizar carritoActualizar;
    private final CarritoListarTodos carritoListarTodos;
    private final CarritoEliminar carritoEliminar;
    private final CarritoEliminarItems carritoEliminarItems;
    private String monedaActual = "USD";
    private Carrito carritoSeleccionado;
    private Locale localeActual = new Locale("es", "EC");
    private final MensajeInternacionalizacionHandler mensajes;
    private Usuario usuario;
    private Carrito carrito;

    public CarritoController(CarritoDAO carritoDAO,
                             ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             Usuario usuario,
                             CarritoBuscar carritoBuscar,
                             CarritoListarUsuario carritoListarUsuario,
                             CarritoActualizar carritoActualizar,
                             CarritoListarTodos carritoListarTodos,
                             CarritoEliminar carritoEliminar,
                             CarritoEliminarItems carritoEliminarItems,
                             MensajeInternacionalizacionHandler mensajes) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.usuario = usuario;
        this.carrito = new Carrito(usuario);
        this.carritoBuscar = carritoBuscar;
        this.carritoActualizar = carritoActualizar;
        this.carritoListarUsuario = carritoListarUsuario;
        this.carritoListarTodos = carritoListarTodos;
        this.carritoEliminar = carritoEliminar;
        this.carritoEliminarItems = carritoEliminarItems;
        this.mensajes = mensajes;
        this.carritoAnadirView.cambiarIdiomaTexto(mensajes);

        configurarEventosEnVistas();
    }
    public void cambiarMoneda(Locale nuevaLocale) {
        this.localeActual = nuevaLocale;
        mostrarTotales();

        List<Carrito> todos = carritoDAO.listarTodos();
        carritoListarTodos.cargarCarritos(todos, localeActual);


    }



    public CarritoAnadirView getCarritoAnadirView() {
        return carritoAnadirView;
    }

    public String getMonedaActual() {
        return monedaActual;
    }

    public void setMonedaActual(String monedaActual) {
        this.monedaActual = monedaActual;
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
                carritoListarTodos.cargarCarritos(todos,localeActual);
            }
        });
        carritoEliminarItems.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoCodigo = carritoEliminarItems.getTxtCodigoCarrito().getText();
                int codigo;

                try {
                    codigo = Integer.parseInt(textoCodigo);
                } catch (NumberFormatException ex) {
                    carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.codigoInvalido"));
                    return;
                }

                Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
                if (carrito != null) {
                    carritoEliminarItems.cargarProductos(carrito.obtenerItems());
                } else {
                    carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.noExiste"));
                }
            }
        });

        carritoEliminarItems.getBtnEliminarProducto().addActionListener(new ActionListener() {
            @Override
                    public void actionPerformed(ActionEvent e) {
                String codCarritoStr = carritoEliminarItems.getTxtCodigoCarrito().getText();
                int codCarrito ;

                try {
                    codCarrito = Integer.parseInt(codCarritoStr);
                } catch (NumberFormatException ex) {
                    carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.noValid"));
                    return;
                }
                Carrito carrito = carritoDAO.buscarPorCodigo(codCarrito);

                if (carrito == null) {
                    carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.noEncontrado"));
                    return;
                }

                int codProducto = carritoEliminarItems.obtenerProductoSeleccionado();
                if (codProducto == -1) {
                    carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.seleccionaProducto"));
                    return;
                }

                carrito.eliminarProducto(codProducto);
                carritoDAO.actualizar(carrito);


                carritoEliminarItems.cargarProductos(carrito.obtenerItems());

                carritoEliminarItems.mostrarMensaje(mensajes.get("carrito.productoEliminado"));
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
        carritoEliminar.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(carritoEliminar.getTxtCodigo().getText());
                    Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
                    if (carrito != null) {
                        carritoDAO.eliminar(codigo);
                        carritoEliminar.mostrarMensaje(mensajes.get("carrito.eliminado"));
                        cargarTodosCarritosEnEliminar(); // recargar tabla
                    } else {
                        carritoEliminar.mostrarMensaje(mensajes.get("carrito.noExiste"));
                    }
                } catch (NumberFormatException ex) {
                    carritoEliminar.mostrarMensaje(mensajes.get("carrito.codigoInvalido"));
                }
            }
        });



        carritoActualizar.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarritoPorCodigoParaActualizar();
            }
        });
        carritoListarUsuario.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarMisCarritos();
            }
        });
    }

    public void cargarTodosCarritosEnEliminar() {
        carritoEliminar.cargarTabla(carritoDAO.listarTodos(),localeActual);
    }

    private void mostrarTotales() {
        carritoAnadirView.getTxtSubtotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), localeActual));
        carritoAnadirView.getTxtIva().setText(FormateadorUtils.formatearMoneda(carrito.calcularIVA(), localeActual));
        carritoAnadirView.getTxtTotal().setText(FormateadorUtils.formatearMoneda(carrito.calcularTotal(), localeActual));
    }

    private void guardarCarrito() {
        carritoDAO.crear(carrito);
        carritoAnadirView.mostrarMensaje(mensajes.get("carrito.guardado"));


        carrito = new Carrito(usuario);


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

    private void cargarProductos() {
        List<ItemCarrito> items = carrito.obtenerItems();
        DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
        modelo.setNumRows(0);

        for (ItemCarrito item : items) {
            Producto producto = item.getProducto();

            if (producto != null) {
                modelo.addRow(new Object[]{
                        producto.getCodigo(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        item.getCantidad(),
                        producto.getPrecio() * item.getCantidad()
                });
            } else {
                System.out.println("⚠ Advertencia: ItemCarrito con producto nulo (carrito código " + carrito.getCodigo() + ")");
            }
        }
    }

    private void buscarCarritoPorCodigoParaActualizar() {
        try {
            int codigo = Integer.parseInt(carritoActualizar.getTxtBuscar().getText());
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
            if (carrito == null) {
                carritoActualizar.mostrarMensaje(mensajes.get("carrito.noEncontrado"));
            } else {
                carritoActualizar.cargarCarritos(List.of(carrito)); // Mostrar solo ese carrito
            }
        } catch (NumberFormatException e) {
            carritoActualizar.mostrarMensaje(mensajes.get("carrito.codigoInvalido"));
        }
    }



    private void actualizarCarritoDesdeTabla() {
        DefaultTableModel modelo = carritoActualizar.getModelo();
        int codCarrito = Integer.parseInt(carritoActualizar.getTxtBuscar().getText());



        Carrito carrito = carritoDAO.buscarPorCodigo(codCarrito);
        if (carrito == null) {
            carritoActualizar.mostrarMensaje(mensajes.get("carrito.noEncontrado"));
            return;
        }

        carrito.obtenerItems().clear();


        for (int i = 0; i < modelo.getRowCount(); i++) {
            int codProducto = Integer.parseInt(modelo.getValueAt(i, 1).toString());
            String nombre = modelo.getValueAt(i, 2).toString();
            double precio = Double.parseDouble(modelo.getValueAt(i, 3).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 4).toString());

            carrito.agregarProducto(new Producto(codProducto, nombre, precio), cantidad);
        }

        carritoDAO.actualizar(carrito);
        carritoActualizar.mostrarMensaje(mensajes.get("carrito.actualizado"));
    }

    private void buscarCarritosPorUsuario() {
        String nombre = carritoBuscar.getTxtBuscar().getText();

        List<Carrito> carritosEncontrados = carritoDAO.buscarPorUsuario(nombre);

        if (carritosEncontrados.isEmpty()) {
            carritoBuscar.mostrarMensaje(mensajes.get("carrito.noCarritos"));
        } else {
            carritoBuscar.cargarDatos(carritosEncontrados,localeActual);
        }
    }

    private void buscarCarritosPorUsuarioParaActualizar() {
        String username = carritoActualizar.getTxtBuscar().getText();
        List<Carrito> carritos = carritoDAO.buscarPorUsuario(username);

        if (carritos.isEmpty()) {
            carritoActualizar.mostrarMensaje(mensajes.get("carrito.noEncontrado"));
        } else {
            carritoActualizar.cargarCarritos(carritos); // Mostrar todos los carritos del usuario
        }
    }




    public void cargarMisCarritos() {
        List<Carrito> misCarritos = carritoDAO.buscarPorUsuario(usuario.getUsername());
        carritoListarUsuario.cargarCarritos(misCarritos,localeActual);
    }


}
