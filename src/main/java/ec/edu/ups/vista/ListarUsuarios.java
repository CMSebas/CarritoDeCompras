package ec.edu.ups.vista;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * Ventana interna que permite listar los usuarios registrados en el sistema.
 *
 * Esta vista muestra una tabla con los datos de los usuarios, incluyendo nombre,
 * apellido, nombre de usuario y rol. También incluye soporte para internacionalización
 * del contenido textual.
 *
 * Extiende {@link JInternalFrame} como parte del diseño MDI (Multiple Document Interface).
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */

public class ListarUsuarios extends JInternalFrame {
    private JTable tblUsuarios;
    private JButton btnListar;
    private JPanel panelPrincipal;
    private DefaultTableModel modeloTabla;

    public ListarUsuarios() {
        setContentPane(panelPrincipal);
        setTitle("Listar Usuarios");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setLocationRelativeTo(null);
        //setVisible(true);
        //pack();

        // Definir columnas del modelo
        String[] columnas = {"Nombre", "Apellido", "Usuario", "Rol"};
        modeloTabla = new DefaultTableModel(columnas, 0);

        // ⚠️ No reemplaces la tabla, solo aplica el modelo
        tblUsuarios.setModel(modeloTabla);

    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("listarUsuario.titulo"));
        btnListar.setText(mensajes.get("listarUsuario.boton"));
        btnListar.setIcon(new ImageIcon(getClass().getResource("/icons/listar.png")));
        modeloTabla.setColumnIdentifiers(new String[]{
                mensajes.get("usuario.nombre"),
                mensajes.get("usuario.apellido"),
                mensajes.get("usuario.usuario"),
                mensajes.get("usuario.rol")
        });
    }

    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    public void setTblUsuarios(JTable tblUsuarios) {
        this.tblUsuarios = tblUsuarios;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public void cargarUsuarios(Object[][] datos) {
        modeloTabla.setRowCount(0); // limpia antes de cargar
        for (Object[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }
}
