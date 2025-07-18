package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
 * Vista principal para seleccionar el tipo de almacenamiento (memoria o carpeta personalizada).
 * Forma parte del flujo inicial de configuración del sistema.
 * Permite cambiar de idioma dinámicamente e interactuar con el usuario para seleccionar una carpeta.
 * Utiliza el patrón MVC como componente de vista.
 *
 * @author Sebastián Cerón
 * @version 1.0
 * @date 18-07-2025
 */
public class SelecionarAlmView extends JFrame {
    private JLabel lblInfo;
    private File carpetaSeleccionada;
    private JButton btnSeleccionarCarpeta;
    private JButton btnMemoria;
    private JPanel panelPrincipal;
    /**
     * Constructor que inicializa los componentes gráficos de la ventana
     * y configura propiedades básicas como tamaño, título y posición.
     */
    public SelecionarAlmView() {
        setTitle("Almacenamiento");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
    }

    public void setBtnSeleccionarCarpeta(JButton btnSeleccionarCarpeta) {
        this.btnSeleccionarCarpeta = btnSeleccionarCarpeta;
    }

    public void setBtnMemoria(JButton btnMemoria) {
        this.btnMemoria = btnMemoria;
    }

    public JLabel getLblInfo() {
        return lblInfo;
    }

    public void setLblInfo(JLabel lblInfo) {
        this.lblInfo = lblInfo;
    }
    /**
     * Cambia los textos visibles de los componentes según el idioma actual.
     *
     * @param mensajes Manejador de mensajes internacionalizados.
     */
    public void cambiarIdiomaTexto(MensajeInternacionalizacionHandler mensajes) {
        setTitle(mensajes.get("seleccionAlm.titulo"));
        btnMemoria.setText(mensajes.get("seleccionAlm.memoria"));
        btnSeleccionarCarpeta.setText(mensajes.get("seleccionAlm.seleccionarCarpeta"));
        lblInfo.setText(mensajes.get("seleccionAlm.titulo"));



    }

    public JButton getBtnSeleccionarCarpeta() {
        return btnSeleccionarCarpeta;
    }

    public JButton getBtnMemoria() {
        return btnMemoria;
    }

    public File getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }

    /**
     * Abre un selector de carpetas (JFileChooser) para que el usuario seleccione
     * un directorio donde se almacenarán los datos.
     *
     * @return Archivo que representa la carpeta seleccionada o null si se canceló.
     */
    public File mostrarSelectorCarpeta() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int opcion = chooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }

        return null;
    }
}
