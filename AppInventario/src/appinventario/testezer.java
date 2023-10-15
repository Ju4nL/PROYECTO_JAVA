package appinventario;

import appinventario.models.CSV;
import appinventario.models.Seguridad;
import appinventario.models.Usuario;

public class testezer {

    public static void main(String[] arg) {
        String pass = Seguridad.hashPassword("123");
        Usuario newuser = new Usuario( "Ezer", "Vidarte", "ezerutp", pass);
        CSV<Usuario> usercsv = new CSV<>(Usuario.class);
        if (usercsv.registrar(newuser) == true) {
            System.out.println("Registrado correctamente!");
        }
    }
}