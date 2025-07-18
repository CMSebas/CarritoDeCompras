package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOBin implements PreguntaDAO {

    private List<Pregunta> preguntas;
    private String rutaArchivo;

    public PreguntaDAOBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.preguntas = new ArrayList<>();
        cargarDesdeArchivo();


        if (preguntas.isEmpty()) {
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
            guardarEnArchivo();
        }
    }

    @Override
    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
        guardarEnArchivo();
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

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(preguntas);
        } catch (IOException e) {
            System.out.println("Error al guardar preguntas en archivo binario.");
        }
    }

    private void cargarDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                preguntas = (List<Pregunta>) obj;
                System.out.println("Preguntas cargadas:");
                for (Pregunta p : preguntas) {
                    System.out.println(p.getId() + ". " + p.getTexto());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar preguntas desde archivo binario (puede no existir aún).");
        }
    }
}
