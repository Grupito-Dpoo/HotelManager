package proyecto1implementacion;

/**
 *
 * @author Johan Bautista
 */
public class Cama {

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
