package appinventario.views;

import appinventario.controlers.ProveedorControler;
import appinventario.models.Proveedor;

import java.util.List;
import java.util.Scanner;

public class ProductoView{

    private ProveedorControler controlador;
    private Scanner scanner;

    public ProductoView() {
        controlador = new ProveedorControler();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
      
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| ***** GESTIÓN DE PROVEEDORES ***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Registrar Proveedor             |");
            System.out.println("| 2. Ver Proveedor por ID            |");
            System.out.println("| 3. Ver todos los Proveedores       |");
            System.out.println("| 4. Actualizar Proveedor            |");
            System.out.println("| 5. Eliminar Proveedor              |");
            System.out.println("| 6. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");


            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarProveedor();
                    break;
                case 2:
                    verProveedorPorId();
                    break;
                case 3:
                    verTodosLosProveedores();
                    break;
                case 4:
                    actualizarProveedor();
                    break;
                case 5:
                    eliminarProveedor();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void registrarProveedor() {
        System.out.println("Registrar nuevo Proveedor");
        // Declarando variables
        String nombre, telefono, direccion, email;
        int id=0;
        scanner.nextLine();
        //Generando inputs
        System.out.print("Ingrese nombre: ");
        nombre = scanner.nextLine();
        
        System.out.print("Ingrese teléfono: ");
        telefono = scanner.nextLine();
        
        System.out.print("Ingrese direccion: ");
        direccion = scanner.nextLine();
        
        System.out.print("Ingrese email: ");
        email = scanner.nextLine();
        
        //Creando objeto proveedor
        Proveedor proveedor = new Proveedor(id,nombre,telefono,direccion,email);
        boolean resultado = controlador.registrarProveedor(proveedor);
        if (resultado) {
            System.out.println("Proveedor registrado con éxito.");
        } else {
            System.out.println("Error al registrar el proveedor.");
        }
    }

    private void verProveedorPorId() {
        System.out.print("Ingrese el ID del proveedor: ");
        int id = scanner.nextInt();
        Proveedor proveedor = controlador.obtenerProveedorPorId(id);
        if (proveedor != null) {
            proveedor.cabeceras();
            proveedor.mostrarTabla();
    
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private void verTodosLosProveedores() {
        List<Proveedor> proveedores = controlador.obtenerTodosProveedores();
        
        // FORMATO TABLA
        
        // Poniendo caebceras
        proveedores.get(0).cabeceras();

        // Datos de los proveedores
        for (Proveedor proveedor : proveedores) {
            proveedor.mostrarTabla();
        }
        System.out.println("");
        
    }

    
    private void actualizarProveedor() {
        
        // Declarando variables
        String nombre, telefono, direccion, email;
        int id;
        //Generando inputs
        System.out.println("Ingrese el id del proveedor para actualizar:");
        id=scanner.nextInt();
        
        // Print de proveedor a actualizar
        Proveedor proveedor = controlador.obtenerProveedorPorId(id);
        if (proveedor != null) {
            proveedor.cabeceras();
            proveedor.mostrarTabla();
            
            System.out.println("Desea actualizar todo el proveedor?");
            System.out.println("(1=Si,2=No)");
            int respuesta=scanner.nextInt();
            
            if(respuesta==1){
                System.out.println("Si desea salir ingrese 1, si no omite");
                int salir=scanner.nextInt();
                if(salir==1){
                    System.out.println("Saliendo al Menú ");
                    return;
                }
                else{
                    System.out.print("Ingrese nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Ingrese teléfono: ");
                    telefono = scanner.nextLine();

                    System.out.print("Ingrese direccion: ");
                    direccion = scanner.nextLine();

                    System.out.print("Ingrese email: ");
                    email = scanner.nextLine();
                }
            }else{
                
                String atributo="";
                nombre=proveedor.getNombre();
                telefono=proveedor.getTelefono();
                direccion=proveedor.getDireccion();
                email=proveedor.getEmail();
                
                while (true) {
                    System.out.println("Ingrese el atributo que quiere actualizar (nombre, telefono, direccion, email):");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            System.out.print("Ingrese nombre: ");
                            nombre = scanner.nextLine();
                            break;
                        case "telefono":
                            System.out.print("Ingrese teléfono: ");
                            telefono = scanner.nextLine();
                            break;
                        case "direccion":
                            System.out.print("Ingrese direccion: ");
                            direccion = scanner.nextLine();
                            break;
                        case "email":
                            System.out.print("Ingrese email: ");
                            email = scanner.nextLine();
                            break;
                        default:
                            System.out.println("Entrada no válida. Por favor, intente de nuevo.");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }

            }

            //Creando objeto proveedor
            Proveedor proveedorActualizar = new Proveedor(id,nombre,telefono,direccion,email);
            boolean resultado = controlador.actualizarPorId(id,proveedorActualizar);
            if (resultado) {
                System.out.println("Proveedor actualizado con éxito.");
                Proveedor proveedorAct = controlador.obtenerProveedorPorId(id);
                proveedorAct.cabeceras();
                proveedorAct.mostrarTabla();
                
            } else {
                System.out.println("Error al actualizar el proveedor.");
            }
            
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private void eliminarProveedor() {
        System.out.print("Ingrese el ID del proveedor a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controlador.eliminarPorId(id);
        if (resultado) {
            System.out.println("Proveedor eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el proveedor.");
        }
    }

    public static void main(String[] args) {
        ProductoView vista = new ProductoView();
        vista.mostrarMenu();
    }
}
