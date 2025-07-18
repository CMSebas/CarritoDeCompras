package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Clase que representa un ítem dentro de un {@link Carrito},
 * compuesto por un {@link Producto} y la cantidad deseada de dicho producto.
 *
 * Esta clase implementa {@link Serializable} para permitir su almacenamiento
 * en archivos binarios junto con el carrito.
 *
 * Es utilizada como parte de la lista de productos en un carrito de compras.
 *
 */
public class ItemCarrito implements Serializable {
    private static final long serialVersionUID = 1L;

    private Producto producto;
    private int cantidad;

    public ItemCarrito() {
    }
    /**
     * Constructor que inicializa el ítem con un producto y una cantidad.
     *
     * @param producto Producto agregado al carrito
     * @param cantidad Cantidad del producto
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }

}

