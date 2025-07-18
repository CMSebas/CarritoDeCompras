package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RespuestaDAOBin implements RespuestaDAO {

    private List<Respuesta> respuestas;
    private String rutaArchivo;

    public RespuestaDAOBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.respuestas = new ArrayList<>();
        cargarDesdeArchivo();
    }

    @Override
    public void guardarRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
        guardarEnArchivo();
        System.out.println("Respuesta guardada:");
        System.out.println("Usuario: " + respuesta.getUsuario().getUsername());
        System.out.println("Pregunta: " + respuesta.getPregunta().getTexto());
        System.out.println("Respuesta: " + respuesta.getTexto());
    }

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

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(respuestas);
        } catch (IOException e) {
            System.out.println("Error al guardar respuestas en archivo binario.");

        }
    }

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