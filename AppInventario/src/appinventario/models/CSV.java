package appinventario.models;
import appinventario.models.CSVConvertible;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSV<T extends CSVConvertible> {

    private static final String CSV_DELIMITER = ",";

    //Funcion para crear el arraylist con objetos
    public List<T> leerCSV(Class<T> clazz) {
        List<T> objetos = new ArrayList<>();
        try {
            T instancia = clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());
             
            for (String[] registro : registros) {
                T objeto = clazz.newInstance();
                //unimos el csv para crear los objetos
                objeto.fromCSV(String.join(CSV_DELIMITER, registro));
                
                objetos.add(objeto);
            }
        } catch (InstantiationException | IllegalAccessException e) {      
            e.printStackTrace();
        }
        
        return objetos;
    }

    //Funcion para leer el CSV de cualquier ruta
    private List<String[]> leerRegistros(String filePath) {
        List<String[]> registros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                registros.add(linea.split(CSV_DELIMITER));
            }
        } catch (IOException e) {
            //imprimir el registro del stack donde se ha iniciado la excepción
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

    
    public boolean registrar(T objeto) {
        try (FileWriter fw = new FileWriter(objeto.getFilePath(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            String nuevaLinea = objeto.toCSV();
            out.println(nuevaLinea);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
