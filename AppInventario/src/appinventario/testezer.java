package appinventario;

import appinventario.models.CSV;
import appinventario.models.Seguridad;
import appinventario.models.Usuario;

public class testezer {

    public static void main(String[] arg) {
<<<<<<< Updated upstream
        /*String pass = Seguridad.hashPassword("321");
        Usuario newuser = new Usuario( "Hillary", "Castañeda", "hillary1304", pass);
=======
        String pass = Seguridad.hashPassword("123");
        Usuario newuser = new Usuario(1, "Ezer", "Vidarte", "ezerutp", pass);
>>>>>>> Stashed changes
        CSV<Usuario> usercsv = new CSV<>(Usuario.class);
        if (usercsv.registrar(newuser) == true) {
            System.out.println("Registrado correctamente!");
        }*/
    }
}