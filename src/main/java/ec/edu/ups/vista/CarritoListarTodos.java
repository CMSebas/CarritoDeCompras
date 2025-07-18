package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
/**
 * Clase que representa una ventana interna (JInternalFrame) para listar todos
 * los carritos de compras existentes en el sistema.
 *
 * Muestra una tabla con la información de cada carrito: código, usuario, fecha,
 * subtotal, IVA y total. Permite cambiar los textos mediante internacionalización
 * y formatear los valores de fecha y moneda de acuerdo al idioma y país seleccionados.
 *
 * Pertenece a la capa de vista del sistema.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoListarTodos extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTable tblCarritos;
    private JButton btnListar;
    private DefaultTableModel modelo;
    /**
     * Constructor por defecto que inicializa la ventana de listado de carritos.
     * Configura la tabla, sus columnas y la apariencia general del JInternalFrame.
     */
    public CarritoListarTodos(){
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
        Object[] columnas = {"Código", "Usuario", "Fecha", "Subtotal", "IVA", "Total"};
        modelo.setColumnIdentifiers(columnas);
        tblCarritos.setModel(modelo);
    }
    /**
     * Cambia todos los textos visibles de la interfaz según el idioma seleccionado.
     * Actualiza también los encabezados de la tabla con las etiquetas traducidas.
     *
     * @param mensajes Manejador de internacionalización con los textos localizados.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carritoListarTodos.titulo"));
        btnListar.setText(mensajes.get("carritoListarTodos.btnListar"));
        btnListar.setIcon(new ImageIcon(getClass().getResource("/icons/listar.png")));

        String[] columnas = {
                mensajes.get("carritoListarTodos.col.codigo"),
                mensajes.get("carritoListarTodos.col.usuario"),
                mensajes.get("carritoListarTodos.col.fecha"),
                mensajes.get("carritoListarTodos.col.subtotal"),
                mensajes.get("carritoListarTodos.col.iva"),
                mensajes.get("carritoListarTodos.col.total")
        };

        for (int i = 0; i < columnas.length; i++) {
            tblCarritos.getColumnModel().getColumn(i).setHeaderValue(columnas[i]);
        }

        tblCarritos.getTableHeader().repaint();
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

    public JPanel getPanelPrincipal2() {
        return panelPrincipal2;
    }

    public void setPanelPrincipal2(JPanel panelPrincipal2) {
        this.panelPrincipal2 = panelPrincipal2;
    }

    public JTable getTblCarritos() {
        return tblCarritos;
    }

    public void setTblCarritos(JTable tblCarritos) {
        this.tblCarritos = tblCarritos;
    }
    /**
     * Carga la lista de carritos en la tabla con formato de fecha y moneda
     * según la configuración regional (locale) especificada.
     *
     * @param carritos Lista de carritos a mostrar.
     * @param locale Configuración regional para formateo de fecha y moneda.
     */
    public void cargarCarritos(List<Carrito> carritos, Locale locale) {
        modelo.setRowCount(0);
        for (Carrito c : carritos) {
            Object[] fila = {
                    c.getCodigo(),
                    c.getUsuario().getUsername(),
                    FormateadorUtils.formatearFecha(c.getFechaCreacion().getTime(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularSubtotal(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularIVA(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularTotal(), locale)
            };
            modelo.addRow(fila);
        }
    }
}
