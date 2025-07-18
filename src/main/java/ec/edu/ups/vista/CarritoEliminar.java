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
/**
 * Clase que representa una ventana interna (JInternalFrame) utilizada para eliminar carritos de compras.
 * Permite al usuario ingresar un código de carrito, visualizar una tabla con los carritos existentes y
 * eliminarlos de la lista. También soporta internacionalización para cambiar los textos visibles.
 *
 * Forma parte de la capa de presentación del sistema de gestión de compras.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoEliminar extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTextField txtCodigo;
    private JButton btnEliminar;
    private JTable tblCarritos;
    private JButton btnActualizar;
    private JLabel lblCodigo;
    private DefaultTableModel modelo;
    private List<Carrito> carritosActuales = new ArrayList<>();
    /**
     * Constructor que configura y construye la interfaz de la ventana para eliminar carritos.
     * Inicializa el diseño y los componentes como botones, tabla y campos de entrada.
     */
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
    /**
     * Carga la tabla de carritos con la lista proporcionada, usando formato local para fechas y moneda.
     *
     * @param lista Lista de carritos a mostrar en la tabla.
     * @param locale Configuración regional para formatear los datos (moneda y fecha).
     */
    public void cargarTabla(List<Carrito> lista, Locale locale) {
        this.carritosActuales = lista;
        modelo.setRowCount(0);

        for (Carrito carrito : lista) {
            modelo.addRow(new Object[]{
                    carrito.getCodigo(),
                    carrito.getUsuario().getUsername(),
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


    /**
     * Cambia todos los textos visibles de la interfaz según el idioma seleccionado.
     * También actualiza los encabezados de columna en la tabla.
     *
     * @param mensajes Manejador de internacionalización que contiene los textos en el idioma actual.
     */
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
