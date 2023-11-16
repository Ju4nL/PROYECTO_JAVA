/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.controlers;

import appinventario.models.CSV;
import appinventario.models.Suministro;
import java.util.List;
/**
 *
 * @author J_lozada
 */
public class SuministroControler {

    //Creando variable de CSV
    private CSV<Suministro> csv;

    public SuministroControler() {
        this.csv = new CSV<>(Suministro.class);
    }
    
    //CRUD
    //Metodo para registrar
    public boolean registrarSuministro(Suministro suministro){
        int id=csv.obtenerIdMaximo()+1;
        suministro.setId(id);
        return csv.registrar(suministro);
    }
    
    //Metodo para obtener por el id 
    public Suministro obtenerSuministroPorId(int id){
        return csv.leerPorId(id);
    }
    
    //Metodo para obtener 
    public List<Suministro> obtenerTodosSuministros(){
        return csv.leerCSV();
    }
    
    //Metodo para eliminar por id
    public boolean eliminarPorId(int id){
        return csv.eliminarPorId(id);
    }
    
    //Metodo para actualizar por id
    public boolean actualizarPorId(int id, Suministro suministro){
        return csv.actualizarPorId(id, suministro);
    }

    public boolean registrar(Suministro nuevoSuministro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
