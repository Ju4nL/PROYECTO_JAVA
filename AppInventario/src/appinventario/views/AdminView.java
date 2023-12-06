package appinventario.views;

import appinventario.controlers.AdminControler;
import appinventario.models.Usuario;
import appinventario.utils.Utilidades;

import java.util.Scanner;
import java.util.List;

public class AdminView {

    private AdminControler controlador;
    private Scanner scanner;

    public AdminView() {
        this.controlador = new AdminControler();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        AdminView adminview = new AdminView();
        adminview.mostrarMenu();
    }

    private void mostrarMenu() {

        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| **  ADMINISTRACION DE USUARIOS  ** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Ver usuarios                    |");
            System.out.println("| 2. Registrar usuario               |");
            System.out.println("| 3. Eliminar usuario                |");
            System.out.println("| 4. Actualizar usuario              |");
            System.out.println("| 5. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verListaUsuarios();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    eliminarUsuario();
                    break;
                case 4:
                    // Insertar código para actualizar usuario
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
            Utilidades.cleanConsolaPausa();
        }
    }

    private void verListaUsuarios() {
        Utilidades.cleanConsola();
        System.out.println(" LISTA DE USUARIOS");
        List<Usuario> listUser = controlador.obtenerTodosUsuarios();
        Usuario.cabeceras();
        for (Usuario user : listUser) {
            user.mostrarTabla();
        }
    }

    private void registrarUsuario() {

        Utilidades.cleanConsola();
        System.out.println(" REGISTRAR NUEVO USUARIO");
        // Declarando variables
        String nombre, apellido, telefono, usuario, contraseña, cargo;
        boolean isAdmin = false;
        int Admin;
        int id = 0;
        // Generando inputs
        System.out.print(" -> Ingrese nombre: ");
        nombre = scanner.nextLine();

        System.out.print(" -> Ingrese apellido: ");
        apellido = scanner.nextLine();

        System.out.print(" -> Ingrese teléfono: ");
        telefono = scanner.nextLine();

        System.out.print(" -> Ingrese usuario: ");
        usuario = scanner.nextLine();

        System.out.print(" -> Ingrese contraseña: ");
        contraseña = scanner.nextLine();

        System.out.print(" -> Ingrese el cargo: ");
        cargo = scanner.nextLine();

        System.out.print(" -> ¿Es una cuenta de administración? (Sí = 1 | No = 2): ");
        Admin = scanner.nextInt();

        if(Admin==1) { isAdmin = true; }

        // Creando objeto producto
        Usuario newuser = new Usuario(id,isAdmin, nombre, apellido, telefono, usuario, contraseña, cargo);
        boolean resultado = controlador.registrarUsuario(newuser);
        if (resultado) {
            System.out.println(" Usuario registrado con éxito.");
        } else {
            System.out.println(" Error al registrar el usuario.");
        }
    }

    private void eliminarUsuario() {
        verListaUsuarios();
        System.out.println("");
        System.out.print(" -> Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controlador.eliminarPorId(id);
        if (resultado) {
            System.out.println("Usuario eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el usuario.");
        }
    }

    
}
