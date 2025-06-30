package ec.edu.ups.vista;

import javax.swing.*;

public class LoginView extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JTextField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrate;

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
