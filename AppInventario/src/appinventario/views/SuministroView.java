/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.views;

import appinventario.controlers.SuministroControler;
import appinventario.models.Suministro;

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
        // Aquí podrías solicitar al usuario que ingrese los datos del suministro
        // y luego llamar al método registrarSuministro() del controlador.
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