package proyecto1implementacion;

import java.io.Serializable;
import java.util.ArrayList;

public class GrupoHuespedes implements Serializable{
    private Huesped huespedPrincipal;
    private ArrayList<Huesped> acompaniantes;
    private ArrayList<Habitacion> habitacionesAsignadas;
    private ArrayList<Consumo> consumosAsociados;

    public GrupoHuespedes(Huesped huespedPrincipal) {
        this.huespedPrincipal = huespedPrincipal;
        this.acompaniantes = new ArrayList<Huesped>();
        this.habitacionesAsignadas = new ArrayList<Habitacion>();
        this.consumosAsociados = new ArrayList<Consumo>();
    }

    public Huesped getHuespedPrincipal() {
        return huespedPrincipal;
    }

    public void setHuespedPrincipal(Huesped huespedPrincipal) {
        this.huespedPrincipal = huespedPrincipal;
    }

    public ArrayList<Huesped> getAcompaniantes() {
        return acompaniantes;
    }

    public void agregarAcompaniante(Huesped acompaniante) {
        this.acompaniantes.add(acompaniante);
    }

    public void eliminarAcompaniante(Huesped acompaniante) {
        this.acompaniantes.remove(acompaniante);
    }

    public ArrayList<Habitacion> getHabitacionesAsignadas() {
        return habitacionesAsignadas;
    }

    public void asignarHabitacion(Habitacion habitacion) {
        this.habitacionesAsignadas.add(habitacion);
    }

    public void desasignarHabitacion(Habitacion habitacion) {
        this.habitacionesAsignadas.remove(habitacion);
    }

    public ArrayList<Consumo> getConsumosAsociados() {
        return consumosAsociados;
    }

    public void agregarConsumo(Consumo consumo) {
        this.consumosAsociados.add(consumo);
    }

    public void eliminarConsumo(Consumo consumo) {
        this.consumosAsociados.remove(consumo);
    }
}