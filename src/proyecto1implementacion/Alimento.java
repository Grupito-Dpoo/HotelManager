package proyecto1implementacion;

/**
 *
 * @author Johan Bautista
 */
public class Alimento {

    private tipoAlimento Tipo;
    private String LugarDisponibilidad;
    private String Horario;
    private String Nombre;
    private float Tarifa;

    public enum tipoAlimento {
        BEBIDA, PLATO
    }

    public tipoAlimento getTipo() {
        return Tipo;
    }

    public void setTipo(tipoAlimento Tipo) {
        this.Tipo = Tipo;
    }

    public String getLugarDisponibilidad() {
        return LugarDisponibilidad;
    }

    public void setLugarDisponibilidad(String LugarDisponibilidad) {
        this.LugarDisponibilidad = LugarDisponibilidad;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
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
    
}
