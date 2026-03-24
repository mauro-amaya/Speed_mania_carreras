import java.awt.*;

public class Auto {
    int x,y;
    int ancho = 60;
    int alto = 30;
    int velocidad = 10;
    Color color;

    public Auto(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void moverDerecha(){
        x += velocidad;
    }
    public void moverIzquiera(){
        x -= velocidad;
    }

    public void moverArriba(){
        y += velocidad;
    }

    public void moverAbajo(){
        y -= velocidad;
    }

    public void dibujar(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, ancho, alto);
    }
}
