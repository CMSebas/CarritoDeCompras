package ec.edu.ups.vista;

import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class LoginRecuperar extends JFrame {
    private JButton btnRecuperar;
    private JTextField txtUsuario;
    private JPanel panelPrincipal;
    private JTextField txtRespuesta;
    private JLabel lblPreguntaAle;
    private JButton btnVerificar;
    private JLabel lblUsuario;
    private JLabel lbltittle;
    private Pregunta preguntaActual;

    public LoginRecuperar() {
        setContentPane(panelPrincipal);
        setTitle("loginRecuperar.lbltittle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);


    }

    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("loginRecuperar.titulo"));
        lblUsuario.setText(mensajes.get("loginRecuperar.lblUsuario"));
        lblPreguntaAle.setText(mensajes.get("loginRecuperar.lblPreguntaAle"));
        btnRecuperar.setText(mensajes.get("loginRecuperar.btnRecuperar"));
        btnVerificar.setText(mensajes.get("loginRecuperar.btnVerificar"));
        btnVerificar.setIcon(new ImageIcon(getClass().getResource("/icons/aceptar.png")));
        btnRecuperar.setIcon(new ImageIcon(getClass().getResource("/icons/recuperar.png")));
        lbltittle.setText(mensajes.get("loginRecuperar.lbltittle"));
    }

    public JLabel getLbltittle() {
        return lbltittle;
    }

    public void setLbltittle(JLabel lbltittle) {
        this.lbltittle = lbltittle;
    }

    public JButton getBtnVerificar() {
        return btnVerificar;
    }

    public void setBtnVerificar(JButton btnVerificar) {
        this.btnVerificar = btnVerificar;
    }

    public JButton getBtnRecuperar() {
        return btnRecuperar;
    }

    public JTextField getTxtRespuesta() {
        return txtRespuesta;
    }

    public void setTxtRespuesta(JTextField txtRespuesta) {
        this.txtRespuesta = txtRespuesta;
    }

    public JLabel getLblPreguntaAle() {
        return lblPreguntaAle;
    }

    public void setLblPreguntaAle(JLabel lblPreguntaAle) {
        this.lblPreguntaAle = lblPreguntaAle;
    }

    public Pregunta getPreguntaActual() {
        return preguntaActual;
    }

    public void setPreguntaActual(Pregunta preguntaActual) {
        this.preguntaActual = preguntaActual;
    }

    public void setBtnRecuperar(JButton btnRecuperar) {
        this.btnRecuperar = btnRecuperar;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
}
