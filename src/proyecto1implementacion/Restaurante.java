package proyecto1implementacion;

import java.util.ArrayList;

/**
 *
 * @author Johan Bautista
 */
public class Restaurante extends Servicio {

    private ArrayList<Alimento> MenuRestaurante;
    private ArrayList<Alimento> MenuHabitacion;

    public ArrayList<Alimento> getMenuRestaurante() {
        return MenuRestaurante;
    }

    public void setMenuRestaurante(ArrayList<Alimento> MenuRestaurante) {
        this.MenuRestaurante = MenuRestaurante;
    }

    public ArrayList<Alimento> getMenuHabitacion() {
        return MenuHabitacion;
    }

    public void setMenuHabitacion(ArrayList<Alimento> MenuHabitacion) {
        this.MenuHabitacion = MenuHabitacion;
    }

}
