package appinventario.storage;
import appinventario.storage.CSVConvertible;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSV<T extends CSVConvertible> {

    private static final String CSV_DELIMITER = ",";

    public List<T> leerCSV(Class<T> clazz) {
        List<T> objetos = new ArrayList<>();
        try {
            T instancia = clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());

            for (String[] registro : registros) {
                T objeto = clazz.newInstance();
                objeto.fromCSV(String.join(CSV_DELIMITER, registro));
                objetos.add(objeto);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return objetos;
    }

    private List<String[]> leerRegistros(String filePath) {
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                registros.add(linea.split(CSV_DELIMITER));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public T leerPorId(Class<T> clazz, int id) {
        try {
            T instancia = clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());

            for (String[] registro : registros) {
                instancia.fromCSV(String.join(",", registro));
                if (instancia.getId() == id) {
                    return instancia;
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
