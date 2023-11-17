/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.views;

import appinventario.controlers.SuministroControler;
import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import java.util.Date;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author J_lozada
 */
public class SuministroView {

    private SuministroControler controlador;
    private Scanner scanner;

    public SuministroView(SuministroControler controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar suministro");
            System.out.println("2. Ver suministros");
            System.out.println("3. Salir");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    registrarSuministro();
                    break;
                case "2":
                    verSuministros();
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void registrarSuministro() {
    //declarando variables. 
    int id =0,cantidad; 
    Date fechaCaducidad;
        
        System.out.println("Ingrese el ID del producto:");
    int productoId = Integer.parseInt(scanner.nextLine());
(int id,Producto producto, int cantidad,Date fechaCaducidad,Proveedor proveedor)
    // Aquí puedes solicitar al usuario los demás datos del suministro, como cantidad y fecha de caducidad.
    // Puedes usar scanner.nextLine() para leer la entrada del usuario.

    // Crear un nuevo suministro con los datos ingresados por el usuario
    Suministro nuevoSuministro = new Suministro();
    nuevoSuministro.setProducto(new Producto(productoId)); // Asumiendo que tienes un constructor en Producto que acepta el ID.

    // Completa el resto de la información del nuevoSuministro con la entrada del usuario.

    // Llamar al método registrar del controlador
    if (controlador.registrar(nuevoSuministro)) {
        System.out.println("Suministro registrado con éxito.");
    } else {
        System.out.println("Error al registrar el suministro.");
    }
}

    private void verSuministros() {
        List<Suministro> suministros = controlador.obtenerTodosSuministros();
        for (Suministro suministro : suministros) {
            System.out.println(suministro.getId() + ": " + suministro.getProducto().getNombre() + ", Cantidad: " + suministro.getCantidad()+", Fecha Vencimiento:"+suministro.getFechaCaducidad());
        }
    }

    public static void main(String[] args) {
        SuministroControler controlador = new SuministroControler();
        SuministroView vista = new SuministroView(controlador);
        vista.mostrarMenu();
    }
}