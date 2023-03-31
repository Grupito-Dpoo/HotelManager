package proyecto1implementacion;

import proyecto1implementacion.Servicio.areaAsociada;

/**
 *
 * @author Johan Bautista
 */
public class Consumo {
    private static Integer ConsumosExpedidos;
    private Integer Identificador;
    private String Fecha;
    private areaAsociada AreaConsumo;
    private Huesped Huesped;
    private Habitacion Habitacion;
    private float valor;
    private float valorTotal;
    private boolean Pagado;

    public Consumo(String Fecha, areaAsociada AreaConsumo, Huesped Huesped, Habitacion Habitacion, float valor, float valorTotal, boolean Pagado) {
        this.Identificador = ConsumosExpedidos;
        ConsumosExpedidos += 1;
        this.Fecha = Fecha;
        this.AreaConsumo = AreaConsumo;
        this.Huesped = Huesped;
        this.Habitacion = Habitacion;
        this.valor = valor;
        this.valorTotal = valorTotal;
        this.Pagado = Pagado;
    }

    public void setIdentificador(int Identificador){
        this.Identificador = Identificador;
    }

    public Integer getIdentificador(){
        return Identificador;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public areaAsociada getAreaConsumo() {
        return AreaConsumo;
    }

    public void setAreaConsumo(areaAsociada AreaConsumo) {
        this.AreaConsumo = AreaConsumo;
    }

    public Huesped getHuesped() {
        return Huesped;
    }

    public void setHuesped(Huesped Huesped) {
        this.Huesped = Huesped;
    }

    public Habitacion getHabitacion() {
        return Habitacion;
    }

    public void setHabitacion(Habitacion Habitacion) {
        this.Habitacion = Habitacion;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isPagado() {
        return Pagado;
    }

    public void setPagado(boolean Pagado) {
        this.Pagado = Pagado;
    }

}
