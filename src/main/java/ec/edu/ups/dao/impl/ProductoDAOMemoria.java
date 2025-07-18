package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Implementación de {@link ProductoDAO} que utiliza una lista en memoria
 * para gestionar objetos de tipo {@link Producto}.
 *
 * Es útil para pruebas, demostraciones o aplicaciones que no requieren
 * persistencia en archivos o bases de datos.
 *
 * Todas las operaciones CRUD se realizan sobre una lista interna.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class    ProductoDAOMemoria implements ProductoDAO {

    private List<Producto> productos;

    /**
     * Constructor que inicializa la lista de productos como vacía.
     */
    public ProductoDAOMemoria() {
        productos = new ArrayList<Producto>();
    }
    /**
     * Registra un nuevo producto en la lista.
     *
     * @param producto Producto a agregar
     */
    @Override
    public void crear(Producto producto) {
        productos.add(producto);
    }
    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto
     * @return Producto encontrado o {@code null} si no existe
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : productos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }
    /**
     * Busca todos los productos que coincidan exactamente con el nombre dado (sin importar mayúsculas/minúsculas).
     *
     * @param nombre Nombre del producto
     * @return Lista de productos encontrados
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                productosEncontrados.add(producto);
            }
        }
        return productosEncontrados;
    }
    /**
     * Actualiza un producto existente, reemplazando el que tenga el mismo código.
     *
     * @param producto Producto actualizado
     */
    @Override
    public void actualizar(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
            }
        }
    }
    /**
     * Elimina un producto de la lista por su código.
     *
     * @param codigo Código del producto a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        Iterator<Producto> iterator = productos.iterator();
        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }
    /**
     * Retorna la lista completa de productos almacenados en memoria.
     *
     * @return Lista de productos
     */
    @Override
    public List<Producto> listarTodos() {
        return productos;
    }
}
