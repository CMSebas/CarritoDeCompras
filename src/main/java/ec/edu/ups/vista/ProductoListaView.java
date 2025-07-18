package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * Ventana interna (JInternalFrame) para listar y buscar productos registrados en el sistema.
 * Permite visualizar productos en una tabla, buscar por nombre y cambiar idioma dinámicamente.
 * Utiliza el patrón MVC, actuando como la vista del módulo de productos.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel plPrincipal2;
    private JButton btnListar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    /**
     * Constructor que inicializa los componentes de la interfaz gráfica,
     * configurando el modelo de la tabla y los botones principales.
     */
    public ProductoListaView() {

        setContentPane(plPrincipal2);
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
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        lblNombre.setText(mensajes.get("producto.lista.lblNombre"));
        setTitle(mensajes.get("productoLista.titulo"));
        btnBuscar.setText(mensajes.get("productoLista.btnBuscar"));
        btnListar.setText(mensajes.get("productoLista.btnListar"));
        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/icons/search.png")));
        btnListar.setIcon(new ImageIcon(getClass().getResource("/icons/listar.png")));

        String[] columnas = {
                mensajes.get("productoLista.tbl.codigo"),
                mensajes.get("productoLista.tbl.nombre"),
                mensajes.get("productoLista.tbl.precio")
        };

        modelo.setColumnIdentifiers(columnas); // cambia encabezados
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JPanel getPlPrincipal2() {
        return plPrincipal2;
    }

    public void setPlPrincipal2(JPanel plPrincipal2) {
        this.plPrincipal2 = plPrincipal2;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return plPrincipal2;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.plPrincipal2 = panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(List<Producto> listaProductos, String moneda) {
        modelo.setNumRows(0);

        String simbolo = moneda.equals("EUR") ? "€" : "$";

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    simbolo + String.format("%.2f", producto.getPrecio())
            };
            modelo.addRow(fila);
        }
    }
}
