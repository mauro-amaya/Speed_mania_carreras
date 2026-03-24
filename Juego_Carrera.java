import javax.swing.*;

public class Juego_Carrera {

    public Juego_Carrera() {

        JFrame ventana = new JFrame("Speed Manía");
        ventana.setSize(800, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        Panel_Juego panel = new Panel_Juego();
        ventana.add(panel);

        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new Pantalla_Inicio();
    }
}