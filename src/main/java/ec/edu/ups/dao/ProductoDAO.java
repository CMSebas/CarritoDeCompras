package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas de acceso a datos
 * para objetos de tipo {@link Producto}.
 *
 * Forma parte del patrón DAO (Data Access Object), y permite abstraer
 * el acceso a diferentes fuentes de datos, como memoria, archivos de texto o binarios.
 *
 * Provee métodos para crear, buscar, actualizar, eliminar y listar productos.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public interface ProductoDAO {

    void crear(Producto producto);

    Producto buscarPorCodigo(int codigo);

    List<Producto> buscarPorNombre(String nombre);

    void actualizar(Producto producto);

    void eliminar(int codigo);

    List<Producto> listarTodos();

}
