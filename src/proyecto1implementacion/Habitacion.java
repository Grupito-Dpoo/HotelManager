package proyecto1implementacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Habitacion implements Serializable {

    private int Identificador;
    private int Capacidad;
    private tipoHabitacion TipoHabitacion;
    private int Balcon;
    private String Vista;
    private int CocinaIntegrada;
    private ArrayList<Cama> Camas;
    private Huesped HuespedActual;
    private String Ubicacion;
    private static Integer habitacionesCreadas = 0;

    public Habitacion(tipoHabitacion TipoHabitacion, int Balcon,
            String Vista, int CocinaIntegrada, ArrayList<Cama> Camas,
            String Ubicacion) {
        this.TipoHabitacion = TipoHabitacion;
        this.Balcon = Balcon;
        this.Vista = Vista;
        this.CocinaIntegrada = CocinaIntegrada;
        this.Camas = Camas;
        this.Ubicacion = Ubicacion;
        this.Identificador = habitacionesCreadas;
        Habitacion.habitacionesCreadas += 1;
        this.CalcularCapacidad();
    }

    public int getIdentificador() {
        return Identificador;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void CalcularCapacidad() {
        Integer sumaCapacidades = 0;
        if (this.Camas.isEmpty()) {
            this.Capacidad = sumaCapacidades;
        } else {
            for (Cama camita : this.Camas) {
                sumaCapacidades += camita.getCapacidad();
            }
            this.Capacidad = sumaCapacidades;
        }
    }

    public tipoHabitacion getTipoHabitacion() {
        return TipoHabitacion;
    }

    public void setTipoHabitacion(tipoHabitacion TipoHabitacion) {
        this.TipoHabitacion = TipoHabitacion;
    }

    public String getVista() {
        return Vista;
    }

    public void setVista(String Vista) {
        this.Vista = Vista;
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

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }

    public int getBalcon() {
        return Balcon;
    }

    public void setBalcon(int Balcon) {
        this.Balcon = Balcon;
    }

    public int getCocinaIntegrada() {
        return CocinaIntegrada;
    }

    public void setCocinaIntegrada(int CocinaIntegrada) {
        this.CocinaIntegrada = CocinaIntegrada;
    }

}
