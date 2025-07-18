package ec.edu.ups.controlador;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.LoginRecuperar;
import ec.edu.ups.vista.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class LoginController {

    private final UsuarioDAO usuarioDAO;
    private final PreguntaDAO preguntaDAO;
    private final RespuestaDAO respuestaDAO;
    private final LoginRecuperar recuperarView;
    private Pregunta preguntaActual;
    private String usuarioActual;

    public LoginController(UsuarioDAO usuarioDAO, PreguntaDAO preguntaDAO, RespuestaDAO respuestaDAO, LoginRecuperar recuperarView) {
        this.usuarioDAO = usuarioDAO;
        this.preguntaDAO = preguntaDAO;
        this.respuestaDAO = respuestaDAO;
        this.recuperarView = recuperarView;
        configurar();
    }

    private void configurar() {
        recuperarView.getBtnRecuperar().addActionListener(e -> {
            usuarioActual = recuperarView.getTxtUsuario().getText().trim();
            Usuario u = usuarioDAO.buscarPorUsername(usuarioActual);

            if (u == null) {
                JOptionPane.showMessageDialog(recuperarView, Main.mensajes.get("loginRecuperar.usuarioNoEncontrado"));
                return;
            }


            List<Respuesta> respuestas = respuestaDAO.buscarPorUsuario(usuarioActual);
            if (respuestas.size() < 3) {
                JOptionPane.showMessageDialog(recuperarView, Main.mensajes.get("loginRecuperar.pocasRespuestas"));
                return;
            }


            Respuesta respuestaSeleccionada = respuestas.get(new Random().nextInt(respuestas.size()));


            Pregunta pregunta = preguntaDAO.buscarPorId(respuestaSeleccionada.getIdPregunta());
            if (pregunta != null) {
                preguntaActual = pregunta;
                recuperarView.setPreguntaActual(pregunta); // Guarda la pregunta actual
                recuperarView.getLblPreguntaAle().setText(pregunta.getTexto());
            } else {
                JOptionPane.showMessageDialog(recuperarView, Main.mensajes.get("lloginRecuperar.errorPregunta"));
            }
        });

        recuperarView.getBtnVerificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String respuestaIngresada = recuperarView.getTxtRespuesta().getText();


                List<Respuesta> respuestas = respuestaDAO.buscarPorUsuario(usuarioActual);

                boolean acierto = false;
                for (Respuesta r : respuestas) {
                    if (r.getIdPregunta() == preguntaActual.getId()
                            && r.getTexto().equalsIgnoreCase(respuestaIngresada)) {
                        acierto = true;
                        break;
                    }
                }

                if (acierto) {
                    Usuario u = usuarioDAO.buscarPorUsername(usuarioActual);
                    JOptionPane.showMessageDialog(recuperarView, Main.mensajes.get("loginRecuperar.contrasena") + u.getContrasenia());
                    recuperarView.dispose();
                } else {
                    JOptionPane.showMessageDialog(recuperarView, Main.mensajes.get("loginRecuperar.respuestaIncorrecta"));
                }
            }
        });


    }
}
