package proyecto1implementacion;

import proyecto1implementacion.Servicio.areaAsociada;

/**
 *
 * @author Johan Bautista
 */
public class Factura extends contable {

    private Consumo InformacionConsumo;

    public Factura(String Fecha, Consumo InformacionConsumo, float ValorPagado) {

        super(Fecha, InformacionConsumo.getAreaAsociada(),
                InformacionConsumo.getHuesped(), InformacionConsumo.getHabitacion(), ValorPagado);
        this.InformacionConsumo = InformacionConsumo;
    }

}
