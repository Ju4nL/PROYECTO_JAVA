package appinventario.controlers;

import java.util.List;
import appinventario.models.CSV;
import appinventario.models.Usuario;

public class AdminControler {

    private CSV<Usuario> csv;

    public AdminControler() {
        this.csv = new CSV<>(Usuario.class);
    }
    // #CRUD

    // Metodo para registrar un Usuario
    public boolean registrarUsuario(Usuario user) {
        List<Usuario> listUsers = obtenerTodosUsuarios();
        // Verifica si el usuario ya está registrado en la lista de usuarios
        for (Usuario u : listUsers) {
            if (u.getUsuario().equals(user.getUsuario())) {
                return false;
            }
        }
        // Se obtiene el id máximo actual y se le suma 1 para asignar un
        // nuevo id al usuario
        int id = csv.obtenerIdMaximo() + 1;
        user.setId(id);
        return csv.registrar(user);
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return csv.leerPorId(id);
    }

    // Metodo para obtener la lista de todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return this.csv.leerCSV();
    }

    public boolean eliminarPorId(int id) {
        return csv.eliminarPorId(id);
    }

    public boolean actualizarPorId(int id, Usuario user) {
        return csv.actualizarPorId(id, user);
    }
}
