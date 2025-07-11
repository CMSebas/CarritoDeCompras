package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.ArrayList;
import java.util.List;

public class RespuestaDAOMemoria implements RespuestaDAO {
    private List<Respuesta> respuestas = new ArrayList<>();

    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }

    public List<Respuesta> buscarPorUsuario(String username) {
        List<Respuesta> resultado = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsuario().equalsIgnoreCase(username)) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}