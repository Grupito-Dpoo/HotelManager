package proyecto1implementacion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class FacturaSerializer {

    private static final String FILENAME = "facturas.csv";

    public static void saveFacturas(ArrayList<Huesped> huespedes) {
        try (FileWriter fw = new FileWriter(FILENAME);
             BufferedWriter bw = new BufferedWriter(fw);
             CSVWriter csvWriter = new CSVWriter(bw)) {

            // Escribe la primera fila del archivo CSV
            String[] header = {"Nombre", "Documento", "Celular", "Habitacion", "Consumo", "Fecha", "ValorPagado"};
            csvWriter.writeNext(header);

            // Itera sobre los huespedes y sus facturas asociadas
            for (Huesped h : huespedes) {
                for (Factura f : h.getFacturasAsociadas()) {
                    // Obtiene los datos relevantes de la factura
                    String nombre = h.getNombre();
                    String documento = h.getDocumento();
                    Integer celular = h.getCelular();
                    String habitacion = f.getHabitacion().getNumero();
                    String consumo = f.getInformacionConsumo().getNombre();
                    String fecha = f.getFecha();
                    String valorPagado = String.valueOf(f.getValorPagado());

                    // Escribe los datos en una nueva fila del archivo CSV
                    String[] data = {nombre, documento, celular.toString(), habitacion, consumo, fecha, valorPagado};
                    csvWriter.writeNext(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}