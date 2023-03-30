package proyecto1implementacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class FacturaDeserializer {

    private static final String FILENAME = "facturas.csv";

    public static ArrayList<factura> loadFacturas(ArrayList<huesped> huespedes) {
        ArrayList<factura> facturas = new ArrayList<>();

        try (FileReader fr = new FileReader(FILENAME);
             BufferedReader br = new BufferedReader(fr);
             CSVReader csvReader = new CSVReader(br)) {

            // Lee la primera fila del archivo CSV (que contiene los nombres de las columnas)
            String[] header = csvReader.readNext();

            // Itera sobre las filas del archivo CSV
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                // Obtiene los datos de la fila
                String nombre = data[0];
                String documento = data[1];
                Integer celular = Integer.parseInt(data[2]);
                String habitacion = data[3];
                String consumo = data[4];
                String fecha = data[5];
                Float valorPagado = Float.parseFloat(data[6]);

                // Busca el huesped correspondiente en la lista de huespedes
                huesped h = null;
                for (huesped temp : huespedes) {
                    if (temp.getNombre().equals(nombre) && temp.getDocumento().equals(documento) && temp.getCelular().