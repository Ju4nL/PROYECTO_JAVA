/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.controlers;
import appinventario.models.CSV;
import appinventario.models.Proveedor;
import java.util.List;
/**
 *
 * @author J_lozada
 */
public class ProveedorControler {

    //Creando variable de CSV
    private CSV<Proveedor> csv;

    public ProveedorControler() {
        this.csv = new CSV<>(Proveedor.class);
    }
    
    //CRUD
    //Metodo para registrar
    public boolean registrarProveedor(Proveedor proveedor){
        int id=csv.obtenerIdMaximo()+1;
        proveedor.setId(id);
        return csv.registrar(proveedor);
    }
    
    //Metodo para obtener el producto por el id 
    public Proveedor obtenerProveedorPorId(int id){
        return csv.leerPorId(id);
    }
    
    //Metodo para obtener todos los productos
    public List<Proveedor> obtenerTodosProveedores(){
        return csv.leerCSV();
    }
    
    //Metodo para eliminar por id
    public boolean eliminarPorId(int id){
        return csv.eliminarPorId(id);
    }
    
    //Metodo para actualizar por id
    public boolean actualizarPorId(int id, Proveedor provedor){
        return csv.actualizarPorId(id, provedor);
    }
    
    
}
