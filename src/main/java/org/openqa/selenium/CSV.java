package org.openqa.selenium;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {



        public void generarCsvEstat(ArrayList<String> linea) {
            String csvFilePath = "./archivo.csv";
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

                String[] data = {"Posicion", "Nombre", "Partidos Jugados", "Partidos Ganados", "Partidos Empatados", "Partidos Perdidos", "Goles", "Diferencia Goles", "Puntos", "Forma"};
                for (String lineas:linea) {
                    writer.writeNext(new String[]{lineas});
                }
                writer.writeNext(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}