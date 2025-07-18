package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link PreguntaDAO} que utiliza archivos binarios para
 * almacenar y recuperar objetos {@link Pregunta}.
 *
 * Al inicializarse, precarga un conjunto de 10 preguntas de seguridad si el archivo no existe
 * o está vacío. Esta clase permite la persistencia de preguntas personalizadas también.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class PreguntaDAOBin implements PreguntaDAO {

    private List<Pregunta> preguntas;
    private String rutaArchivo;
    /**
     * Constructor que recibe la ruta del archivo binario donde se almacenarán las preguntas.
     * Si el archivo no existe o está vacío, se cargan 10 preguntas por defecto.
     *
     * @param rutaArchivo Ruta del archivo binario (por ejemplo: "preguntas.dat")
     */

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
    /**
     * Crea una nueva pregunta y la guarda en el archivo binario.
     *
     * @param pregunta Pregunta a registrar
     */
    @Override
    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
        guardarEnArchivo();
    }
    /**
     * Busca una pregunta por su ID único.
     *
     * @param id Identificador de la pregunta
     * @return Pregunta correspondiente al ID o {@code null} si no existe
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
     * Retorna la lista completa de preguntas almacenadas.
     *
     * @return Lista de preguntas
     */
    @Override
    public List<Pregunta> listarTodas() {
        return preguntas;
    }
    /**
     * Guarda la lista de preguntas actual en el archivo binario.
     * Se ejecuta automáticamente luego de agregar una nueva pregunta.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(preguntas);
        } catch (IOException e) {
            System.out.println("Error al guardar preguntas en archivo binario.");
        }
    }
    /**
     * Intenta cargar preguntas desde el archivo binario especificado.
     * Si el archivo no existe o está vacío, se mantiene la lista en blanco.
     */
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
