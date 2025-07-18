package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
/**
 * Ventana interna (JInternalFrame) para eliminar productos del sistema.
 *
 * Esta clase permite al usuario ingresar un código de producto y eliminarlo del sistema.
 * Se integra con el sistema de internacionalización a través del objeto {@link MensajeInternacionalizacionHandler}.
 * También permite mostrar mensajes al usuario mediante cuadros de diálogo.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoEliminar  extends JInternalFrame{
    private JTextField jTxfEliCodigo;
    private JButton jButtonEliminar;
    private JPanel panelPrincipal;
    private JLabel lblTittle;
    private JLabel jblCodigo;
    /**
     * Constructor que inicializa la ventana para eliminar un producto.
     * Configura el panel principal, tamaño, y comportamiento de cierre.
     */
    public ProductoEliminar() {
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //pack();
    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("productoEliminar.titulo"));
        lblTittle.setText(mensajes.get("productoEliminar.lblTitulo"));
        jblCodigo.setText(mensajes.get("productoEliminar.lblCodigo"));
        jButtonEliminar.setText(mensajes.get("productoEliminar.btnEliminar"));
        jButtonEliminar.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));
    }


    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getjTxfEliCodigo() {
        return jTxfEliCodigo;
    }

    public void setjTxfEliCodigo(JTextField jTxfEliCodigo) {
        this.jTxfEliCodigo = jTxfEliCodigo;
    }

    public JButton getjButtonEliminar() {
        return jButtonEliminar;
    }

    public void setjButtonEliminar(JButton jButtonEliminar) {
        this.jButtonEliminar = jButtonEliminar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
}
