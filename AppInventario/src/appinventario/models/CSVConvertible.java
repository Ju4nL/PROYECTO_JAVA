
package appinventario.models;


public interface CSVConvertible {
    String getFilePath();//contiene la ruta del objeto
    void fromCSV(String csvData); //Nos permite ingresr los datos del csv para crear objetos
    String toCSV();
    int getId(); 
}
