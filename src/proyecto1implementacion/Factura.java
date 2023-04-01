package proyecto1implementacion;

import proyecto1implementacion.Servicio.areaAsociada;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Johan Bautista
 */
public class Factura implements Serializable{

    private static Integer FacturasExpedidas;
    private Consumo informacionConsumo;
    private Huesped Huesped;
    private LocalDate Fecha;


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
    
    

    



}
