package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;

import java.util.ArrayList;
import java.util.List;
/**
 * Implementación de {@link CarritoDAO} que gestiona los objetos {@link Carrito}
 * en memoria utilizando una lista interna. Ideal para pruebas o sesiones temporales
 * donde no se requiere persistencia en disco.
 *
 * Todos los cambios se mantienen únicamente durante la ejecución del programa.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class CarritoDAOMemoria implements CarritoDAO {

    private List<Carrito> carritos;
    /**
     * Constructor que inicializa la lista de carritos como vacía.
     */
    public CarritoDAOMemoria() {
        carritos = new ArrayList<>();
    }
    /**
     * Crea un nuevo carrito, asignándole un código automáticamente y agregándolo a la lista.
     *
     * @param carrito Carrito a registrar
     */
    @Override
    public void crear(Carrito carrito) {
        carrito.asignarCodigo();
        carritos.add(carrito);
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
     * Busca todos los carritos asociados a un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario
     * @return Lista de carritos del usuario
     */
    @Override
    public List<Carrito> buscarPorUsuario(String username) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario() != null && carrito.getUsuario().getUsername().equalsIgnoreCase(username)) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }

    /**
     * Actualiza un carrito existente en la lista, buscando por su código.
     *
     * @param carrito Carrito actualizado
     */
    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
            }
        }
    }


    /**
     * Elimina un carrito por su código.
     *
     * @param codigo Código del carrito a eliminar
     */
    @Override
    public void eliminar(int codigo) {
        carritos.removeIf(c -> c.getCodigo() == codigo);
    }
    /**
     * Devuelve la lista completa de carritos en memoria.
     *
     * @return Lista de todos los carritos
     */
    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }
}