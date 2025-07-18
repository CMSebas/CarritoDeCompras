package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas para la gestión de objetos {@link Carrito}.
 *
 * Esta interfaz forma parte del patrón DAO (Data Access Object), y puede ser implementada
 * con diferentes tipos de almacenamiento (memoria, archivos binarios, bases de datos, etc.).
 *
 * Las operaciones incluyen creación, búsqueda, actualización, eliminación y listado.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public interface CarritoDAO {
    /**
     * Crea y almacena un nuevo carrito.
     *
     * @param carrito Objeto {@link Carrito} a registrar
     */
    void crear(Carrito carrito);
    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito
     * @return El carrito encontrado o {@code null} si no existe
     */
    Carrito buscarPorCodigo(int codigo);
    List<Carrito> buscarPorUsuario(String username);
    /**
     * Actualiza un carrito existente.
     *
     * @param carrito Carrito con los nuevos datos
     */
    void actualizar(Carrito carrito);
    /**
     * Elimina un carrito por su código.
     *
     * @param codigo Código del carrito a eliminar
     */
    void eliminar(int codigo);
    /**
     * Retorna la lista completa de carritos registrados.
     *
     * @return Lista de carritos
     */
    List<Carrito> listarTodos();


}