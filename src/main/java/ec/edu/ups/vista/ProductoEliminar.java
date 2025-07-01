package ec.edu.ups.vista;

import javax.swing.*;

public class ProductoEliminar  extends JInternalFrame{
    private JTextField jTxfEliCodigo;
    private JButton jButtonEliminar;
    private JPanel panelPrincipal;

    public ProductoEliminar() {
        setContentPane(panelPrincipal);
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
