package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de {@link ProductoDAO} que utiliza archivos de texto
 * para almacenar y recuperar objetos de tipo {@link Producto}.
 *
 * Cada producto se guarda en una línea del archivo con formato: código;nombre;precio
 * Este DAO es útil para persistencia simple sin necesidad de bases de datos ni archivos binarios.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class ProductoDAOTexto implements ProductoDAO {

    private final String rutaArchivo;
    private List<Producto> productos;
    /**
     * Constructor que recibe la ruta del archivo de texto.
     * Al inicializarse, carga los productos existentes desde el archivo.
     *
     * @param rutaArchivo Ruta del archivo de texto (ej. "productos.txt")
     */
    public ProductoDAOTexto(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.productos = new ArrayList<>();
        cargarDesdeArchivo();
    }
    /**
     * Crea un nuevo producto y lo guarda en el archivo.
     *
     * @param producto Producto a registrar
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
        guardarEnArchivo();
    }
    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto
     * @return El producto encontrado o {@code null} si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }
    /**
     * Busca productos cuyo nombre coincida exactamente con el nombre proporcionado,
     * ignorando mayúsculas y minúsculas.
     *
     * @param nombre Nombre del producto
     * @return Lista de productos encontrados
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }
    /**
     * Actualiza un producto existente con el mismo código en la lista.
     * Luego, reescribe el archivo completo.
     *
     * @param producto Producto actualizado
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
        guardarEnArchivo();
    }
    /**
     * Elimina un producto por su código, si existe.
     * Luego, reescribe el archivo completo.
     *
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == codigo) {
                productos.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }
    /**
     * Lista todos los productos actualmente cargados en memoria.
     *
     * @return Lista de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
    /**
     * Guarda todos los productos de la lista en el archivo de texto,
     * sobreescribiéndolo completamente.
     */
    private void guardarEnArchivo() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Producto p : productos) {
                escritor.write(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio());
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos.");
        }
    }
    /**
     * Carga los productos desde el archivo de texto si existe.
     * Cada línea se espera en el formato: código;nombre;precio
     */
    private void cargarDesdeArchivo() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    productos.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar productos desde archivo.");
        }
    }
}