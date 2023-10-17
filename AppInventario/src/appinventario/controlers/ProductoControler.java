/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.controlers;

import appinventario.models.CSV;
import appinventario.models.Producto;
import java.util.List;
/**
 *
 * @author J_lozada
 */
public class ProductoControler {

    //Creando variable de CSV
    private CSV<Producto> csv;

    public ProductoControler() {
        this.csv = new CSV<>(Producto.class);
    }
    
    //CRUD
    //Metodo para registrar
    public boolean registrarProducto(Producto producto){
        int id=csv.obtenerIdMaximo()+1;
        producto.setId(id);
        return csv.registrar(producto);
    }
    
    //Metodo para obtener el producto por el id 
    public Producto obtenerProductoPorId(int id){
        return csv.leerPorId(id);
    }
    
    //Metodo para obtener todos los productos
    public List<Producto> obtenerTodosProductos(){
        return csv.leerCSV();
    }
    
    //Metodo para eliminar por id
    public boolean eliminarPorId(int id){
        return csv.eliminarPorId(id);
    }
    
    //Metodo para actualizar por id
    public boolean actualizarPorId(int id, Producto producto){
        return csv.actualizarPorId(id, producto);
    }
    
}
