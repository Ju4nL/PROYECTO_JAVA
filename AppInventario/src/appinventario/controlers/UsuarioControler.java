package appinventario.controlers;

import java.util.List;
import appinventario.models.CSV;
import appinventario.models.Usuario;

public class UsuarioControler {

    // CSV es una clase que maneja la lectura y escritura de archivos CSV para el
    // modelo Usuario
    private CSV<Usuario> csv;

    // Constructor que inicializa la variable csv con una nueva instancia de CSV
    // para el modelo Usuario.
    public UsuarioControler() {
        this.csv = new CSV<>(Usuario.class);
    }

    // #CRUD

    // Funcion para validar un Usuario y retorna un entero que representa el id del usuario si es válido, de lo contrario retorna -1
    public Integer validarUsuario(String usuario, String password) {
        List<Usuario> listUsers = obtenerTodosUsuarios();
        for (Usuario u : listUsers) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(Usuario.hashPassword(password))) {
                return u.getId();
            }
        }
        return -1;
    }

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

}
