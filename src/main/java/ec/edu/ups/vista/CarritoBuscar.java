package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoBuscar extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarritos;
    private JLabel lblUsuario;
    private JButton btnListar;
    private DefaultTableModel modelo;

    public CarritoBuscar() {
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

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código", "Fecha", "Subtotal", "IVA", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
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

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(List<Carrito> listaCarritos, Locale locale) {
        modelo.setRowCount(0);
        for (Carrito carrito : listaCarritos) {
            Object[] fila = {
                    carrito.getCodigo(),
                    FormateadorUtils.formatearFecha(carrito.getFechaCreacion().getTime(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularIVA(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale)
            };
            modelo.addRow(fila);
        }
    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        btnBuscar.setText(mensajes.get("carritoBuscar.btnBuscar"));
        lblUsuario.setText(mensajes.get("carritoBuscar.lblUsuario"));
        setTitle(mensajes.get("carritoBuscar.titulo"));
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));


        modelo.setColumnIdentifiers(new Object[]{
                mensajes.get("carritoBuscar.col.codigo"),
                mensajes.get("carritoBuscar.col.fecha"),
                mensajes.get("carritoBuscar.col.subtotal"),
                mensajes.get("carritoBuscar.col.iva"),
                mensajes.get("carritoBuscar.col.total")
        });
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
