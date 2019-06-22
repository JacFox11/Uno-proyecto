
package uno;

import java.util.ArrayList;


public class Players {
    String nombre;
    ArrayList <Cards> cartas = new ArrayList();

    public Players(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Cards> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<Cards> cartas) {
        this.cartas = cartas;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
