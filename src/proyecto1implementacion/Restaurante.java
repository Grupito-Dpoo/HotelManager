package proyecto1implementacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import proyecto1implementacion.Alimento.tipoAlimento;

/**
 *
 * @author Johan Bautista
 */
public class Restaurante extends Servicio {

    private ArrayList<Alimento> menu;
    private ArrayList<Alimento> paraHabitacion;
    private ArrayList<Alimento> paraRestaurante;

    public Restaurante(String nombre) {
        super(nombre);
        menu = new ArrayList<>();
        paraHabitacion = new ArrayList<>();
        paraRestaurante = new ArrayList<>();
    }

    public void CargarAlimentos(String archivo) {
        ArrayList<Alimento> menuInterno = new ArrayList<>();
        ArrayList<Alimento> paraHabitacionInterno = new ArrayList<>();
        ArrayList<Alimento> paraRestauranteInterno = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                tipoAlimento tAlimento = null;
                String[] datos = linea.split(";");
                String tipo = datos[0];
                String nombre = datos[1];
                Float tarifa = Float.parseFloat(datos[2]);
                boolean paraLlevar = Boolean.parseBoolean(datos[3]);
                String horario = datos[4];
                if (tipo.equals("Bebida")) {
                    tAlimento = tipoAlimento.BEBIDA;
                } else if (tipo.equals("Plato")) {
                    tAlimento = tipoAlimento.PLATO;
                }
                Alimento alimento = new Alimento(tAlimento, nombre, tarifa, paraLlevar, horario);
                menuInterno.add(alimento);

                if (paraLlevar) {
                    paraHabitacionInterno.add(alimento);
                } else {
                    paraRestauranteInterno.add(alimento);
                }
            }

            this.setMenu(menuInterno);
            this.setParaHabitacion(paraHabitacionInterno);
            this.setParaRestaurante(paraRestauranteInterno);

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public Alimento buscarAlimento(String id) {
        for (Alimento alimento : this.menu) {
            if (alimento.getIdentificador() == Integer.parseInt(id)) {
                return alimento;
            }
        }
        return null;
    }

    public void modificarAlimento(Alimento alimento, tipoAlimento Tipo, String Nombre, float Tarifa,
            boolean LugarDisponibilidad, String Horario) {
        alimento = new Alimento(Tipo, Nombre, Tarifa, LugarDisponibilidad, Horario);
    }

    public void crearNuevoAlimento(tipoAlimento Tipo, String Nombre, float Tarifa,
            boolean LugarDisponibilidad, String Horario) {
        Alimento alimento = new Alimento(Tipo, Nombre, Tarifa, LugarDisponibilidad, Horario);
        this.menu.add(alimento);
        if (LugarDisponibilidad) {
            this.paraHabitacion.add(alimento);
        } else {
            this.paraRestaurante.add(alimento);
        }
    }

    public ArrayList<Alimento> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Alimento> menu) {
        this.menu = menu;
    }

    public ArrayList<Alimento> getParaHabitacion() {
        return paraHabitacion;
    }

    public void setParaHabitacion(ArrayList<Alimento> paraHabitacion) {
        this.paraHabitacion = paraHabitacion;
    }

    public ArrayList<Alimento> getParaRestaurante() {
        return paraRestaurante;
    }

    public void setParaRestaurante(ArrayList<Alimento> paraRestaurante) {
        this.paraRestaurante = paraRestaurante;
    }

}
