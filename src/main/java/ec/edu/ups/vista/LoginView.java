package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JTextField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrate;
    private JButton btnRecuperar;
    private JLabel lblUsuario;
    private JLabel lblContrasena;

    public LoginView() {
        setContentPane(panelPrincipal);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("login.titulo"));
        lblUsuario.setText(mensajes.get("login.usuario"));
        lblContrasena.setText(mensajes.get("login.contrasena"));
        btnIniciarSesion.setText(mensajes.get("login.btnIniciarSesion"));
        btnRegistrate.setText(mensajes.get("login.btnRegistrate"));
        btnRecuperar.setText(mensajes.get("login.btnRecuperar"));
        btnIniciarSesion.setIcon(new ImageIcon(getClass().getResource("/icons/usuario.png")));
        btnRecuperar.setIcon(new ImageIcon(getClass().getResource("/icons/recuperar.png")));
        btnRegistrate.setIcon(new ImageIcon(getClass().getResource("/icons/crear.png")));
    }

    public JButton getBtnRecuperar() {
        return btnRecuperar;
    }

    public void setBtnRecuperar(JButton btnRecuperar) {
        this.btnRecuperar = btnRecuperar;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
    }

    public JTextField getTxtContrasenia() {
        return txtContrasenia;
    }

    public void setTxtContrasenia(JTextField txtContrasenia) {
        this.txtContrasenia = txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(JButton btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    public JButton getBtnRegistrate() {
        return btnRegistrate;
    }

    public void setBtnRegistrate(JButton btnRegistrate) {
        this.btnRegistrate = btnRegistrate;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
