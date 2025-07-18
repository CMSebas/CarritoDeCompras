package ec.edu.ups.controlador;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.SelecionarAlmView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartupController {

    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;
    private CarritoDAO carritoDAO;
    private PreguntaDAO preguntaDAO;
    private RespuestaDAO respuestaDAO;
    private File carpetaArchivos;
    private String tipoAlmacenamiento;

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