package appinventario.utils;

import java.util.Scanner;
import java.io.Console;

public class Utilidades {

    public static void cleanConsolaPausa() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print(" Presione ENTER para continuar ");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("");
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
                colorCode = "\u001B[32m    "; // Verde para éxito
                break;
            case "warning":
                colorCode = "\u001B[33m    "; // Amarillo para advertencia
                break;
            case "error":
                colorCode = "\u001B[31m    "; // Rojo para error
                break;
            default:
                colorCode = "\u001B[0m    "; // Color predeterminado
                break;
        }
        //System.out.println("");
        System.out.println(colorCode + mensaje.toUpperCase() + "\u001B[0m"); // Restablecer el color después del mensaje
    }

    public static char[] solicitarInputPassword(String mensaje, Console consola) {
        String colorCode = "\u001B[36m "; // Color cyan para mensajes de solicitud
        return consola.readPassword( colorCode + mensaje + "\u001B[0m");
    }

    public static int solicitarInputInt(String mensaje, Scanner scanner) {
        String colorCode = "\u001B[36m "; // Color cyan para mensajes de solicitud
        System.out.print( colorCode + mensaje + "\u001B[0m");
        String retorno = scanner.nextLine();
        if (retorno.equalsIgnoreCase("cancelar")) { return -666; }
        try {
            int retornoInt = Integer.parseInt(retorno);
            return retornoInt;
        } catch (Exception e) {
            return -666;
        }
    }

    public static double solicitarInputDouble(String mensaje, Scanner scanner) {
        String colorCode = "\u001B[36m "; // Color cyan para mensajes de solicitud
        System.out.print( colorCode + mensaje + "\u001B[0m");
        String retorno = scanner.nextLine();
        if (retorno.equalsIgnoreCase("cancelar")) { return -666.6; }
        try {
            double retornoDouble = Double.parseDouble(retorno);
            return retornoDouble;
        } catch (Exception e) {
            return -666.6;
        }
    }

    public static String solicitarInput(String mensaje, Scanner scanner) {
        String colorCode = "\u001B[36m "; // Color cyan para mensajes de solicitud
        System.out.print( colorCode + mensaje + "\u001B[0m");
        String retorno = scanner.nextLine();
        if (retorno.equalsIgnoreCase("cancelar")) { return null; }
        return retorno;
    }

    // Sobrecarga para cuando el input se realiza con una pregunta
    public static String solicitarInput(String mensaje, Scanner scanner, int opcion) {
        String colorCode = "\u001B[33m "; // Amarillo para advertencia
        System.out.print( colorCode + mensaje + "\u001B[0m");
        String retorno = scanner.nextLine();
        if (retorno.equalsIgnoreCase("cancelar")) { return null; }
        return retorno;
    }

}