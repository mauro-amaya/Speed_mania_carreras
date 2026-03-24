import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pantalla_Inicio extends JFrame {

    JLabel texto;
    JButton boton;
    Timer animacionTimer;

    public Pantalla_Inicio() {

        setTitle("Speed Manía");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Cargar imagen
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/portada.jpg"));
        JLabel fondo = new JLabel(imagen);
        fondo.setLayout(null);
        add(fondo);

        // Título del juego
        JLabel titulo = new JLabel("SPEED MANÍA");
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setForeground(Color.ORANGE);
        titulo.setBounds(150, 50, 500, 80);
        fondo.add(titulo);

        // Texto animado
        texto = new JLabel("PRESIONA INICIAR");
        texto.setFont(new Font("Arial", Font.BOLD, 30));
        texto.setForeground(Color.YELLOW);
        texto.setBounds(250, 250, 400, 50);
        fondo.add(texto);

        // Botón
        boton = new JButton("INICIAR JUEGO");
        boton.setBounds(300, 300, 200, 40);
        fondo.add(boton);

        // Animación parpadeo
        animacionTimer = new Timer(500, new ActionListener() {
            boolean visible = true;
            public void actionPerformed(ActionEvent e) {
                texto.setVisible(visible);
                visible = !visible;
            }
        });
        animacionTimer.start();

        // Acción del botón
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarCuentaRegresiva();
            }
        });

        setVisible(true);
    }

    // Cuenta regresiva con efecto GOOO!!!
    public void iniciarCuentaRegresiva() {

        boton.setEnabled(false);
        texto.setVisible(true);

        Timer cuenta = new Timer(1000, null);

        cuenta.addActionListener(new ActionListener() {

            int numero = 3;

            public void actionPerformed(ActionEvent e) {

                if (numero > 0) {
                    texto.setFont(new Font("Arial", Font.BOLD, 40));
                    texto.setForeground(Color.RED);
                    texto.setText("" + numero);
                    numero--;

                } else if (numero == 0) {

                    numero--; // Para pasar al siguiente paso

                    // Timer para efecto de zoom y parpadeo de GOOO!!!
                    Timer efectoGO = new Timer(100, null);
                    final int[] size = {40};
                    final boolean[] crecer = {true};
                    final int[] count = {0};

                    efectoGO.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            texto.setFont(new Font("Arial", Font.BOLD, size[0]));
                            texto.setText("GOOO!!! 🚗💨");

                            // Parpadeo
                            if (texto.getForeground() == Color.GREEN) {
                                texto.setForeground(Color.YELLOW);
                            } else {
                                texto.setForeground(Color.GREEN);
                            }

                            // Zoom
                            if (crecer[0]) size[0] += 4;
                            else size[0] -= 4;

                            if (size[0] >= 80) crecer[0] = false;
                            if (size[0] <= 40) crecer[0] = true;

                            count[0]++;
                            if (count[0] >= 20) { // dura ~2 segundos
                                efectoGO.stop();
                                dispose();
                                new Juego_Carrera();
                            }
                        }
                    });

                    efectoGO.start();

                }
            }
        });

        cuenta.start();
    }
}