package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
/**
 * Clase que representa la vista de eliminación de usuarios del sistema.
 *
 * Esta clase extiende {@link JInternalFrame} y proporciona una interfaz gráfica
 * para que el administrador pueda eliminar usuarios por su nombre de usuario.
 *
 * Es compatible con la internacionalización y usa íconos representativos.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class EliminarUsuario extends JInternalFrame {
    private JLabel lblTittle;
    private JLabel jblUsuario;
    private JTextField jTxfEliNombre;
    private JButton jButtonEliminar;
    private JPanel panelPrincipal;


    public EliminarUsuario() {
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
        setTitle(mensajes.get("eliminarUsuario.titulo"));
        lblTittle.setText(mensajes.get("eliminarUsuario.titulo"));
        jblUsuario.setText(mensajes.get("eliminarUsuario.lblUsuario"));
        jButtonEliminar.setText(mensajes.get("eliminarUsuario.btnEliminar"));
        jButtonEliminar.setIcon(new ImageIcon(getClass().getResource("/icons/delete.png")));

    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JLabel getLblTittle() {
        return lblTittle;
    }

    public JTextField getjTxfEliNombre() {
        return jTxfEliNombre;
    }

    public void setjTxfEliNombre(JTextField jTxfEliNombre) {
        this.jTxfEliNombre = jTxfEliNombre;
    }

    public void setLblTittle(JLabel lblTittle) {
        this.lblTittle = lblTittle;
    }

    public JLabel getJblCodigo() {
        return jblUsuario;
    }

    public void setJblCodigo(JLabel jblCodigo) {
        this.jblUsuario = jblCodigo;
    }

    public JTextField getjTxfEliCodigo() {
        return jTxfEliNombre;
    }

    public void setjTxfEliCodigo(JTextField jTxfEliCodigo) {
        this.jTxfEliNombre = jTxfEliCodigo;
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

    public JLabel getJblUsuario() {
        return jblUsuario;
    }

    public void setJblUsuario(JLabel jblUsuario) {
        this.jblUsuario = jblUsuario;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
}
