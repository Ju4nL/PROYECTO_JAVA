package appinventario.views;

import appinventario.controlers.UsuarioControler;
import appinventario.models.Usuario;
import appinventario.utils.Utilidades;

import java.io.Console;
import java.util.Scanner;

public class UsuarioView {

    private UsuarioControler controlador;
    private Scanner scanner;
    private Usuario user;

    // Constructor que inicializa el controlador de usuario y el escáner
    public UsuarioView(Usuario user) {
        this.controlador = new UsuarioControler();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    public static void main(Usuario user) {
        UsuarioView usuariovista = new UsuarioView(user);
        usuariovista.mostrarMenu();
    }

    public void mostrarMenu() {

        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| *****   GESTIÓN DE CUENTA    ***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Mostrar información             |");
            System.out.println("| 2. Cambiar contraseña              |");
            System.out.println("| 3. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarInfo();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 2:
                    cambiarContrasenia();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void mostrarInfo() {
        Utilidades.cleanConsola();
        // Mostrar información del usuario
        System.out.println("+------------------------------------+");
        System.out.println("|      INFORMACIÓN DE USUARIO        |");
        System.out.println("+------------------------------------+");
        System.out.println("| Nombre: " + user.getNombre());
        System.out.println("| Apellido: " + user.getApellido());
        System.out.println("| Telefono: " + user.getTelefono());
        System.out.println("| Usuario: " + user.getUsuario());
        System.out.println("| Cargo: " + user.getCargo());
        System.out.println("+------------------------------------+");
    }

    private void cambiarContrasenia() {

        Utilidades.cleanConsola();
        System.out.println(" CAMBIAR CONTRASEÑA");
        Console console = System.console();

        while (true) {
            char[] contraseñaArray = console.readPassword(" -> Por seguridad, ingrese su contraseña actual: ");
            String contraseña = new String(contraseñaArray);

            if (controlador.verificarPassword(contraseña, user)) {
                while (true) {
                    char[] contraseñaNuevaArray = console.readPassword(" -> Ingrese una nueva contraseña: ");
                    String contraseñaNueva = new String(contraseñaNuevaArray);
                    char[] contraseñaConfirmacionArray = console
                            .readPassword(" -> Vuelva a escribir la nueva contraseña: ");
                    String contraseñaConfirmacion = new String(contraseñaConfirmacionArray);

                    if (contraseñaNueva.equals(contraseñaConfirmacion)) {
                        user.setPassword(contraseñaConfirmacion);

                        if (controlador.actualizarPorId(user.getId(), user)) {
                            System.out.println(" Contraseña actualizada correctamente.");
                        } else {
                            System.out.println(" La contraseña no se pudo actualizar.");
                        }
                        break;
                    } else {
                        System.out.println(" Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
                    }
                }
                break;
            } else {
                System.out.println(" Contraseña incorrecta. Inténtelo de nuevo.");
            }
        }

    }

}
