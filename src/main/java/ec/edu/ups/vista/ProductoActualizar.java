package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
/**
 * Ventana interna (JInternalFrame) para actualizar y eliminar productos.
 *
 * Permite al usuario ingresar un código, nombre y precio de un producto
 * para actualizar sus datos o eliminar un producto por su código.
 *
 * Los textos de la interfaz pueden cambiar dinámicamente según el idioma
 * utilizando la clase {@link MensajeInternacionalizacionHandler}.
 *
 * Esta clase forma parte de la interfaz gráfica Swing.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoActualizar extends JInternalFrame {

    private JPanel panelPrincipalAct;
    private JTextField jTxfCodigo;
    private JTextField jTxfNombre;
    private JTextField jTxfPrecio;
    private JButton jButtonActualizar;
    private JTextField jTxfEliCodigo;
    private JButton jButtonEliminar;
    private JLabel lblTittle;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    /**
     * Constructor por defecto.
     * Configura el tamaño y los atributos básicos de la ventana.
     */
    public ProductoActualizar() {
        setContentPane(panelPrincipalAct);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //pack();


    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("productoActualizar.titulo"));
        lblTittle.setText(mensajes.get("productoActualizar.lblTitulo"));
        lblCodigo.setText(mensajes.get("productoActualizar.lblCodigo"));
        lblNombre.setText(mensajes.get("productoActualizar.lblNombre"));
        lblPrecio.setText(mensajes.get("productoActualizar.lblPrecio"));
        jButtonActualizar.setText(mensajes.get("productoActualizar.btnActualizar"));
        jButtonActualizar.setIcon(new ImageIcon(getClass().getResource("/icons/update.png")));

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }



    public JPanel getPanelPrincipal() {
        return panelPrincipalAct;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipalAct = panelPrincipal;
    }

    public JTextField getjTxfCodigo() {
        return jTxfCodigo;
    }

    public void setjTxfCodigo(JTextField jTxfCodigo) {
        this.jTxfCodigo = jTxfCodigo;
    }

    public JTextField getjTxfNombre() {
        return jTxfNombre;
    }

    public void setjTxfNombre(JTextField jTxfNombre) {
        this.jTxfNombre = jTxfNombre;
    }

    public JTextField getjTxfPrecio() {
        return jTxfPrecio;
    }

    public void setjTxfPrecio(JTextField jTxfPrecio) {
        this.jTxfPrecio = jTxfPrecio;
    }

    public JTextField getjTxfEliCodigo() {
        return jTxfEliCodigo;
    }

    public void setjTxfEliCodigo(JTextField jTxfEliCodigo) {
        this.jTxfEliCodigo = jTxfEliCodigo;
    }

    public JButton getjButtonActualizar() {
        return jButtonActualizar;
    }

    public void setjButtonActualizar(JButton jButtonActualizar) {
        this.jButtonActualizar = jButtonActualizar;
    }

    public JButton getjButtonEliminar() {
        return jButtonEliminar;
    }

    public void setjButtonEliminar(JButton jButtonEliminar) {
        this.jButtonEliminar = jButtonEliminar;
    }

    public JLabel getjLabel1() {
        return lblTittle;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.lblTittle = jLabel1;
    }


}
