package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CarritoListarUsuario extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTable tblCarritos;
    private JButton btnListar;
    private DefaultTableModel modelo;

    public CarritoListarUsuario() {
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

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public void cargarCarritos(List<Carrito> carritos) {
        modelo.setRowCount(0); // Limpiar
        for (Carrito c : carritos) {
            modelo.addRow(new Object[]{
                    c.getCodigo(),
                    c.getFechaCreacion().getTime(),
                    c.calcularSubtotal(),
                    c.calcularIVA(),
                    c.calcularTotal()
            });
        }
    }


}
