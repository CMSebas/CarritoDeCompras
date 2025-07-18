package ec.edu.ups.servicio;

import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import java.util.List;
/**
 * Interfaz que define los servicios básicos para la gestión de un carrito de compras.
 */
public interface CarritoService {
    /**
     * Agrega un producto al carrito con una cantidad específica.
     *
     * @param producto El producto a agregar.
     * @param cantidad La cantidad del producto.
     */
    void agregarProducto(Producto producto, int cantidad);
    /**
     * Elimina un producto del carrito según su código.
     *
     * @param codigoProducto El código del producto a eliminar.
     */
    void eliminarProducto(int codigoProducto);
    /**
     * Vacía todos los productos del carrito.
     */
    void vaciarCarrito();
    /**
     * Calcula el total a pagar incluyendo impuestos.
     *
     * @return El valor total del carrito.
     */
    double calcularTotal();
    /**
     * Obtiene la lista de ítems actualmente en el carrito.
     *
     * @return Lista de ítems del carrito.
     */
    List<ItemCarrito> obtenerItems();
    /**
     * Verifica si el carrito está vacío.
     *
     * @return true si no hay productos en el carrito, false en caso contrario.
     */
    boolean estaVacio();

}

