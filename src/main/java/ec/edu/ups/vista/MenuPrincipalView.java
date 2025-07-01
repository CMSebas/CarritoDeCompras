package ec.edu.ups.vista;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuProducto2;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;
    private JDesktopPane jDesktopPane;
    private JMenuItem menuItemCrearCarrito;
    private Usuario usuarioLogueado;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemListarCarritosUsuario;
    private JMenuItem menuItemActualizarCarrito;
    private JMenuItem menuItemListarTodosCarritos;

    public MenuPrincipalView(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;


        setTitle("Datos del Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        //setResizable(false);
        setLocationRelativeTo(null);

        //pack();

        jDesktopPane = new JDesktopPane();
        setSize(500, 500);
        menuBar = new JMenuBar();
        menuProducto = new JMenu("Producto");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Actualizar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");
        menuProducto2=new JMenu("Carrito");
        menuItemCrearCarrito=new JMenuItem("Crear Carrito");
        menuItemBuscarCarrito = new JMenuItem("Buscar Carrito");
        menuProducto2.add(menuItemBuscarCarrito);
        menuItemListarCarritosUsuario = new JMenuItem("Listar Mis Carritos");
        menuProducto2.add(menuItemListarCarritosUsuario);
        menuItemActualizarCarrito = new JMenuItem("Modificar Mis Carritos");
        menuProducto2.add(menuItemActualizarCarrito);
        menuItemListarTodosCarritos = new JMenuItem("Listar Todos los Carritos");
        menuProducto2.add(menuItemListarTodosCarritos);

        menuBar.add(menuProducto);
        menuBar.add(menuProducto2);
        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);
        menuProducto2.add(menuItemCrearCarrito);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras En Línea");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        configurarMenusSegunRol();
        setVisible(true);

    }

    public JMenuItem getMenuItemListarTodosCarritos() {
        return menuItemListarTodosCarritos;
    }

    public void setMenuItemListarTodosCarritos(JMenuItem menuItemListarTodosCarritos) {
        this.menuItemListarTodosCarritos = menuItemListarTodosCarritos;
    }

    public JMenuItem getMenuItemListarCarritosUsuario() {
        return menuItemListarCarritosUsuario;
    }

    public JMenuItem getMenuItemActualizarCarrito() {
        return menuItemActualizarCarrito;
    }

    public void setMenuItemActualizarCarrito(JMenuItem menuItemActualizarCarrito) {
        this.menuItemActualizarCarrito = menuItemActualizarCarrito;
    }

    public void setMenuItemListarCarritosUsuario(JMenuItem menuItemListarCarritosUsuario) {
        this.menuItemListarCarritosUsuario = menuItemListarCarritosUsuario;
    }

    public JMenuItem getMenuItemBuscarCarrito() {
        return menuItemBuscarCarrito;
    }

    public void setMenuItemBuscarCarrito(JMenuItem menuItemBuscarCarrito) {
        this.menuItemBuscarCarrito = menuItemBuscarCarrito;
    }

    private void configurarMenusSegunRol() {
        if (usuarioLogueado.getRol() == Rol.USUARIO) {


            // O desactivar solo ciertos ítems si quieres otra lógica
            menuItemEliminarProducto.setVisible(false);
            menuItemActualizarProducto.setVisible(false);
            menuItemCrearProducto.setVisible(false);

        }
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public void setMenuItemCrearCarrito(JMenuItem menuItemCrearCarrito) {
        this.menuItemCrearCarrito = menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public void setMenuItemCrearProducto(JMenuItem menuItemCrearProducto) {
        this.menuItemCrearProducto = menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public void setMenuItemEliminarProducto(JMenuItem menuItemEliminarProducto) {
        this.menuItemEliminarProducto = menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public void setMenuItemActualizarProducto(JMenuItem menuItemActualizarProducto) {
        this.menuItemActualizarProducto = menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public void setMenuItemBuscarProducto(JMenuItem menuItemBuscarProducto) {
        this.menuItemBuscarProducto = menuItemBuscarProducto;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(JDesktopPane jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }
}