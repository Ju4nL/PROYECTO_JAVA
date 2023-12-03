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

    public List<Usuario> obtenerTodosUsuarios() {
        return this.csv.leerCSV();
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return csv.leerPorId(id);
    }

    public boolean cambiarPassword(Usuario user, String pass){
        user.setPassword(pass);
        csv.actualizarPorId(user.getId(), user);
        return true;
    }

    public boolean verificarPassword(String password, Usuario user) {
        String hashedPassword = Usuario.hashPassword(password);
        return user.getPassword().equals(hashedPassword);
    }

    public boolean actualizarPorId(int id, Usuario user) {
        return csv.actualizarPorId(id, user);
    }

}
