package appinventario.controlers;

import appinventario.models.Inventario;
import appinventario.models.Suministro;
import java.util.List;

public class InventarioControler {

    private Inventario inventario;

    public InventarioControler() {
        this.inventario = new Inventario();
    }

    public boolean registrarSuministro(Suministro suministro) {
        return inventario.agregarSuministro(suministro);
    }

    public Suministro obtenerSuministroPorId(int id) {
        return inventario.obtenerSuministro(id);
    }

    public List<Suministro> obtenerTodosSuministros() {
        return inventario.obtenerTodosSuministros();
    }

    public boolean eliminarSuministroPorId(int id) {
        return inventario.eliminarSuministro(id);
    }

    public boolean actualizarSuministroPorId(int id, Suministro suministroActualizado) {
        return inventario.actualizarSuministro(id, suministroActualizado);
    }

    public Suministro encontrarSuministroConMenorCantidad() {
        return inventario.encontrarSuministroConMenorCantidad();
    }
}
