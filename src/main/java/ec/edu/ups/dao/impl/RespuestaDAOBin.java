package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link RespuestaDAO} que almacena las respuestas
 * de seguridad en un archivo binario.
 *
 * Esta clase permite guardar respuestas asociadas a preguntas y usuarios
 * para su posterior validación durante la recuperación de contraseña.
 *
 * Utiliza serialización de objetos Java para persistir las respuestas.
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class RespuestaDAOBin implements RespuestaDAO {

    private List<Respuesta> respuestas;
    private String rutaArchivo;
    /**
     * Constructor que recibe la ruta del archivo binario donde se guardarán las respuestas.
     * Carga las respuestas desde el archivo si existe.
     *
     * @param rutaArchivo Ruta del archivo binario (por ejemplo: "respuestas.dat")
     */
    public RespuestaDAOBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.respuestas = new ArrayList<>();
        cargarDesdeArchivo();
    }
    /**
     * Guarda una nueva respuesta en la lista y actualiza el archivo binario.
     *
     * @param respuesta Objeto de tipo {@link Respuesta} a guardar
     */
    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        guardarEnArchivo();
        System.out.println("Respuesta guardada:");
        System.out.println("Usuario: " + respuesta.getUsuario().getUsername());
        System.out.println("Pregunta: " + respuesta.getPregunta().getTexto());
        System.out.println("Respuesta: " + respuesta.getTexto());
    }
    /**
     * Busca todas las respuestas asociadas a un usuario específico.
     *
     * @param usuario Usuario cuyas respuestas se desean obtener
     * @return Lista de respuestas asociadas al usuario
     */
    @Override
    public List<Respuesta> buscarPorUsuario(Usuario usuario) {
        List<Respuesta> resultado = new ArrayList<>();
        for (Respuesta r : respuestas) {
            if (r.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    /**
     * Guarda la lista de respuestas en el archivo binario, sobrescribiéndolo completamente.
     */
    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(respuestas);
        } catch (IOException e) {
            System.out.println("Error al guardar respuestas en archivo binario.");

        }
    }
    /**
     * Carga las respuestas desde el archivo binario especificado.
     * Si el archivo no existe o no se puede leer, la lista queda vacía.
     */
    private void cargarDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                respuestas = (List<Respuesta>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar respuestas desde archivo binario (puede no existir aún).");
        }
    }
}