package appinventario.views;

import appinventario.controlers.UsuarioControler;

import java.util.Scanner;

public class UsuarioView {

    private UsuarioControler controlador;
    private Scanner scanner;

    // Constructor que inicializa el controlador de usuario y el escáner
    public UsuarioView() {
        this.controlador = new UsuarioControler();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        UsuarioView usuariovista = new UsuarioView();

    }

    // Método para realizar el inicio de sesión
    private void realizarLogin() {

        boolean loginCorrecto = false;

        while (!loginCorrecto) {
            System.out.print("Ingrese nombre de usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("Ingrese contraseña: ");
            String contraseña = scanner.nextLine();

            // Verificar las credenciales
            if (controlador.validarUsuario(usuario, contraseña) != -1) {
                System.out.println("¡Login exitoso!");
                loginCorrecto = true;
            } else {
                System.out.println("Credenciales incorrectas. Intente de nuevo.");
            }
        }
    }
}
