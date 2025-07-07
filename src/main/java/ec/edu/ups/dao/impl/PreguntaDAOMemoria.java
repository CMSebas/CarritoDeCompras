package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {
    private List<Pregunta> preguntas;

    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<>();

        // Agregar las 10 preguntas por defecto
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

    @Override
    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    @Override
    public Pregunta buscarPorId(int id) {
        for (Pregunta p : preguntas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pregunta> listarTodas() {
        return preguntas;
    }
}
