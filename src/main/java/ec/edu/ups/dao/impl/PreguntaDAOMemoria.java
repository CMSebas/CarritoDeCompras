package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link PreguntaDAO} que almacena las preguntas de seguridad
 * en memoria utilizando una lista. Ideal para pruebas, entornos temporales
 * o cuando no se requiere persistencia en disco.
 *
 * Al instanciarse, precarga 10 preguntas comunes de seguridad.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class PreguntaDAOMemoria implements PreguntaDAO {
    private List<Pregunta> preguntas;
    /**
     * Constructor que inicializa la lista de preguntas en memoria.
     * Se cargan 10 preguntas de seguridad predeterminadas.
     */
    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<>();


        preguntas.add(new Pregunta(1, "¿Cuál es tu comida favorita?"));
        preguntas.add(new Pregunta(2, "¿Nombre de tu primera mascota?"));
        preguntas.add(new Pregunta(3, "¿Ciudad donde naciste?"));
        preguntas.add(new Pregunta(4, "¿Nombre de tu mejor amigo/a?"));
        preguntas.add(new Pregunta(5, "¿A qué escuela fuiste?"));
        preguntas.add(new Pregunta(6, "¿Cuál es tu película favorita?"));
        preguntas.add(new Pregunta(7, "¿Nombre de tu personaje favorito?"));
        preguntas.add(new Pregunta(8, "¿Marca de tu primer celular?"));
        preguntas.add(new Pregunta(9, "¿Lugar que sueñas con visitar?"));
        preguntas.add(new Pregunta(10, "¿Cuál es tu color favorito?"));
    }
    /**
     * Agrega una nueva pregunta a la lista en memoria.
     *
     * @param pregunta Pregunta a agregar
     */
    @Override
    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
    }
    /**
     * Busca una pregunta en la lista por su ID.
     *
     * @param id ID de la pregunta
     * @return La pregunta encontrada o {@code null} si no existe
     */
    @Override
    public Pregunta buscarPorId(int id) {
        for (Pregunta p : preguntas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    /**
     * Devuelve la lista completa de preguntas cargadas en memoria.
     *
     * @return Lista de preguntas
     */
    @Override
    public List<Pregunta> listarTodas() {
        return preguntas;
    }
}
