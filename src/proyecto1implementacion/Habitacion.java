package proyecto1implementacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Johan Bautista
 */
public class Habitacion implements Serializable{

    private int Identificador;
    private int Capacidad;
    private tipoHabitacion TipoHabitacion;
    private boolean Balcon;
    private String Vista;
    private boolean CocinaIntegrada;
    private ArrayList<Cama> Camas;
    private Huesped HuespedActual;
    private String Ubicacion;
    private static Integer habitacionesCreadas;

    
    public Habitacion(tipoHabitacion TipoHabitacion, boolean Balcon,
            String Vista, boolean CocinaIntegrada, ArrayList<Cama> Camas,
            String Ubicacion) {
        this.TipoHabitacion = TipoHabitacion;
        this.Balcon = Balcon;
        this.Vista = Vista;
        this.CocinaIntegrada = CocinaIntegrada;
        this.Camas = Camas;
        this.Ubicacion = Ubicacion;
        this.Identificador = habitacionesCreadas;
        Habitacion.habitacionesCreadas += 1;
        this.setCapacidad();
    }


    public int getIdentificador() {
        return Identificador;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad() {
        Integer sumaCapacidades = 0;
        for (Cama camita : this.Camas) {
            sumaCapacidades += camita.getCapacidad();
        }
        this.Capacidad = sumaCapacidades;
    }

    public tipoHabitacion getTipoHabitacion() {
        return TipoHabitacion;
    }

    public void setTipoHabitacion(tipoHabitacion TipoHabitacion) {
        this.TipoHabitacion = TipoHabitacion;
    }

    public boolean isBalcon() {
        return Balcon;
    }

    public void setBalcon(boolean Balcon) {
        this.Balcon = Balcon;
    }

    public String getVista() {
        return Vista;
    }

    public void setVista(String Vista) {
        this.Vista = Vista;
    }

    public boolean isCocinaIntegrada() {
        return CocinaIntegrada;
    }

    public void setCocinaIntegrada(boolean CocinaIntegrada) {
        this.CocinaIntegrada = CocinaIntegrada;
    }

    public ArrayList<Cama> getCamas() {
        return Camas;
    }

    public void setCamas(ArrayList<Cama> Camas) {
        this.Camas = Camas;
    }

    public Huesped getHuespedActual() {
        return HuespedActual;
    }

    public void setHuespedActual(Huesped HuespedActual) {
        this.HuespedActual = HuespedActual;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public enum tipoHabitacion {
        ESTANDAR, SUITE, SUITEDOBLE
    }

}
