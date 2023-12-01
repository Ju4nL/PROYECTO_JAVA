/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario;

import appinventario.views.InventarioView;
import appinventario.views.ProductoView;
import appinventario.views.ProveedorView; 
import java.util.Scanner;

/**
 *
 * @author J_lozada
 */
public class AppInventario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InventarioView inventarioView=new InventarioView();
        ProveedorView proveedorview=new ProveedorView();
        ProductoView productoView=new ProductoView();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| ******BIENVENIDO A INVENTARIO***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Ver inventario                  |");
            System.out.println("| 2. Registar nuevo proveedor        |");
            System.out.println("| 3. Registar nuevo producto         |");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:inventarioView.mostrarMenu();
                       break;
                case 2:proveedorview.mostrarMenu();
                       break;
                case 3:productoView.mostrarMenu();
                       break;
                case 4:
                        System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                }
        }
    }
    
}
