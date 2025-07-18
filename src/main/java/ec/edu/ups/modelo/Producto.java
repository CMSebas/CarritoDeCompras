package ec.edu.ups.modelo;

import java.io.Serializable;
/**
 * Representa un producto que puede ser añadido a un carrito de compras.
 *
 * La clase incluye atributos como código, nombre y precio, y es serializable
 * para permitir su almacenamiento en archivos binarios o textos.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String nombre;
    private double precio;
    /**
     * Constructor vacío requerido para ciertas operaciones como deserialización.
     */
    public Producto() {
    }
    /**
     * Crea un nuevo producto con los datos especificados.
     *
     * @param codigo Código único del producto
     * @param nombre Nombre descriptivo del producto
     * @param precio Precio del producto en dólares
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    /**
     * Establece el código del producto.
     *
     * @param codigo Código único
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    /**
     * Establece el nombre del producto.
     *
     * @param nombre Nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Establece el precio del producto.
     *
     * @param precio Precio en dólares
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * Retorna el código del producto.
     *
     * @return Código único
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * Retorna el nombre del producto.
     *
     * @return Nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Retorna el precio del producto.
     *
     * @return Precio en dólares
     */
    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

}