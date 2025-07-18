package ec.edu.ups.controlador;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.SelecionarAlmView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * Controlador responsable del proceso de inicialización del sistema.
 * Permite seleccionar el tipo de almacenamiento (memoria o archivos) y
 * configura los DAOs de acuerdo con la elección del usuario.
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 *
 */
public class StartupController {

    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;
    private CarritoDAO carritoDAO;
    private PreguntaDAO preguntaDAO;
    private RespuestaDAO respuestaDAO;
    private File carpetaArchivos;
    private String tipoAlmacenamiento;

    /**
     * Inicia el proceso de selección del tipo de almacenamiento.
     * Muestra una ventana para que el usuario elija entre "Memoria" o "Texto".
     * Luego, configura los DAOs en función de la elección.
     */

    public void inicializar() {
        SelecionarAlmView selector = new SelecionarAlmView();

        selector.getBtnSeleccionarCarpeta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File carpeta = selector.mostrarSelectorCarpeta();
                if (carpeta != null) {
                    tipoAlmacenamiento = "Texto";
                    carpetaArchivos = carpeta;
                    selector.dispose();
                }
            }
        });

        selector.getBtnMemoria().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipoAlmacenamiento = "Memoria";
                selector.dispose();
            }
        });

        selector.setVisible(true);


        while (tipoAlmacenamiento == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }

        configurarDAOs();
    }
    /**
     * Configura los DAOs de acuerdo al tipo de almacenamiento seleccionado.
     * Si se elige "Texto", se utilizan DAOs que trabajan con archivos.
     * Si se elige "Memoria", se utilizan DAOs que almacenan los datos en listas en tiempo de ejecución.
     */
    private void configurarDAOs() {
        if ("Texto".equals(tipoAlmacenamiento)) {
            String base = carpetaArchivos.getAbsolutePath() + File.separator;
            usuarioDAO = new UsuarioDAOTexto(base + "usuarios.txt");
            productoDAO = new ProductoDAOTexto(base + "productos.txt");
            carritoDAO = new CarritoDAOBin(base + "carritos.dat");
            ((CarritoDAOBin) carritoDAO).formatearArchivo();
            respuestaDAO = new RespuestaDAOBin(base + "respuestas.dat");
            preguntaDAO = new PreguntaDAOBin(base + "preguntas.dat");
        } else {
            usuarioDAO = new UsuarioDAOMemoria();
            productoDAO = new ProductoDAOMemoria();
            carritoDAO = new CarritoDAOMemoria();
            respuestaDAO = new RespuestaDAOMemoria();
            preguntaDAO = new PreguntaDAOMemoria();
        }
    }
    /**
     * @return DAO configurado para gestionar usuarios.
     */
    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public PreguntaDAO getPreguntaDAO() {
        return preguntaDAO;
    }

    public RespuestaDAO getRespuestaDAO() {
        return respuestaDAO;
    }

    public String getTipoAlmacenamiento() {
        return tipoAlmacenamiento;
    }
}