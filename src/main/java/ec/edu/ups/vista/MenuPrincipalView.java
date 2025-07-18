package ec.edu.ups.vista;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.servicio.FondoEscritorio;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuProducto2;
    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;
    private FondoEscritorio jDesktopPane;
    private JMenuItem menuItemCrearCarrito;
    private Usuario usuarioLogueado;
    private JMenuItem menuItemBuscarCarrito;
    private JMenuItem menuItemListarCarritosUsuario;
    private JMenuItem menuItemActualizarCarrito;
    private JMenuItem menuItemListarTodosCarritos;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemEliminarCarritoItems;
    private JMenu menuUsuario;
    private JMenu menuIdioma;
    private JMenuItem menuItemEspanol;
    private JMenuItem menuItemIngles;
    private JMenuItem menuItemFrances;
    private static String idiomaSeleccionado = "ES";
    private JMenuItem menuItemCerrarSesion;
    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemEliminarUsuario;
    private JMenuItem menuItemListarUsuarios;

    public MenuPrincipalView(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        //setResizable(false);
        setLocationRelativeTo(null);

        //pack();

        jDesktopPane = new FondoEscritorio();
        setSize(500, 500);
        menuBar = new JMenuBar();
        menuProducto = new JMenu(Main.mensajes.get("menu.producto"));

        menuItemCrearProducto = new JMenuItem(Main.mensajes.get("menu.producto.crear"));
        menuItemEliminarProducto = new JMenuItem(Main.mensajes.get("menu.producto.eliminar"));
        menuItemActualizarProducto = new JMenuItem(Main.mensajes.get("menu.producto.actualizar"));
        menuItemBuscarProducto = new JMenuItem(Main.mensajes.get("menu.producto.buscar"));
        menuProducto2=new JMenu(Main.mensajes.get("menu.carrito"));
        menuItemCrearCarrito=new JMenuItem(Main.mensajes.get("menu.carrito.crear"));
        menuProducto2.add(menuItemCrearCarrito);
        menuItemBuscarCarrito = new JMenuItem(Main.mensajes.get("menu.carrito.buscar"));
        menuProducto2.add(menuItemBuscarCarrito);
        menuItemListarCarritosUsuario = new JMenuItem(Main.mensajes.get("menu.carrito.listarUsuario"));
        menuProducto2.add(menuItemListarCarritosUsuario);
        menuItemActualizarCarrito = new JMenuItem(Main.mensajes.get("menu.carrito.actualizar"));
        menuProducto2.add(menuItemActualizarCarrito);
        menuItemListarTodosCarritos = new JMenuItem(Main.mensajes.get("menu.carrito.listarTodos"));
        menuProducto2.add(menuItemListarTodosCarritos);
        menuItemEliminarCarrito = new JMenuItem(Main.mensajes.get("menu.carrito.eliminar"));
        menuProducto2.add(menuItemEliminarCarrito);
        menuItemEliminarCarritoItems = new JMenuItem(Main.mensajes.get("menu.carrito.eliminarProducto"));
        menuProducto2.add(menuItemEliminarCarritoItems);
        menuUsuario = new JMenu(Main.mensajes.get("menu.usuario"));
        menuItemCrearUsuario = new JMenuItem(Main.mensajes.get("menu.usuario.crear"));
        menuUsuario.add(menuItemCrearUsuario);
            menuItemEliminarUsuario = new JMenuItem(Main.mensajes.get("menu.usuario.eliminar"));
        menuUsuario.add(menuItemEliminarUsuario);
        menuItemListarUsuarios = new JMenuItem(Main.mensajes.get("menu.usuarios.listarUsuarios"));
        menuUsuario.add(menuItemListarUsuarios);
        menuItemCerrarSesion = new JMenuItem(Main.mensajes.get("menu.salir.cerrarSesion"));
        menuUsuario.add(menuItemCerrarSesion);
        menuIdioma = new JMenu(Main.mensajes.get("menu.idioma"));
        menuItemEspanol = new JMenuItem(Main.mensajes.get("menu.idioma.espanol"));
        menuItemIngles = new JMenuItem(Main.mensajes.get("menu.idioma.ingles"));
        menuItemFrances = new JMenuItem(Main.mensajes.get("menu.idioma.frances"));

        menuIdioma.add(menuItemEspanol);
        menuIdioma.add(menuItemIngles);
        menuIdioma.add(menuItemFrances);


        menuBar.add(menuProducto);
        menuBar.add(menuProducto2);
        menuBar.add(menuUsuario);
        menuBar.add(menuIdioma);
        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);


        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Main.mensajes.get("menu.titulo"));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        configurarMenusSegunRol();
        setVisible(true);

    }

    public JMenuItem getMenuItemListarUsuarios() {
        return menuItemListarUsuarios;
    }



    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }



    public JMenuItem getMenuItemEliminarCarritoItems() {
        return menuItemEliminarCarritoItems;
    }

    public void setMenuItemEliminarCarritoItems(JMenuItem menuItemEliminarCarritoItems) {
        this.menuItemEliminarCarritoItems = menuItemEliminarCarritoItems;
    }





    public JMenu getMenuUsuario() {
        return menuUsuario;
    }



    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public void setMenuItemCerrarSesion(JMenuItem menuItemCerrarSesion) {
        this.menuItemCerrarSesion = menuItemCerrarSesion;
    }

    public void configurarTextosMenus(MensajeInternacionalizacionHandler mensajes) {
        menuProducto.setText(mensajes.get("menu.producto"));
        menuProducto2.setText(mensajes.get("menu.carrito"));
        menuIdioma.setText(mensajes.get("menu.idioma"));
        menuUsuario.setText(mensajes.get("menu.usuario"));

        menuItemCrearProducto.setText(mensajes.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajes.get("menu.producto.eliminar"));
        menuItemActualizarProducto.setText(mensajes.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajes.get("menu.producto.buscar"));

        menuItemCrearCarrito.setText(mensajes.get("menu.carrito.crear"));
        menuItemEliminarCarrito.setText(mensajes.get("menu.carrito.eliminar"));
        menuItemEliminarCarritoItems.setText(mensajes.get("menu.carrito.eliminarProducto"));
        menuItemActualizarCarrito.setText(mensajes.get("menu.carrito.actualizar"));
        menuItemBuscarCarrito.setText(mensajes.get("menu.carrito.buscar"));
        menuItemListarCarritosUsuario.setText(mensajes.get("menu.carrito.listarUsuario"));
        menuItemListarTodosCarritos.setText(mensajes.get("menu.carrito.listarTodos"));

        menuItemCrearUsuario.setText(mensajes.get("menu.usuario.crear"));
        menuItemEliminarUsuario.setText(mensajes.get("menu.usuario.eliminar"));
        menuItemListarUsuarios.setText(mensajes.get("menu.usuarios.listarUsuarios"));
        menuItemCerrarSesion.setText(mensajes.get("menu.salir.cerrarSesion"));

        menuItemEspanol.setText(mensajes.get("menu.idioma.espanol"));
        menuItemIngles.setText(mensajes.get("menu.idioma.ingles"));
        menuItemFrances.setText(mensajes.get("menu.idioma.frances"));
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }



    public JMenuItem getMenuItemListarTodosCarritos() {
        return menuItemListarTodosCarritos;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
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


            menuItemEliminarProducto.setVisible(false);
            menuItemActualizarProducto.setVisible(false);
            menuItemCrearProducto.setVisible(false);
            menuItemEliminarCarrito.setVisible(false);
            menuItemActualizarCarrito.setVisible(false);
            menuItemEliminarCarrito.setVisible(false);
            menuItemListarTodosCarritos.setVisible(false);
            menuItemEliminarCarritoItems.setVisible(false);
            menuItemCrearUsuario.setVisible(false);


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

    public JMenuItem getMenuItemEspanol() {
        return menuItemEspanol;
    }

    public void setMenuItemEspanol(JMenuItem menuItemEspanol) {
        this.menuItemEspanol = menuItemEspanol;
    }

    public static void setIdioma(String idioma) {
        idiomaSeleccionado = idioma;
    }

    public static String getMoneda() {
        switch (idiomaSeleccionado) {
            case "FR": return "€";
            case "EN": return "$";
            case "ES": return "$";
            default: return "$";
        }
    }



    public JMenuItem getMenuItemIngles() {
        return menuItemIngles;
    }

    public void setMenuItemIngles(JMenuItem menuItemIngles) {
        this.menuItemIngles = menuItemIngles;
    }

    public JMenuItem getMenuItemFrances() {
        return menuItemFrances;
    }

    public void setMenuItemFrances(JMenuItem menuItemFrances) {
        this.menuItemFrances = menuItemFrances;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

    public void setjDesktopPane(FondoEscritorio jDesktopPane) {
        this.jDesktopPane = jDesktopPane;
    }
}