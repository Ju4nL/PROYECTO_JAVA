package appinventario.views;

import java.io.Console;
import java.util.Scanner;

import appinventario.controlers.UsuarioControler;
import appinventario.models.Usuario;

public class LoginView {
    
    private UsuarioControler controlador;
    private Scanner scanner;

    // Constructor que inicializa el controlador de usuario y el escáner
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

        boolean loginCorrecto = false;
        Console console = System.console();

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
                System.out.print("\033[H\033[2J");
                System.out.flush();

                // Solo pa ver como vamo
                Usuario tmp = controlador.obtenerUsuarioPorId(idUsuario);
                System.out.println("¡Bienvenido, " + tmp.getNombre().toUpperCase() + " " + tmp.getApellido().toUpperCase() + "!");
                MenuView.main(null);
            } else {
                System.out.println("Credenciales incorrectas. Intente de nuevo.");
            }
        }
    }
}
