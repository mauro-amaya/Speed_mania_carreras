import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Panel_Juego extends JPanel
implements ActionListener, KeyListener{

     Auto jugador;
     Auto enemigo;

    Timer timer;

    int nivel = 1;
    int meta = 700;
    int vidas = 3;
    int puntaje = 0;
    int record = 0;

    public Panel_Juego() {

        setFocusable(true);
        addKeyListener(this);

        jugador = new Auto(50, 150, Color.GREEN);
        enemigo = new Auto(50, 250, Color.RED);

        cargarRecord();

        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(meta, 0, 10, getHeight());

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Nivel: " + nivel, 20, 20);
        g.drawString("Vidas: " + vidas, 20, 40);
        g.drawString("Puntaje: " + puntaje, 20, 60);
        g.drawString("Record: " + record, 20, 80);

        jugador.dibujar(g);
        enemigo.dibujar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        enemigo.x += 2 + nivel;

        if (jugador.getBounds().intersects(enemigo.getBounds())) {
            vidas--;
            jugador.x = 100;
            enemigo.x = 80;

            if (vidas <= 0) {
                timer.stop();
                guardarRecord();
                JOptionPane.showMessageDialog(this, " GAME OVER, Puntaje final: " + puntaje);
                System.exit(0);
            }
        }

        if (jugador.x >= meta) {
            nivel++;
            puntaje += 100;
            jugador.x = 50;
            enemigo.x = 50;
            JOptionPane.showMessageDialog(this, " ¡Subes al nivel " + nivel + "!");
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) jugador.moverDerecha();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) jugador.moverIzquiera();
        if (e.getKeyCode() == KeyEvent.VK_UP) jugador.moverArriba();
        if (e.getKeyCode() == KeyEvent.VK_DOWN) jugador.moverAbajo();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    // Guardar record
    public void guardarRecord() {
        if (puntaje > record) {
            try {
                FileWriter fw = new FileWriter("record.txt");
                fw.write(String.valueOf(puntaje));
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void cargarRecord() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("record.txt"));
            record = Integer.parseInt(br.readLine());
            br.close();
        } catch (Exception e) {
            record = 0;
        }
    }
}