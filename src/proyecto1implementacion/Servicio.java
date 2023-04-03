package proyecto1implementacion;

import java.io.Serializable;

/**
 *
 * @author Johan Bautista
 */
public class Servicio implements Serializable {

    private String nombre;

    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    public enum areaAsociada {
        SPA, RESTAURANTE, GUIATURISTICO
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
