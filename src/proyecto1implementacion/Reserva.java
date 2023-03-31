package proyecto1implementacion;

import proyecto1implementacion.Habitacion;
import proyecto1implementacion.Huesped;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reserva {

    private Integer identificador;
    private ArrayList<Habitacion> habitaciones;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Huesped informacionHuesped;
    private Integer cantidadPersonas;

    // Constructor
    public Reserva(Integer identificador, ArrayList<Habitacion> habitaciones, LocalDate fechaInicial, LocalDate fechaFinal,
                   Huesped informacionHuesped, Integer cantidadPersonas) {
        this.identificador = identificador;
        this.habitaciones = habitaciones;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.informacionHuesped = informacionHuesped;
        this.cantidadPersonas = cantidadPersonas;
    }


    // Verificar fecha de cancelación
    public String verificarFechaCancelar() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fechaInicial.minusDays(2))) {
            return "La reserva puede ser cancelada sin cargo.";
        } else if (fechaActual.isBefore(fechaInicial)) {
            return "La reserva puede ser cancelada con un cargo del 50%.";
        } else {
            return "La reserva no puede ser cancelada.";
        }
    }

    // Getters y setters

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Huesped getInformacionHuesped() {
        return informacionHuesped;
    }

    public void setInformacionHuesped(Huesped informacionHuesped) {
        this.informacionHuesped = informacionHuesped;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}