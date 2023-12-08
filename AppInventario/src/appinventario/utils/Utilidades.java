package appinventario.utils;

import java.util.Scanner;

public class Utilidades {

    public static void cleanConsolaPausa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Presione ENTER para continuar ");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void cleanConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("");
    }

    public static void imprimirMensaje(String mensaje, String tipo) {
        String colorCode = "";
        switch (tipo.toLowerCase()) {
            case "success":
                colorCode = "\u001B[32m"; // Verde para éxito
                break;
            case "warning":
                colorCode = "\u001B[33m"; // Amarillo para advertencia
                break;
            case "error":
                colorCode = "\u001B[31m"; // Rojo para error
                break;
            default:
                colorCode = "\u001B[0m"; // Color predeterminado
                break;
        }
        System.out.println(colorCode + mensaje + "\u001B[0m"); // Restablecer el color después del mensaje
    }

}