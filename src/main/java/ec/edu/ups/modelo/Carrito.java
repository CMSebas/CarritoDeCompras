package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;


/**
 * Clase que representa un carrito de compras dentro del sistema.
 * Cada carrito está asociado a un {@link Usuario}, contiene una lista de
 * {@link ItemCarrito}, y puede calcular subtotales, IVA y total final.
 *
 * Esta clase implementa {@link Serializable} para poder ser almacenada
 * en archivos binarios si es necesario.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class Carrito implements Serializable {
    private static final long serialVersionUID = 1L;

    private final double IVA = 0.12;
    private Usuario usuario;

    private static int contador = 1;

    private int codigo;

    private GregorianCalendar fechaCreacion;

    private List<ItemCarrito> items;

    /**
     * Constructor que inicializa el carrito con el usuario asociado y la fecha actual.
     *
     * @param usuario Usuario propietario del carrito
     */
    public Carrito(Usuario usuario) {
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = usuario;
    }
    /**
     * Devuelve el usuario asociado al carrito.
     *
     * @return Usuario propietario
     */
    public Usuario getUsuario() {
        return usuario;
    }
    public void asignarCodigo() {
        this.codigo = contador++;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }
    /**
     * Elimina un producto del carrito por su código.
     *
     * @param codigoProducto Código del producto a eliminar
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }
    /**
     * Devuelve la lista de ítems contenidos en el carrito.
     *
     * @return Lista de {@link ItemCarrito}
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }
    /**
     * Calcula el subtotal del carrito (sin IVA).
     *
     * @return Monto subtotal
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }
    /**
     * Calcula el valor del IVA (12%) sobre el subtotal.
     *
     * @return Monto de IVA
     */
    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    public void setItems(List<ItemCarrito> items) {
        this.items = items;
    }
    /**
     * Calcula el total del carrito (subtotal + IVA).
     *
     * @return Total a pagar
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaCreacion +
                ", items=" + items +
                '}';
    }
}

