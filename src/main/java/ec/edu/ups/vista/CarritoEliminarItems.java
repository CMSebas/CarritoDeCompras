package ec.edu.ups.vista;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Ventana interna para eliminar productos específicos de un carrito de compras.
 * Permite buscar un carrito por su código, mostrar los productos en una tabla y
 * eliminar un producto seleccionado.
 *
 * Soporta internacionalización de la interfaz gráfica y utiliza un modelo de tabla
 * para gestionar los datos de los ítems del carrito.
 *
 * Pertenece a la capa de vista del sistema.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoEliminarItems extends JInternalFrame {
    private JPanel panelPrincipalAn;
    private JTextField txtCodigoCarrito;
    private JButton btnBuscar;
    private JButton btnEliminarProducto;
    private JTable tblProductos;
    private JLabel lblCodigo;
    private JLabel lblDatos;
    private DefaultTableModel modelo;

    /**
     * Constructor que inicializa la ventana y la tabla de productos para eliminación.
     * Configura los componentes visuales básicos.
     */
    public CarritoEliminarItems() {
        setTitle("Eliminar Producto de Carrito");
        setClosable(true);
        setIconifiable(true);
        setSize(600, 500);
        setContentPane(panelPrincipalAn);

        modelo = new DefaultTableModel(new Object[]{"CodProducto", "Nombre", "Precio", "Cantidad", "Subtotal"}, 0);
        tblProductos.setModel(modelo);
    }

    public JPanel getPanelPrincipalAn() {
        return panelPrincipalAn;
    }

    public void setPanelPrincipalAn(JPanel panelPrincipalAn) {
        this.panelPrincipalAn = panelPrincipalAn;
    }

    public JTextField getTxtCodigoCarrito() {
        return txtCodigoCarrito;
    }

    public void setTxtCodigoCarrito(JTextField txtCodigoCarrito) {
        this.txtCodigoCarrito = txtCodigoCarrito;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JButton getBtnEliminarProducto() {
        return btnEliminarProducto;
    }

    public void setBtnEliminarProducto(JButton btnEliminarProducto) {
        this.btnEliminarProducto = btnEliminarProducto;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    /**
     * Carga los productos del carrito en la tabla usando la lista de ítems.
     * Calcula automáticamente el subtotal de cada ítem.
     *
     * @param items Lista de ítems del carrito a mostrar.
     */
    public void cargarProductos(List<ItemCarrito> items) {
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad(),
                    item.getCantidad() * item.getProducto().getPrecio()
            });
        }
    }
    /**
     * Cambia todos los textos de la interfaz al idioma correspondiente.
     * También actualiza los encabezados de la tabla.
     *
     * @param mensajes Manejador de internacionalización con los textos traducidos.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carritoEliminarItems.titulo"));
        lblCodigo.setText(mensajes.get("carritoEliminarItems.lblCodigo"));
        lblDatos.setText(mensajes.get("carritoEliminarItems.lblDatos"));
        btnBuscar.setText(mensajes.get("carritoEliminarItems.btnBuscar"));
        btnEliminarProducto.setText(mensajes.get("carritoEliminarItems.btnEliminarProducto"));
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
        btnEliminarProducto.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));

        String[] columnas = {
                mensajes.get("carritoEliminarItems.col.codigo"),
                mensajes.get("carritoEliminarItems.col.nombre"),
                mensajes.get("carritoEliminarItems.col.precio"),
                mensajes.get("carritoEliminarItems.col.cantidad"),
                mensajes.get("carritoEliminarItems.col.subtotal")
        };

        for (int i = 0; i < columnas.length; i++) {
            tblProductos.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
        }

        tblProductos.getTableHeader().repaint();
    }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    /**
     * Devuelve el código del producto actualmente seleccionado en la tabla.
     *
     * @return Código del producto como entero, o -1 si no hay selección.
     */
    public int obtenerProductoSeleccionado() {
        int fila = tblProductos.getSelectedRow();
        if (fila != -1) {
            return Integer.parseInt(modelo.getValueAt(fila, 0).toString());
        }
        return -1;
    }
}
