package ec.edu.ups.servicio;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FondoEscritorio extends JDesktopPane {

    private final int cantidadEstrellas = 100;
    private final int[] estrellasX = new int[cantidadEstrellas];
    private final int[] estrellasY = new int[cantidadEstrellas];

    public FondoEscritorio() {
        Random rand = new Random();
        for (int i = 0; i < cantidadEstrellas; i++) {
            estrellasX[i] = rand.nextInt(1920); // ancho estimado
            estrellasY[i] = rand.nextInt(1080); // alto estimado
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();


        g.setColor(new Color(10, 10, 40)); // azul muy oscuro
        g.fillRect(0, 0, getWidth(), getHeight());


        g.setColor(Color.WHITE);
        for (int i = 0; i < cantidadEstrellas; i++) {
            g.fillOval(estrellasX[i], estrellasY[i], 2, 2); // estrella como punto blanco
        }
        g.setColor(Color.WHITE);
        g.fillOval(50, 50, 300, 300);

        g.setColor(new Color(90, 90, 90));
        int[] xMont = {
                0, w / 5, w / 4, w / 2, (int)(w * 0.75), w
        };
        int[] yMont = {
                h, (int)(h * 0.65), (int)(h * 0.5), (int)(h * 0.6), (int)(h * 0.4), h
        };
        g.fillPolygon(xMont, yMont, xMont.length);


        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, h - 60, w, 60);


        g.setColor(new Color(255, 255, 180));
        g.setFont(new Font("Serif", Font.BOLD, 20));

    }
}