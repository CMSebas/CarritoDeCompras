package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Ventana interna (JInternalFrame) para añadir productos al sistema.
 *
 * Permite ingresar los datos de un producto como código, nombre y precio,
 * y proporciona botones para aceptar y limpiar los campos. También puede
 * mostrar productos en consola y cambiar los textos de la interfaz según el idioma.
 *
 * Utiliza internacionalización a través de {@link MensajeInternacionalizacionHandler}.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblPrecio;
    private JLabel lblNombre;
    private JLabel lblCodigo;
    /**
     * Constructor que inicializa la ventana de ingreso de productos.
     * Configura los botones, tamaño, cierre, y comportamiento de limpieza de campos.
     */
    public ProductoAnadirView() {


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

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("productoAnadir.titulo"));
        lblCodigo.setText(mensajes.get("productoAnadir.lblCodigo"));
        lblNombre.setText(mensajes.get("productoAnadir.lblNombre"));
        lblPrecio.setText(mensajes.get("productoAnadir.lblPrecio"));
        btnAceptar.setText(mensajes.get("productoAnadir.btnAceptar"));
        btnLimpiar.setText(mensajes.get("productoAnadir.btnLimpiar"));
        btnLimpiar.setIcon(new ImageIcon(getClass().getResource("/icons/clear.png")));
        btnAceptar.setIcon(new ImageIcon(getClass().getResource("/icons/aceptar.png")));
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
}
