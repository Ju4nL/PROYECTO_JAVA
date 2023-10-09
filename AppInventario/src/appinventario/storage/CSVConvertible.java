
package appinventario.storage;


public interface CSVConvertible {
    String getFilePath();
    void fromCSV(String csvData);
    String toCSV();
    int getId();
}
