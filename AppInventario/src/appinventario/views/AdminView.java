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
            int opcion = Utilidades.solicitarInputInt("| Seleccione una opción: ", scanner);

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
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, apellido, telefono, usuario, contraseña, cargo;
        boolean isAdmin = false;
        int id = 0;
        // Generando inputs
        nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
        if (nombre == null) { return; }

        apellido = Utilidades.solicitarInput("-> Ingrese apellido: ", scanner);
        if (apellido == null) { return; }

        telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
        if (telefono == null) { return; }

        usuario = Utilidades.solicitarInput("-> Ingrese usuario: ", scanner);
        if (usuario == null) { return; }

        contraseña = Utilidades.solicitarInput("-> Ingrese contraseña: ", scanner);
        if (contraseña == null) { return; }

        cargo = Utilidades.solicitarInput("-> Ingrese el cargo: ", scanner);
        if (cargo == null) { return; }

        String admin = Utilidades.solicitarInput("-> ¿Es una cuenta de administración? (Sí = 1 | No = 2): ", scanner, 0);
        if (admin == null) { return; }

        // Verificamos si es 1
        if(admin.equals("1")) { isAdmin = true; }

        // Creando objeto usuario
        Usuario newuser = new Usuario(id,isAdmin, nombre, apellido, telefono, usuario, contraseña, cargo);
        boolean resultado = controlador.registrarUsuario(newuser);
        if (resultado) {
            Utilidades.imprimirMensaje("Usuario registrado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al registrar el usuario.", "error");
        }
    }

    private void eliminarUsuario() {
        verListaUsuarios();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
    
        int id = Utilidades.solicitarInputInt("-> Ingrese el ID del usuario a eliminar: ", scanner);
        if (id == -666) { return; }

        boolean resultado = controlador.eliminarPorId(id);
        if (resultado) {
            Utilidades.imprimirMensaje("Usuario eliminado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al eliminar el usuario.", "error");
        }
    }

    private void actualizarUsuario() {
        verListaUsuarios();
        System.out.println("");;
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, apellido, telefono, usuario, contraseña, cargo;
        boolean isAdmin = false;
        
        int id  = Utilidades.solicitarInputInt("-> Ingrese el id del usuario para actualizar: ", scanner);
        if (id == -666) { return; }

        Usuario user = controlador.obtenerUsuarioPorId(id);
        if (user != null) {
            Usuario.cabeceras();
            user.mostrarTabla();
            System.out.println("");
           
            String respuesta =  Utilidades.solicitarInput("¿Desea actualizar todo el usuario? (Sí = 1 | No = 2): ", scanner, 0);
            if (respuesta == null) { return; }

            if (respuesta.equals("1")) {
                String input = Utilidades.solicitarInput("Presione 1 para salir, o Enter para continuar: ", scanner, 0);

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                    if (nombre == null) { return; }

                    apellido = Utilidades.solicitarInput("-> Ingrese apellido: ", scanner);
                    if (apellido == null) { return; }

                    telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
                    if (telefono == null) { return; }

                    usuario = Utilidades.solicitarInput("-> Ingrese usuario: ", scanner);
                    if (usuario == null) { return; }

                    contraseña = Utilidades.solicitarInput("-> Ingrese contraseña: ", scanner);
                    if (contraseña == null) { return; }

                    cargo = Utilidades.solicitarInput("-> Ingrese el cargo: ", scanner);
                    if (cargo == null) { return; }

                    String admin =  Utilidades.solicitarInput("-> ¿Es una cuenta de administración? (Sí = 1 | No = 2): ", scanner, 0);
                    if (admin == null) { return; }

                    if(admin.equals("1")) { isAdmin = true; } else { isAdmin = false; }
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
                    atributo = Utilidades.solicitarInput("Ingrese el atributo que quiere actualizar (nombre, apellido, telefono, usuario, contraseña, cargo, admin): ", scanner);
                    if (atributo == null) { return; }
                    
                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                            if (nombre == null) { return; }
                            break;
                        case "apellido":
                            apellido = Utilidades.solicitarInput("-> Ingrese apellido: ", scanner);
                            if (apellido == null) { return; }
                            break;
                        case "telefono":
                            telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
                            if (telefono == null) { return; }
                            break;
                        case "usuario":
                            usuario = Utilidades.solicitarInput("-> Ingrese usuario: ", scanner);
                            if (usuario == null) { return; }
                            break;
                        case "contraseña":
                            contraseña = Utilidades.solicitarInput("-> Ingrese contraseña: ", scanner);
                            if (contraseña == null) { return; }
                            break;
                        case "cargo":
                            cargo = Utilidades.solicitarInput("-> Ingrese el cargo: ", scanner);
                            if (cargo == null) { return; }
                            break;
                        case "admin":
                            String admin =  Utilidades.solicitarInput("-> ¿Es una cuenta de administración? (Sí = 1 | No = 2): ", scanner, 0);
                            if (admin == null) { return; }
                            if (admin.equals("1")) { isAdmin = true; } else { isAdmin = false; }
                            break;
                        default:
                            Utilidades.imprimirMensaje("Entrada no válida. Por favor, intente de nuevo.", "error");
                            continue;
                    }
                    break;
                }
            }

            Usuario userUpdate = new Usuario(id, isAdmin, nombre, apellido, telefono, usuario, contraseña, cargo);
            boolean resultado = controlador.actualizarPorId(id, userUpdate);
            if (resultado) {
                Utilidades.imprimirMensaje("Usuario actualizado con éxito.", "success");
                Usuario userUp = controlador.obtenerUsuarioPorId(id);
                Usuario.cabeceras();
                userUp.mostrarTabla();
            } else {
                Utilidades.imprimirMensaje("Error al actualizar el usuario", "error");
            }
        } else {
            Utilidades.imprimirMensaje("Usuario no encontrado", "error");
        }
    }
}
