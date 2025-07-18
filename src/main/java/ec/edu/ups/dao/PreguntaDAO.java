package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;
/**
 * Interfaz que define las operaciones de acceso a datos para objetos {@link Pregunta}.
 *
 * Esta interfaz forma parte del patrón DAO (Data Access Object) y puede ser implementada
 * con distintos mecanismos de almacenamiento, como archivos de texto, binarios o memoria.
 *
 * Las preguntas son utilizadas en el sistema como parte del mecanismo de seguridad
 * para recuperación de contraseñas.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 *
 */
public interface PreguntaDAO {
    void crear(Pregunta pregunta);
    Pregunta buscarPorId(int id);
    List<Pregunta> listarTodas();


}
