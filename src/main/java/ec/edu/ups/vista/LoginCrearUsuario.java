package ec.edu.ups.vista;
/**
 * Ventana principal para el registro de nuevos usuarios.
 *
 * Esta interfaz permite ingresar datos personales, nombre de usuario, contraseña
 * y seleccionar 3 preguntas de seguridad con sus respectivas respuestas.
 *
 * Utiliza componentes Swing para la interfaz gráfica y admite internacionalización
 * mediante {@link MensajeInternacionalizacionHandler}.
 *
 * Extiende {@link JFrame}.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
import ec.edu.ups.modelo.Pregunta;

import javax.swing.*;
import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import java.util.List;

public class LoginCrearUsuario extends JFrame {
    private JTextField txtNombre;

    private JTextField txtApellido;
    private JTextField txtUsuario;

    private JButton btnCrear;
    private JTextField txtContrasena;
    private JPanel panelPrincipal;
    private JComboBox cbxPregunta1;
    private JTextField txtRespuesta1;
    private JComboBox cbxPregunta2;
    private JTextField txtRespuesta2;
    private JComboBox cbxPregunta3;
    private JTextField txtRespuesta3;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblUser;
    private JLabel lblContra;
    private JLabel lbltittle;


    public LoginCrearUsuario(PreguntaDAO preguntaDAO) {
        setContentPane(panelPrincipal);
        setTitle("loginCrear.lbltittle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        List<Pregunta> preguntas = preguntaDAO.listarTodas();
        for (Pregunta p : preguntas) {
            cbxPregunta1.addItem(p);
            cbxPregunta2.addItem(p);
            cbxPregunta3.addItem(p);
        }



    }

    public JLabel getLbltittle() {
        return lbltittle;
    }

    public void setLbltittle(JLabel lbltittle) {
        this.lbltittle = lbltittle;
    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("loginCrear.titulo"));
        btnCrear.setText(mensajes.get("loginCrear.btnCrear"));
        btnCrear.setIcon(new ImageIcon(getClass().getResource("/icons/crear.png")));

        lblNombre.setText(mensajes.get("loginCrear.lblNombre"));
        lblApellido.setText(mensajes.get("loginCrear.lblApellido"));
        lblUser.setText(mensajes.get("loginCrear.lblUser"));
        lblContra.setText(mensajes.get("loginCrear.lblContra"));
        lbltittle.setText(mensajes.get("loginCrear.lbltittle"));
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void cargarPreguntasConIdioma(List<Pregunta> preguntas, MensajeInternacionalizacionHandler mensajes) {
        cbxPregunta1.removeAllItems();
        cbxPregunta2.removeAllItems();
        cbxPregunta3.removeAllItems();

        for (Pregunta p : preguntas) {
            String textoTraducido = mensajes.get("pregunta." + p.getId());
            p.setTexto(textoTraducido);
            cbxPregunta1.addItem(p);
            cbxPregunta2.addItem(p);
            cbxPregunta3.addItem(p);
        }
    }



    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }



    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }



    public JButton getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(JButton btnCrear) {
        this.btnCrear = btnCrear;
    }

    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(JTextField txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public int getIdPreguntaSeleccionada(int numero) {
        JComboBox<Pregunta> cbx = switch (numero) {
            case 1 -> cbxPregunta1;
            case 2 -> cbxPregunta2;
            case 3 -> cbxPregunta3;
            default -> null;
        };
        return ((Pregunta) cbx.getSelectedItem()).getId();
    }

    public String getRespuestaEscrita(int numero) {
        return switch (numero) {
            case 1 -> txtRespuesta1.getText().trim();
            case 2 -> txtRespuesta2.getText().trim();
            case 3 -> txtRespuesta3.getText().trim();
            default -> "";
        };
    }
}
