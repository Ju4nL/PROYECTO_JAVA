package appinventario.views;

import appinventario.controlers.UsuarioControler;
import appinventario.models.Usuario;
import appinventario.utils.Utilidades;

import java.io.Console;
import java.util.Scanner;

public class LoginView {

    UsuarioControler controlador;
    Scanner scanner;

    public LoginView() {
        this.controlador = new UsuarioControler();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        LoginView login = new LoginView();
        login.realizarLogin();
    }

    // Método para realizar el inicio de sesión
    private void realizarLogin() {

        Utilidades.cleanConsola();

        boolean loginCorrecto = false;
        Console console = System.console();

        // Nombre del programa en ASCII
        System.out.println("");
        System.out.println("");
        System.out.println("      >>                         >=>                                             >=>");
        System.out.println("     >>=>                        >=>                                             >=>                        >>");
        System.out.println("    >> >=>     >=> >=>  >=> >=>  >=> >==>>==>  >=>     >=>   >==>    >==>>==>  >=>>==>    >=> >=>  >> >==>        >=>");
        System.out.println("   >=>  >=>    >>   >=> >>   >=> >=>  >=>  >=>  >=>   >=>  >>   >=>   >=>  >=>   >=>    >=>   >=>   >=>    >=>  >=>  >=>");
        System.out.println("  >=====>>=>   >>   >=> >>   >=> >=>  >=>  >=>   >=> >=>   >>===>>=>  >=>  >=>   >=>   >=>    >=>   >=>    >=> >=>    >=>");
        System.out.println(" >=>      >=>  >=> >=>  >=> >=>  >=>  >=>  >=>    >=>=>    >>         >=>  >=>   >=>    >=>   >=>   >=>    >=>  >=>  >=>");
        System.out.println(">=>        >=> >=>      >=>      >=> >==>  >=>     >=>      >====>   >==>  >=>    >=>    >==>>>==> >==>    >=>    >=>");
        System.out.println("               >=>      >=>");
        System.out.println("");

        while (!loginCorrecto) {

            // Solicitar nombre de usuario y contraseña al usuario
            System.out.print("Ingrese nombre de usuario: ");
            String usuario = scanner.nextLine();
            char[] contraseñaArray = console.readPassword("Ingrese contraseña: ");
            String contraseña = new String(contraseñaArray);

            // Almacena el identificador del usuario validado, si no coincide devuelve -1
            int idUsuario = controlador.validarUsuario(usuario, contraseña);

            // Verificar las credenciales
            if (idUsuario != -1) {
                System.out.println("¡Login exitoso!");
                loginCorrecto = true;

                // Limpiar la consola
                Utilidades.cleanConsola();

                // Solo pa ver como vamo
                Usuario user = controlador.obtenerUsuarioPorId(idUsuario);
                UsuarioView.main(user);
            } else {
                System.out.println("Credenciales incorrectas. Intente de nuevo.");
            }
        }
    }
}
