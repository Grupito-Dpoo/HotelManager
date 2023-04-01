package proyecto1implementacion;

import java.io.Serializable;

/**
 *
 * @author Johan Bautista
 */
public class Cama implements Serializable{

    private int tamanio;
    private int capacidad;

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Cama(int tamanio, int capacidad) {
        this.tamanio = tamanio;
        this.capacidad = capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

}
