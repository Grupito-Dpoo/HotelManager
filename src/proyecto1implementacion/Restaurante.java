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
public class Restaurante extends Servicio{

    private ArrayList<Alimento> menu;
    private ArrayList<Alimento> paraHabitacion;
    private ArrayList<Alimento> paraRestaurante;

    public Restaurante() {
        menu = new ArrayList<>();
        paraHabitacion = new ArrayList<>();
        paraRestaurante = new ArrayList<>();
    }

    public void CargarAlimentos(String archivo) {
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
                if (tipo.equals("Bebida")){
                    tAlimento = tipoAlimento.BEBIDA;
                }
                else if(tipo.equals("Plato")){
                    tAlimento = tipoAlimento.PLATO;
                }
                Alimento alimento = new Alimento(tAlimento, nombre, tarifa, paraLlevar, horario);
                menu.add(alimento);

                if (paraLlevar) {
                    paraHabitacion.add(alimento);
                } else {
                    paraRestaurante.add(alimento);
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
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
