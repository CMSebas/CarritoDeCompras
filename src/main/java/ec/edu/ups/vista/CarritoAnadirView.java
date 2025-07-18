package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * Clase que representa una ventana interna (JInternalFrame) para añadir productos a un carrito de compras.
 * Permite buscar productos, establecer cantidades, añadirlos a una tabla con subtotales,
 * y calcular valores como subtotal, IVA y total.
 *
 * Incluye soporte para internacionalización mediante el uso del handler de mensajes.
 *
 * Esta clase forma parte de la capa de presentación del sistema.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoAnadirView extends JInternalFrame {
    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipalAn;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblSub;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JLabel lblTitulo2;
    /**
     * Constructor que configura la ventana de añadir productos al carrito,
     * inicializa el modelo de la tabla y carga los valores para el combo de cantidad.
     */
    public CarritoAnadirView(){

        super("Carrito de Compras", true, true, false, true);
        setContentPane(panelPrincipalAn);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio", "Cantidad", "Subtotal"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();

    }
    /**
     * Cambia todos los textos visibles de la ventana de acuerdo al idioma seleccionado
     * usando el manejador de internacionalización.
     *
     * @param mensajes Manejador de recursos internacionalizados.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carrito.titulo"));

        btnBuscar.setText(mensajes.get("carrito.btnBuscar"));
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
        btnGuardar.setIcon(new ImageIcon(getClass().getResource("/icons/save.png")));
        btnAnadir.setIcon(new   ImageIcon(getClass().getResource("/icons/anadir.png")));
        btnAnadir.setText(mensajes.get("carrito.btnAnadir"));
        btnGuardar.setText(mensajes.get("carrito.btnGuardar"));
        btnLimpiar.setText(mensajes.get("carrito.btnLimpiar"));
        lblCodigo.setText(mensajes.get("carrito.lblCodigo"));
        lblNombre.setText(mensajes.get("carrito.lblNombre"));
        lblPrecio.setText(mensajes.get("carrito.lblPrecio"));
        lblCantidad.setText(mensajes.get("carrito.lblCantidad"));
        lblSub.setText(mensajes.get("carrito.lblSub"));
        lblIva.setText(mensajes.get("carrito.lblIva"));
        lblTotal.setText(mensajes.get("carrito.lblTotal"));
        lblTitulo2.setText(mensajes.get("carrito.lblTitulo2"));


        String[] columnas = {
                mensajes.get("carrito.tbl.codigo"),
                mensajes.get("carrito.tbl.nombre"),
                mensajes.get("carrito.tbl.precio"),
                mensajes.get("carrito.tbl.cantidad"),
                mensajes.get("carrito.tbl.subtotal")
        };

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setColumnIdentifiers(columnas);


    }


    /**
     * Carga valores del 1 al 20 en el combo de selección de cantidad.
     */
    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public void setBtnAnadir(JButton btnAnadir) {
        this.btnAnadir = btnAnadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public void setTxtSubtotal(JTextField txtSubtotal) {
        this.txtSubtotal = txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public void setTxtIva(JTextField txtIva) {
        this.txtIva = txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public void setTxtTotal(JTextField txtTotal) {
        this.txtTotal = txtTotal;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public void setCbxCantidad(JComboBox cbxCantidad) {
        this.cbxCantidad = cbxCantidad;
    }

    public JPanel getPanelPrincipalAn() {
        return panelPrincipalAn;
    }

    public void setPanelPrincipalAn(JPanel panelPrincipalAn) {
        this.panelPrincipalAn = panelPrincipalAn;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
