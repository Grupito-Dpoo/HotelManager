package proyecto1implementacion;

import java.io.Serializable;

/**
 *
 * @author Johan Bautista
 */
public class Cama implements Serializable {

    private int Identificador;
    private String tamanio;
    private int capacidad;
    private static int ContadorCamas;

    public int getCapacidad() {
        return capacidad;
    }

    public Cama(String tamanio, int capacidad) {
        this.tamanio = tamanio;
        this.capacidad = capacidad;
        this.Identificador = ContadorCamas;
        ContadorCamas += 1;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

}
