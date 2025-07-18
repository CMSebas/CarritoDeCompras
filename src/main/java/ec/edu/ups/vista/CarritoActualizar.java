package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * Clase que representa una ventana interna para actualizar la cantidad de productos en los carritos.
 * Permite buscar un carrito por su código, visualizar los productos contenidos, modificar la cantidad,
 * y mostrar los totales del carrito actualizado.
 *
 * Además, ofrece soporte para internacionalización a través del uso de la clase MensajeInternacionalizacionHandler.
 *
 * Esta clase forma parte del módulo de interfaz gráfica del sistema.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoActualizar extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarritos;
    private JButton btnActualizar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    /**
     * Constructor que inicializa la ventana con su layout, título,
     * configuración de botones y modelo de tabla con edición habilitada
     * solo en la columna de cantidad.
     */
    public CarritoActualizar() {
        setContentPane(panelPrincipal2);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //pack();



        modelo = new DefaultTableModel(new Object[]{"CodCarrito", "CodProducto", "Nombre", "Precio", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna cantidad
            }
        };
        tblCarritos.setModel(modelo);



    }
    /**
     * Cambia los textos visibles de los componentes de la ventana
     * según el idioma proporcionado por el handler de internacionalización.
     *
     * @param mensajes Manejador de internacionalización que contiene los textos localizados.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.actualizar.tabTitulo"));
        btnBuscar.setText(mensajes.get("carrito.actualizar.btnBuscar"));
        btnActualizar.setText(mensajes.get("carrito.actualizar.btnActualizar"));
        lblNombre.setText(mensajes.get("carrito.actualizar.lblNombre"));
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
        btnActualizar.setIcon(new ImageIcon(getClass().getResource("/icons/update.png")));

        modelo.setColumnIdentifiers(new Object[]{
                mensajes.get("carrito.actualizar.tbl.codcarrito"),
                mensajes.get("carrito.actualizar.tbl.codigo"),
                mensajes.get("carrito.actualizar.tbl.nombre"),
                mensajes.get("carrito.actualizar.tbl.precio"),
                mensajes.get("carrito.actualizar.tbl.cantidad")
        });
    }
    /**
     * Carga en la tabla todos los ítems de los carritos proporcionados.
     *
     * @param carritos Lista de carritos cuyos productos se van a mostrar en la tabla.
     */

    public void cargarCarritos(List<Carrito> carritos) {
        modelo.setRowCount(0);
        for (Carrito carrito : carritos) {
            for (ItemCarrito item : carrito.obtenerItems()) {
                modelo.addRow(new Object[]{
                        carrito.getCodigo(),
                        item.getProducto().getCodigo(),
                        item.getProducto().getNombre(),
                        item.getProducto().getPrecio(),
                        item.getCantidad()
                });
            }
        }
    }

    public void mostrarTotales(double subtotal, double iva, double total) {
        JOptionPane.showMessageDialog(this,
                "Subtotal: $" + subtotal +
                        "\nIVA: $" + iva +
                        "\nTotal: $" + total,
                "Totales del carrito actualizado", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getPanelPrincipal2() {
        return panelPrincipal2;
    }

    public void setPanelPrincipal2(JPanel panelPrincipal2) {
        this.panelPrincipal2 = panelPrincipal2;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public void setBtnActualizar(JButton btnActualizar) {
        this.btnActualizar = btnActualizar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    /**
     * Carga en la tabla los ítems de un carrito específico (sin mostrar el código del carrito).
     *
     * @param items Lista de ítems del carrito.
     */
    public void cargarDatos(List<ItemCarrito> items) {
        modelo.setRowCount(0);
        for (ItemCarrito item : items) {
            modelo.addRow(new Object[]{
                    item.getProducto().getCodigo(),
                    item.getProducto().getNombre(),
                    item.getProducto().getPrecio(),
                    item.getCantidad()
            });
        }
    }
    /**
     * Muestra un mensaje emergente al usuario.
     *
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


}
