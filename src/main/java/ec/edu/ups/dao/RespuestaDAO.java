package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

public interface RespuestaDAO {
    void guardarRespuesta(Respuesta respuesta);
    List<Respuesta> buscarPorUsuario(Usuario usuario);  // ✅ ahora recibe Usuario
}
