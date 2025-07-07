package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoActualizar extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarritos;
    private JButton btnActualizar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;

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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }


}
