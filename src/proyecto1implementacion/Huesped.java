package proyecto1implementacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Huesped implements Serializable{

    private String nombre;
    private String documento;
    private Integer celular;
    private Habitacion habitacionAsociada;
    private GrupoHuespedes grupoAsociado;
    private HashMap<Integer,Consumo> consumosAsociados;
    private ArrayList<Factura> FacturasAsociadas;

    public Huesped(String nombre, String documento, Integer celular) {
        this.nombre = nombre;
        this.documento = documento;
        this.celular = celular;
        this.consumosAsociados = new HashMap<>();
        this.FacturasAsociadas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public Habitacion getHabitacionAsociada() {
        return habitacionAsociada;
    }

    public void setHabitacionAsociada(Habitacion habitacionAsociada) {
        this.habitacionAsociada = habitacionAsociada;
    }

    public GrupoHuespedes getGrupoAsociado() {
        return grupoAsociado;
    }

    public void setGrupoAsociado(GrupoHuespedes grupoAsociado) {
        this.grupoAsociado = grupoAsociado;
    }

    public HashMap<Integer,Consumo> getConsumosAsociados() {
        return consumosAsociados;
    }

    public void agregarConsumo(Integer IdentificadorConsumo, Consumo consumo) {
        this.consumosAsociados.put(IdentificadorConsumo, consumo);
    }

    public Consumo buscarConsumo(Integer identificador) {
        return consumosAsociados.get(identificador);
    }

    public void eliminarConsumo(Integer identificador) {
        this.consumosAsociados.remove(identificador);
    }

    public ArrayList<Factura> getFacturasAsociadas() {
        return FacturasAsociadas;
    }

    public void setFacturasAsociadas(ArrayList<Factura> FacturasAsociadas) {
        this.FacturasAsociadas = FacturasAsociadas;
    }
}
