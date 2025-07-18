package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link RespuestaDAO} que almacena las respuestas de seguridad
 * en memoria, utilizando una lista interna.
 *
 * Esta implementación es útil para pruebas o cuando no se requiere persistencia
 * en disco. No guarda los datos una vez cerrada la aplicación.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class RespuestaDAOMemoria implements RespuestaDAO {
    private List<Respuesta> respuestas = new ArrayList<>();
    /**
     * Guarda una respuesta de seguridad asociada a un usuario.
     *
     * @param respuesta Objeto {@link Respuesta} a guardar
     */
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }
    /**
     * Busca todas las respuestas asociadas a un usuario específico.
     *
     * @param usuario Usuario cuyas respuestas se desean obtener
     * @return Lista de respuestas correspondientes al usuario
     */
    public List<Respuesta> buscarPorUsuario(Usuario usuario) {
        List<Respuesta> resultado = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}