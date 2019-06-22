
package uno;

import java.awt.Color;


public class Cards {
    private Color color;
    private int numero;

    public Cards(Color color, int numero) {
        this.color = color;
        this.numero = numero;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cards{" + "color=" + color + ", numero=" + numero + '}';
    }

    
    
    
}
