package proyecto1implementacion;

import java.io.Serializable;

/**
 *
 * @author Johan Bautista
 */
public class Alimento implements Serializable {

    private tipoAlimento Tipo;
    private int Identificador;
    private static int AlimentosCreados = 0;
    private String Nombre;
    private float Tarifa;
    private boolean LugarDisponibilidad;
    private String Horario;

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int identificador) {
        Identificador = identificador;
    }

    public enum tipoAlimento {
        BEBIDA, PLATO
    }

    public tipoAlimento getTipo() {
        return Tipo;
    }

    public void setTipo(tipoAlimento Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getTarifa() {
        return Tarifa;
    }

    public void setTarifa(float Tarifa) {
        this.Tarifa = Tarifa;
    }

    public boolean isLugarDisponibilidad() {
        return LugarDisponibilidad;
    }

    public void setLugarDisponibilidad(boolean LugarDisponibilidad) {
        this.LugarDisponibilidad = LugarDisponibilidad;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public Alimento(tipoAlimento Tipo, String Nombre, float Tarifa, boolean LugarDisponibilidad, String Horario) {
        this.Tipo = Tipo;
        this.Nombre = Nombre;
        this.Tarifa = Tarifa;
        this.LugarDisponibilidad = LugarDisponibilidad;
        this.Horario = Horario;
        this.Identificador = Alimento.AlimentosCreados;
        Alimento.AlimentosCreados += 1;
    }

    



}
