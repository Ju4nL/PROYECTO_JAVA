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

    public static void main(Usuario user) {
        if(!user.isAdmin()) { return; }
        Utilidades.cleanConsola();
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
                    Utilidades.cleanConsolaPausa();
                    break;
                case 2:
                    registrarUsuario();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 3:
                    eliminarUsuario();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 4:
                    actualizarUsuario();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    Utilidades.imprimirMensaje("Opción no válida. Por favor, intente de nuevo.", "error");
                    Utilidades.cleanConsolaPausa();
                    break;
            }
        }
    }

    private void verListaUsuarios() {
        Utilidades.cleanConsola();
        System.out.println(" ====> LISTA DE USUARIOS");
        List<Usuario> listUser = controlador.obtenerTodosUsuarios();
        Usuario.cabeceras();
        for (Usuario user : listUser) {
            user.mostrarTabla();
        }
    }

    private void registrarUsuario() {
        Utilidades.cleanConsola();
        System.out.println(" ====> REGISTRAR NUEVO USUARIO");
        System.out.println("");
        // Declarando variables
        String nombre, apellido, telefono, usuario, contraseña, cargo;
        boolean isAdmin = false;
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
        int Admin = scanner.nextInt();

        if(Admin==1) { isAdmin = true; }

        // Creando objeto usuario
        Usuario newuser = new Usuario(id,isAdmin, nombre, apellido, telefono, usuario, contraseña, cargo);
        boolean resultado = controlador.registrarUsuario(newuser);
        if (resultado) {
            Utilidades.imprimirMensaje(" Usuario registrado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje(" Error al registrar el usuario.", "error");
        }
    }

    private void eliminarUsuario() {
        verListaUsuarios();
        System.out.println("");
        System.out.print(" -> Ingrese el ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controlador.eliminarPorId(id);
        if (resultado) {
            Utilidades.imprimirMensaje(" Usuario eliminado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje(" Error al eliminar el usuario.", "error");
        }
    }

    private void actualizarUsuario() {
        verListaUsuarios();
        System.out.println("");
        // Declarando variables
        String nombre, apellido, telefono, usuario, contraseña, cargo;
        boolean isAdmin = false;
        System.out.print(" -> Ingrese el id del usuario para actualizar: ");
        int id  = scanner.nextInt();

        Usuario user = controlador.obtenerUsuarioPorId(id);
        if (user != null) {
            Usuario.cabeceras();
            user.mostrarTabla();
            System.out.println("");
            System.out.print(" ¿Desea actualizar todo el usuario? (Sí = 1 | No = 2): ");
            int respuesta = scanner.nextInt();
            scanner.nextLine();

            if (respuesta == 1) {
                System.out.print(" Presione 1 para salir, o Enter para continuar: ");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    System.out.print(" -> Ingrese nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print(" -> Ingrese apellido: ");
                    apellido = scanner.nextLine();

                    System.out.print(" -> Ingrese telefono: ");
                    telefono = scanner.nextLine();

                    System.out.print(" -> Ingrese usuario: ");
                    usuario = scanner.nextLine();

                    System.out.print(" -> Ingrese contraseña: ");
                    contraseña = scanner.nextLine();

                    System.out.print(" -> Ingrese cargo: ");
                    cargo = scanner.nextLine();

                    System.out.print(" -> ¿Es una cuenta de administración? (Sí = 1 | No = 2): ");
                    int admin = scanner.nextInt();

                    if(admin==1) { isAdmin = true; } else { isAdmin = false; }
                }
            } else {
                String atributo = "";
                nombre = user.getNombre();
                apellido = user.getApellido();
                telefono = user.getTelefono();
                usuario = user.getUsuario();
                contraseña = user.getPassword();
                cargo = user.getCargo();
                isAdmin = user.isAdmin();

                while (true) {
                    System.out.print(" Ingrese el atributo que quiere actualizar (nombre, apellido, telefono, usuario, contraseña, cargo, admin): ");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            System.out.print(" -> Ingrese nombre: ");
                            nombre = scanner.nextLine();
                            break;
                        case "apellido":
                            System.out.print(" -> Ingrese apellido: ");
                            apellido = scanner.nextLine();
                            break;
                        case "telefono":
                            System.out.print(" -> Ingrese telefono: ");
                            telefono = scanner.nextLine();
                            break;
                        case "usuario":
                            System.out.print(" -> Ingrese usuario: ");
                            usuario = scanner.nextLine();
                            break;
                        case "contraseña":
                            System.out.print(" -> Ingrese contraseña: ");
                            contraseña = scanner.nextLine();
                            break;
                        case "cargo":
                            System.out.print(" -> Ingrese cargo: ");
                            cargo = scanner.nextLine();
                            break;
                        case "admin":
                            System.out.print(" -> Es cuenta administrador? (Sí = 1 | No = 2): ");
                            int admin = scanner.nextInt();
                            if (admin == 1) { isAdmin = true; } else { isAdmin = false; }
                            break;
                        default:
                            Utilidades.imprimirMensaje(" Entrada no válida. Por favor, intente de nuevo.", "error");
                            continue;
                    }
                    break;
                }
            }

            Usuario userUpdate = new Usuario(id, isAdmin, nombre, apellido, telefono, usuario, contraseña, cargo);
            boolean resultado = controlador.actualizarPorId(id, userUpdate);
            if (resultado) {
                Utilidades.imprimirMensaje(" Usuario actualizado con éxito.", "success");
                Usuario userUp = controlador.obtenerUsuarioPorId(id);
                Usuario.cabeceras();
                userUp.mostrarTabla();
            } else {
                Utilidades.imprimirMensaje(" Error al actualizar el usuario", "error");
            }
        } else {
            Utilidades.imprimirMensaje(" Usuario no encontrado", "error");
        }
    }
}
