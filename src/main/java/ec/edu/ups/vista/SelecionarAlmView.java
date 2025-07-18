package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SelecionarAlmView extends JFrame {
    private JLabel lblInfo;
    private File carpetaSeleccionada;
    private JButton btnSeleccionarCarpeta;
    private JButton btnMemoria;
    private JPanel panelPrincipal;

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
