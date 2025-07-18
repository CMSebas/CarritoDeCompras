package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
/**
 * Ventana interna (JInternalFrame) encargada de mostrar todos los carritos
 * de compras asociados a un usuario específico.
 *
 * Esta vista forma parte de la capa de presentación (MVC) y permite visualizar
 * una tabla con los carritos que ha registrado un usuario, mostrando su código,
 * fecha, subtotal, IVA y total. También soporta internacionalización e
 * integración con formatos de moneda y fecha.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoListarUsuario extends JInternalFrame {
    private JPanel panelPrincipal2;
    private JTable tblCarritos;
    private JButton btnListar;
    private DefaultTableModel modelo;
    /**
     * Constructor por defecto que inicializa la ventana y configura
     * los componentes gráficos, incluyendo la tabla con sus columnas.
     */
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
    /**
     * Cambia los textos de los botones, etiquetas y cabeceras de tabla
     * en función del idioma definido por el objeto de internacionalización.
     *
     * @param mensajes Manejador de textos internacionalizados.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("carritoListarUsuario.titulo"));
        btnListar.setText(mensajes.get("carritoListarUsuario.btnListar"));
        btnListar.setIcon(new ImageIcon(getClass().getResource("/icons/listar.png")));

        String[] columnas = {
                mensajes.get("carritoListarUsuario.col.codigo"),
                mensajes.get("carritoListarUsuario.col.fecha"),
                mensajes.get("carritoListarUsuario.col.subtotal"),
                mensajes.get("carritoListarUsuario.col.iva"),
                mensajes.get("carritoListarUsuario.col.total")
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
    /**
     * Carga la tabla con los carritos pertenecientes a un usuario específico,
     * aplicando formato de fecha y moneda según el locale proporcionado.
     *
     * @param carritos Lista de carritos a mostrar.
     * @param locale Configuración regional (idioma y país) para formatear valores.
     */
    public void cargarCarritos(List<Carrito> carritos, Locale locale) {
        modelo.setRowCount(0);
        for (Carrito c : carritos) {
            Object[] fila = {
                    c.getCodigo(),
                    FormateadorUtils.formatearFecha(c.getFechaCreacion().getTime(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularSubtotal(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularIVA(), locale),
                    FormateadorUtils.formatearMoneda(c.calcularTotal(), locale)
            };
            modelo.addRow(fila);
        }
    }


}
