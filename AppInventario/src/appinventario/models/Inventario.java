package appinventario.models;

import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private CSV<Suministro> csv;
    private List<Suministro> suministros;

    public Inventario() {
        this.csv = new CSV<>(Suministro.class);
        this.suministros = new ArrayList<>();
        cargarSuministrosDesdeCSV();
    }

    private void cargarSuministrosDesdeCSV() {
        suministros = csv.leerCSV();
    }

    public boolean agregarSuministro(Suministro suministro) {
        int id = csv.obtenerIdMaximo() + 1;
        suministro.setId(id);
        if (csv.registrar(suministro)) {
            suministros.add(suministro);
            return true;
        }
        return false;
    }

    public boolean eliminarSuministro(int id) {
        if (csv.eliminarPorId(id)) {
            suministros.removeIf(s -> s.getId() == id);
            return true;
        }
        return false;
    }

    public Suministro obtenerSuministro(int id) {
        return suministros.stream()
                          .filter(s -> s.getId() == id)
                          .findFirst()
                          .orElse(null);
    }

    public List<Suministro> obtenerTodosSuministros() {
        return new ArrayList<>(suministros);
    }

    public boolean actualizarSuministro(int id, Suministro suministroActualizado) {
        if (csv.actualizarPorId(id, suministroActualizado)) {
            suministros.replaceAll(s -> s.getId() == id ? suministroActualizado : s);
            return true;
        }
        return false;
    }

    public Suministro encontrarSuministroConMenorCantidad() {
        return suministros.stream()
                          .min((s1, s2) -> Integer.compare(s1.getCantidad(), s2.getCantidad()))
                          .orElse(null);
    }
}
