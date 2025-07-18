package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOBin implements CarritoDAO {

    private List<Carrito> carritos;
    private String rutaArchivo;

    public CarritoDAOBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.carritos = new ArrayList<>();
        cargarDesdeArchivo();
    }

    @Override
    public void crear(Carrito carrito) {
        carrito.asignarCodigo();
        carritos.add(carrito);
        guardarEnArchivo();
    }

    public void formatearArchivo() {
        carritos.clear();
        guardarEnArchivo();
        System.out.println("Archivo de carritos formateado correctamente.");
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Carrito> buscarPorUsuario(String username) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null &&
                    carrito.getUsuario().getUsername().equalsIgnoreCase(username)) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                guardarEnArchivo();
                return;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == codigo) {
                carritos.remove(i);
                guardarEnArchivo();
                return;
            }
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(carritos);
            System.out.println("Carritos guardados:");
            for (Carrito c : carritos) {
                System.out.println("Código: " + c.getCodigo() +
                        ", Usuario: " + (c.getUsuario() != null ? c.getUsuario().getUsername() : "null") +
                        ", Total:" + c.calcularTotal());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar carritos en archivo.");

        }
    }

    private void cargarDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                carritos = (List<Carrito>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar carritos desde archivo .");
        }
    }
}