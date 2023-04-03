package proyecto1implementacion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import proyecto1implementacion.Servicio.areaAsociada;

public class Consumo implements Serializable{
    private static Integer ConsumosExpedidos;
    private Integer Identificador;
    private String Fecha;
    private areaAsociada AreaConsumo;
    private Huesped Huesped;
    private Habitacion Habitacion;
    private double valor;
    private double valorTotal;
    private boolean Pagado;

    public Consumo(String Fecha, areaAsociada AreaConsumo, Huesped Huesped, 
    Habitacion Habitacion, double valor, boolean Pagado) {
        this.Identificador = ConsumosExpedidos;
        Consumo.ConsumosExpedidos += 1;
        this.Fecha = Fecha;
        this.AreaConsumo = AreaConsumo;
        this.Huesped = Huesped;
        this.Habitacion = Habitacion;
        this.valor = valor;
        this.valorTotal = valor * 1.19;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isPagado() {
        return Pagado;
    }

    public void setPagado(boolean Pagado) {
        this.Pagado = Pagado;
    }
    
    public String generarTextoFactura() {
		
		String textoFactura = Identificador + 
				"\t" + Fecha + 
				"\t" + Huesped.getNombre() + 
				"\t" + AreaConsumo + 
				"\t$" + valor + 
				"\t$" + valor * 0.19 +
				"\t$" + valorTotal;
		
		return textoFactura;
	}

}
