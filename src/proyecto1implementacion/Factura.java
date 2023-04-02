package proyecto1implementacion;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Johan Bautista
 */
public class Factura implements Serializable {

    private static Integer FacturasExpedidas;
    private Integer Identificador;
    private Consumo informacionConsumo;
    private Huesped Huesped;
    private LocalDate Fecha;

    public Factura(Consumo informacionConsumo, Huesped Huesped, LocalDate Fecha) {
        this.informacionConsumo = informacionConsumo;
        this.Huesped = Huesped;
        this.Fecha = Fecha;
        this.Identificador = FacturasExpedidas;
        FacturasExpedidas += 1;
    }

    public Consumo getInformacionConsumo() {
        return informacionConsumo;
    }

    public void setInformacionConsumo(Consumo informacionConsumo) {
        this.informacionConsumo = informacionConsumo;
    }

    public Huesped getHuesped() {
        return Huesped;
    }

    public void setHuesped(Huesped Huesped) {
        this.Huesped = Huesped;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    public Integer getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(Integer Identificador) {
        this.Identificador = Identificador;
    }

}
