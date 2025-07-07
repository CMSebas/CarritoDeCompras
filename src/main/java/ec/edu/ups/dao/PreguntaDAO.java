package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public interface PreguntaDAO {
    void crear(Pregunta pregunta);
    Pregunta buscarPorId(int id);
    List<Pregunta> listarTodas();


}
