package ec.edu.ups.servicio;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
/**
 * La clase FondoEscritorio extiende JDesktopPane y se encarga de dibujar un fondo visual personalizado,
 * incluyendo un cielo estrellado, una luna, montañas y una franja de césped.
 * Esta clase puede ser utilizada como fondo principal en una aplicación Swing con ventanas internas.
 *
 * @author [Sebastian Ceron]
 * @version 1.0
 * @date 18-07-2025
 */
public class FondoEscritorio extends JDesktopPane {

    private final int cantidadEstrellas = 100;
    private final int[] estrellasX = new int[cantidadEstrellas];
    private final int[] estrellasY = new int[cantidadEstrellas];
    /**
     * Constructor que inicializa la posición aleatoria de las estrellas para el fondo.
     */
    public FondoEscritorio() {
        Random rand = new Random();
        for (int i = 0; i < cantidadEstrellas; i++) {
            estrellasX[i] = rand.nextInt(1920); // ancho estimado
            estrellasY[i] = rand.nextInt(1080); // alto estimado
        }
    }
    /**
     * Sobrescribe el método paintComponent para dibujar el fondo personalizado.
     * Se incluyen:
     * <ul>
     *     <li>Un fondo azul oscuro que representa el cielo nocturno</li>
     *     <li>Estrellas blancas distribuidas aleatoriamente</li>
     *     <li>Una luna blanca como óvalo</li>
     *     <li>Montañas grises dibujadas con un polígono</li>
     *     <li>Césped verde al pie de la pantalla</li>
     * </ul>
     *
     * @param g El contexto gráfico usado para el dibujo
     */
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