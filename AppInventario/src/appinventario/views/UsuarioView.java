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
        Utilidades.cleanConsola();
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
            int opcion = Utilidades.solicitarInputInt("| Seleccione una opción: ", scanner);

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
                    Utilidades.imprimirMensaje("Opción no válida. Por favor, intente de nuevo.", "error");
                    Utilidades.cleanConsolaPausa();
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
        System.out.println(" ====> CAMBIAR CONTRASEÑA");
        System.out.println("");
        Console console = System.console();

        while (true) {
            char[] contraseñaArray = Utilidades.solicitarInputPassword("-> Por seguridad, ingrese su contraseña actual: ", console);
            String contraseña = new String(contraseñaArray);

            if (controlador.verificarPassword(contraseña, user)) {
                while (true) {
                    char[] contraseñaNuevaArray = Utilidades.solicitarInputPassword("-> Ingrese nueva contraseña: ", console);
                    String contraseñaNueva = new String(contraseñaNuevaArray);
                    char[] contraseñaConfirmacionArray = Utilidades.solicitarInputPassword("-> Vuelva a escribir la nueva contraseña: ", console);
                    String contraseñaConfirmacion = new String(contraseñaConfirmacionArray);

                    if (contraseñaNueva.equals(contraseñaConfirmacion)) {
                        user.setPassword(contraseñaConfirmacion);

                        if (controlador.actualizarPorId(user.getId(), user)) {
                            Utilidades.imprimirMensaje("Contraseña actualizada correctamente.", "success");
                        } else {
                            Utilidades.imprimirMensaje("La contraseña no se pudo actualizar.", "error");
                        }
                        break;
                    } else {
                        Utilidades.imprimirMensaje("Las contraseñas no coinciden. Por favor, inténtelo de nuevo.", "error");
                    }
                }
                break;
            } else {
                Utilidades.imprimirMensaje("Contraseña incorrecta. Inténtelo de nuevo.", "error");
            }
        }

    }

}
