package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarritoEliminar extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTextField txtCodigo;
    private JButton btnEliminar;
    private JTable tblCarritos;
    private JButton btnActualizar;
    private JLabel lblCodigo;
    private DefaultTableModel modelo;
    private List<Carrito> carritosActuales = new ArrayList<>();

    public CarritoEliminar() {
        setTitle("Eliminar Carrito");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);

        panelPrincipal2 = new JPanel();
        panelPrincipal2.setLayout(new BoxLayout(panelPrincipal2, BoxLayout.Y_AXIS));

        JPanel panelSuperior = new JPanel();
        lblCodigo = new JLabel("Código:");
        txtCodigo = new JTextField(10);
        btnEliminar = new JButton("Eliminar");

        panelSuperior.add(lblCodigo);
        panelSuperior.add(txtCodigo);
        panelSuperior.add(btnEliminar);

        panelPrincipal2.add(panelSuperior);

        modelo = new DefaultTableModel(new Object[]{"Código", "Usuario", "Fecha", "Subtotal", "IVA", "Total"}, 0);
        tblCarritos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tblCarritos);

        panelPrincipal2.add(scrollPane);

        setContentPane(panelPrincipal2);
    }

    public void cargarTabla(List<Carrito> lista, Locale locale) {
        this.carritosActuales = lista;
        modelo.setRowCount(0);

        for (Carrito carrito : lista) {
            modelo.addRow(new Object[]{
                    carrito.getCodigo(),
                    FormateadorUtils.formatearFecha(carrito.getFechaCreacion().getTime(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularSubtotal(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularIVA(), locale),
                    FormateadorUtils.formatearMoneda(carrito.calcularTotal(), locale)
            });
        }
    }
    public void actualizarTextosYMoneda(ResourceBundle mensajes, Locale locale) {
        btnEliminar.setText(mensajes.getString("btnEliminar"));
        cargarTabla(carritosActuales, locale);
    }



    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carritoEliminar.titulo"));
        btnEliminar.setText(mensajes.get("carritoEliminar.btnEliminar"));
        lblCodigo.setText(mensajes.get("carritoEliminar.lblCodigo"));
        btnEliminar.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));


        String[] columnas = {
                mensajes.get("carritoEliminar.col.codigo"),
                mensajes.get("carritoEliminar.col.usuario"),
                mensajes.get("carritoEliminar.col.fecha"),
                mensajes.get("carritoEliminar.col.subtotal"),
                mensajes.get("carritoEliminar.col.iva"),
                mensajes.get("carritoEliminar.col.total")
        };

        for (int i = 0; i < columnas.length; i++) {
            tblCarritos.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
        }

        tblCarritos.getTableHeader().repaint();
    }

    public JPanel getPanelPrincipal2() {
        return panelPrincipal2;
    }

    public void setPanelPrincipal2(JPanel panelPrincipal2) {
        this.panelPrincipal2 = panelPrincipal2;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

}
