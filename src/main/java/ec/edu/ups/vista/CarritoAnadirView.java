package ec.edu.ups.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CarritoAnadirView extends JInternalFrame {

    private JTextField TxtCodigo;
    private JTextField TxtNombre;
    private JTextField TxtPrecio;
    private JButton BtnBuscar;
    private JButton BtnAnadir;
    private JTable table1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton guardarButton;
    private JPanel panelPrincipalAn;

    public JTextField getTxtCodigo() {
        return TxtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        TxtCodigo = txtCodigo;
    }

    public CarritoAnadirView(){

        setContentPane(panelPrincipalAn);
        setTitle("Datos del Producto");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio"}, 0);
        table1.setModel(modelo);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //pack();





    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public JTextField getTxtPrecio() {
        return TxtPrecio;
    }

    public JButton getBtnBuscar() {
        return BtnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        BtnBuscar = btnBuscar;
    }

    public JButton getBtnAnadir() {
        return BtnAnadir;
    }

    public void setBtnAnadir(JButton btnAnadir) {
        BtnAnadir = btnAnadir;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        TxtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return TxtNombre;
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTable1(JTable table1) {
        this.table1 = table1;
    }

    public void setTxtNombre(JTextField txtNombre) {
        TxtNombre = txtNombre;
    }
}
