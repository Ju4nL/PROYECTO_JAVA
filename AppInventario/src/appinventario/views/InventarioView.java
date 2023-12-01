package appinventario.views;

import appinventario.controlers.InventarioControler;
import appinventario.controlers.ProductoControler;
import appinventario.controlers.ProveedorControler;
import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InventarioView {
 
    private InventarioControler controladorInventario;
    private ProductoControler controladorProducto;
    private ProveedorControler controladorProveedor;
    private Scanner scanner;
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public InventarioView() {
        controladorInventario = new InventarioControler();
        controladorProducto = new ProductoControler();
        controladorProveedor = new ProveedorControler();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("+--------------------------------------+");
            System.out.println("| ***** GESTIÓN DE INVENTARIO *****    |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1. Registrar Suministro              |");
            System.out.println("| 2. Ver Suministro por ID             |");
            System.out.println("| 3. Ver todos los Suministros         |");
            System.out.println("| 4. Actualizar Suministro             |");
            System.out.println("| 5. Eliminar Suministro               |");
            System.out.println("| 6. Ver Suministro con Menor Cantidad |");
            System.out.println("| 7. Ver Suministro con Mayor Cantidad |");
            System.out.println("| 8. Salir                             |");
            System.out.println("+--------------------------------------+");
            System.out.print("| Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarSuministro();
                    break;
                case 2:
                    verSuministroPorId();
                    break;
                case 3:
                    verSuministros();
                    break;
                case 4:
                    actualizarSuministro();
                    break;
                case 5:
                    eliminarSuministro();
                    break;
                case 6:
                    verSuministroMenorCantidad();
                    break;
                case 7:
                    verSuministroMayorCantidad();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    
    }
    
    
    private void registrarSuministro()  {     
        System.out.println("Registrar nuevo Proveedor");
        // Declarando variables
        int id =0,cantidad; 
        Date fechaCaducidad;

        //Generando inputs
        //PRODUCTO
        Producto producto =inputProducto();
 
        //PROVEEDORES
        Proveedor proveedor = inputProveedor();
 
        System.out.print("Ingrese cantidad de productos: ");
        cantidad = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la fecha de caducidad: (dd/MM/yyyy)");
        String fechaString= scanner.nextLine();

        try {
            fechaCaducidad=formatoFecha.parse(fechaString);
            //Creando objeto proveedor
            Suministro nuevoSuministro = new Suministro(id,producto,cantidad,fechaCaducidad,proveedor);

            boolean resultado = controladorInventario.registrarSuministro(nuevoSuministro);
            if (resultado) {
                System.out.println("Suministro registrado con éxito.");
            } else {
                System.out.println("Error al registrar el Suministro.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    

    
    private void actualizarSuministro() {
        
        // Declarando variables
        int id,cantidad; 
        Date fechaCaducidad = null;
        Proveedor proveedor;
        Producto producto;
        String fechaString = null;
        //Generando inputs
        System.out.println("Ingrese el id del suministro para actualizar:");
        id=scanner.nextInt();
        
        // Print de proveedor a actualizar
        Suministro suministro = controladorInventario.obtenerSuministroPorId(id);
        if (suministro != null) {
            suministro.cabeceras();
            suministro.mostrarTabla();
            
            System.out.println("Desea actualizar todo el suministro?");
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
                    producto = inputProducto(); 
                    proveedor = inputProveedor();

                    System.out.print("Ingrese cantidad de productos: ");
                    cantidad = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese la fecha de caducidad: (dd/MM/yyyy)");
                    fechaString= scanner.nextLine();
                }
            }else{
                
                String atributo="";
                proveedor=suministro.getProveedor();
                producto=suministro.getProducto();
                cantidad=suministro.getCantidad();
                fechaCaducidad=suministro.getFechaCaducidad();
                
                
                while (true) {
                    System.out.println("Ingrese el atributo que quiere actualizar (producto,proveedor, cantidad, fechaCaducidad):");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "producto":
                               producto =inputProducto();                   
                            break;
                            
                        case "proveedor":
                                proveedor = inputProveedor();
                            break;
                        case "cantidad":
                            System.out.print("Ingrese cantidad de productos: ");
                            cantidad = scanner.nextInt();
                            scanner.nextLine();

                            break;
                        case "fechaCaducidad":
                            System.out.print("Ingrese la fecha de caducidad: (dd/MM/yyyy)");
                            fechaString= scanner.nextLine();
                            break;
                        default:
                            System.out.println("Entrada no válida. Por favor, intente de nuevo.");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }

            }
            
            try {
                    if (fechaCaducidad instanceof Date) {
                        
                    } else {
                        fechaCaducidad=formatoFecha.parse(fechaString);
                    }
                    
                    //Creando objeto proveedor
                    Suministro suministroActualizar = new Suministro(id,producto,cantidad,fechaCaducidad,proveedor);

                    boolean resultado = controladorInventario.actualizarSuministroPorId(id,suministroActualizar);
                    if (resultado) {
                        System.out.println("Suministro actualizado con éxito.");
                    } else {
                        System.out.println("Error al actualizar el Suministro.");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }
       
    
    private void eliminarSuministro() {
        System.out.print("Ingrese el ID del suministro a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controladorInventario.eliminarSuministroPorId(id);
        if (resultado) {
            System.out.println("Suministro eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el suministro.");
        }
    }    
    
    
    private void verSuministros() {
        List<Suministro> suministros = controladorInventario.obtenerTodosSuministros();
        // Poniendo caebceras
        suministros.get(0).cabeceras();
        
        for (Suministro suministro : suministros) {
            suministro.mostrarTabla();
        }
        System.out.println("");
    }
    
    
    private void verSuministroPorId() {
        System.out.print("Ingrese el ID del suministro: ");
        int id = scanner.nextInt();
        Suministro suministro = controladorInventario.obtenerSuministroPorId(id);
        if (suministro != null) {
            suministro.cabeceras();
            suministro.mostrarTabla();
    
        } else {
            System.out.println("Suministro no encontrado.");
        }
    }
    
    
    private void verSuministroMenorCantidad() {
        Suministro suministro = controladorInventario.encontrarSuministroConMenorCantidad();
        if (suministro != null) {
            mostrarInformacionSuministro(suministro);
        } else {
            System.out.println("No hay suministros en el inventario.");
        }
    }
    
    private void verSuministroMayorCantidad() {
        Suministro suministro = controladorInventario.encontrarSuministroConMayorCantidad();
        if (suministro != null) {
            mostrarInformacionSuministro(suministro);
        } else {
            System.out.println("No hay suministros en el inventario.");
        }
    }

    private void mostrarInformacionSuministro(Suministro suministro) {
        System.out.println("ID: " + suministro.getId());
        System.out.println("Producto: " + suministro.getProducto().getNombre());
        System.out.println("Cantidad: " + suministro.getCantidad());
        System.out.println("Proveedor: " + suministro.getProveedor().getNombre());
        System.out.println("Fecha de Caducidad: " + suministro.getFechaCaducidad());
        System.out.println("---------------------------------------");
    }
    
    private Producto inputProducto(){
        //PRODUCTO
        System.out.print("Ingrese el idproducto: ");
        //Mostrando productos
            List<Producto> productos = controladorProducto.obtenerTodosProductos();
            productos.get(0).cabeceras();
            for (Producto producto : productos) {
                producto.mostrarTabla();
            }
            System.out.println("");
        //fin productos
        int idproducto=scanner.nextInt();
        Producto inputproducto = controladorProducto.obtenerProductoPorId(idproducto);
        return inputproducto;
    }
    
    private Proveedor inputProveedor(){
        System.out.print("Ingrese el idproveedor: ");
            //Mostrando proveedores
                List<Proveedor> proveedores = controladorProveedor.obtenerTodosProveedores();
                proveedores.get(0).cabeceras();
                for (Proveedor proveedor : proveedores) {
                    proveedor.mostrarTabla();
                }
                System.out.println("");
            //fin proveedores
            int idproveedor=scanner.nextInt();
            Proveedor inputproveedor = controladorProveedor.obtenerProveedorPorId(idproveedor);
        return inputproveedor;
    }
    
    
    
    public static void main(String[] args) {
        InventarioView vista = new InventarioView();
        vista.mostrarMenu();
    }
}
