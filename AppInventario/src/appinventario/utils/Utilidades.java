package appinventario.utils;

import java.util.Scanner;

public class Utilidades {
    public static void cleanConsola() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("");
        System.out.print("Presione ENTER para continuar ");
        scanner.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}