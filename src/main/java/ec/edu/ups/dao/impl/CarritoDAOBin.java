package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link CarritoDAO} que almacena y recupera objetos {@link Carrito}
 * utilizando archivos binarios (.dat). Esta clase permite persistencia de datos
 * entre ejecuciones del sistema.
 *
 * Al iniciar, se intenta cargar los datos desde el archivo binario proporcionado.
 * Cualquier operación de modificación actualiza automáticamente el archivo.
 *
 */
public class CarritoDAOBin implements CarritoDAO {

    private List<Carrito> carritos;
    private String rutaArchivo;
    /**
     * Constructor que recibe la ruta del archivo binario donde se almacenan los carritos.
     * Si el archivo existe, carga los carritos almacenados. Si no, inicia una lista vacía.
     *
     * @param rutaArchivo Ruta completa del archivo binario (.dat)
     */
    public CarritoDAOBin(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.carritos = new ArrayList<>();
        cargarDesdeArchivo();
    }
    /**
     * Crea un nuevo carrito, asigna un código automáticamente y lo guarda en el archivo.
     *
     * @param carrito Carrito a registrar
     */
    @Override
    public void crear(Carrito carrito) {
        carrito.asignarCodigo();
        carritos.add(carrito);
        guardarEnArchivo();
    }
    /**
     * Elimina todos los carritos y sobrescribe el archivo, dejándolo vacío.
     * Se utiliza normalmente en procesos de reinicio o pruebas.
     */
    public void formatearArchivo() {
        carritos.clear();
        guardarEnArchivo();
        System.out.println("Archivo de carritos formateado correctamente.");
    }
    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito
     * @return El carrito encontrado o {@code null} si no existe
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }
    /**
     * Busca todos los carritos pertenecientes a un usuario dado por su nombre de usuario.
     *
     * @param username Nombre de usuario del propietario del carrito
     * @return Lista de carritos asociados al usuario
     */
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
    /**
     * Actualiza un carrito existente si se encuentra por código.
     *
     * @param carrito Carrito con los nuevos datos
     */
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
    /**
     * Elimina un carrito por su código, si existe.
     *
     * @param codigo Código del carrito a eliminar
     */
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
    /**
     * Retorna todos los carritos registrados actualmente.
     *
     * @return Lista completa de carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }
    /**
     * Guarda la lista actual de carritos en el archivo binario.
     * Se ejecuta automáticamente luego de crear, actualizar o eliminar.
     */
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
    /**
     * Carga los carritos desde el archivo binario especificado.
     * Si el archivo no existe o no se puede leer, se ignora el error y se mantiene la lista vacía.
     */
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