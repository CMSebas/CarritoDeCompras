package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;

import javax.swing.*;

public class ProductoActualizarEliminar extends JFrame {

    private JPanel panelPrincipal;
    private JTextField jTxfCodigo;
    private JTextField jTxfNombre;
    private JTextField jTxfPrecio;
    private JButton jButtonActualizar;
    private JTextField jTxfEliCodigo;
    private JButton jButtonEliminar;
    private JLabel jLabel1;

    public ProductoActualizarEliminar() {
        setContentPane(panelPrincipal);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        //setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        //pack();


    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }



    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
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
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }


}
