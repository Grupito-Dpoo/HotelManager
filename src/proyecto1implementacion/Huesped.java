package proyecto1implementacion;

import java.util.ArrayList;

public class Huesped {

    private String nombre;
    private String documento;
    private Integer celular;
    private Habitacion habitacionAsociada;
    private GrupoHuespedes grupoAsociado;
    private ArrayList<Consumo> consumosAsociados;
    private ArrayList<Factura> FacturasAsociadas;

    public Huesped(String nombre, String documento, Integer celular) {
        this.nombre = nombre;
        this.documento = documento;
        this.celular = celular;
        this.consumosAsociados = new ArrayList<>();
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

    public ArrayList<Consumo> getConsumosAsociados() {
        return consumosAsociados;
    }

    public void agregarConsumo(Consumo consumo) {
        this.consumosAsociados.add(consumo);
    }

    public Consumo buscarConsumo(Integer identificador) {
        for (Consumo c : consumosAsociados) {
            if (c.getIdenficador().equals(identificador)) {
                return c;
            }
        }
        return null;
    }

    public void eliminarConsumo(Consumo consumo) {
        this.consumosAsociados.remove(consumo);
    }

    public ArrayList<Factura> getFacturasAsociadas() {
        return FacturasAsociadas;
    }

    public void setFacturasAsociadas(ArrayList<Factura> FacturasAsociadas) {
        this.FacturasAsociadas = FacturasAsociadas;
    }
}
