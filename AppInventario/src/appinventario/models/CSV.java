package appinventario.models;
import appinventario.models.CSVConvertible;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSV<T extends CSVConvertible> {

    private static final String CSV_DELIMITER = ",";
    private Class<T> clazz;

    //constructor
    public CSV(Class<T> clazz) {
        this.clazz = clazz;
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

    private int obtenerIdMaximo() {
        List<T> objetos = leerCSV();

        int maxId = 0;
        for (T objeto : objetos) {
            if(objeto.getId() > maxId) {
                maxId = objeto.getId();
            }
        }

        return maxId;
    }

    
    //Funcion para crear el arraylist con objetos
    public List<T> leerCSV() {
        List<T> objetos = new ArrayList<>();
        try {
            T instancia = this.clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());
             
            for (String[] registro : registros) {
                T objeto = this.clazz.newInstance();
                //unimos el csv para crear los objetos
                objeto.fromCSV(String.join(CSV_DELIMITER, registro));
                
                objetos.add(objeto);
            }
        } catch (InstantiationException | IllegalAccessException e) {      
            e.printStackTrace();
        }
        
        return objetos;
    }
    
    public T leerPorId(int id) {
        try {
            T instancia = this.clazz.newInstance();
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
        int id=obtenerIdMaximo()+1;
        objeto.setId(id);
        
        try (FileWriter fw = new FileWriter(objeto.getFilePath(), true);
                BufferedWriter bw = new BufferedWriter(fw)) {
                
                String nuevaLinea = objeto.toCSV();
                bw.write(nuevaLinea);
                bw.newLine(); // Agregar una nueva línea si es necesario
                return true;
                
        } catch (IOException e) {
            e.printStackTrace();                                                        
            return false;
        }
        
        
    }
    
 
    public boolean eliminarPorId( int id) {
        try {
            T instancia = this.clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());
            List<String[]> nuevosRegistros = new ArrayList<>();

            boolean encontrado = false;

            for (String[] registro : registros) {
                instancia.fromCSV(String.join(",", registro));
                if (instancia.getId() == id) {
                    encontrado = true;
                } else {
                    nuevosRegistros.add(registro);
                }
            }

            if (encontrado) {
                // Actualizar el archivo CSV con los registros restantes
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(instancia.getFilePath()))) {
                    for (String[] nuevoRegistro : nuevosRegistros) {
                        bw.write(String.join(",", nuevoRegistro));
                        bw.newLine();
                    }
                }
                return true;
            }
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean actualizarPorId(int id, T nuevoObjeto) {
        try {
            T instancia = this.clazz.newInstance();
            List<String[]> registros = leerRegistros(instancia.getFilePath());
            List<String[]> nuevosRegistros = new ArrayList<>();

            boolean encontrado = false;

            for (String[] registro : registros) {
                instancia.fromCSV(String.join(",", registro));
                if (instancia.getId() == id) {
                    // Reemplazar el objeto existente con el nuevo objeto
                    nuevosRegistros.add(nuevoObjeto.toCSV().split(",")); // Agregar el nuevo registro
                    encontrado = true;
                } else {
                    nuevosRegistros.add(registro); // Mantener el registro existente sin cambios
                }
            }

            if (encontrado) {
                // Actualizar el archivo CSV con los registros actualizados
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(instancia.getFilePath()))) {
                    for (String[] nuevoRegistro : nuevosRegistros) {
                        bw.write(String.join(",", nuevoRegistro));
                        bw.newLine();
                    }
                }
                return true;
            }
        } catch (InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
