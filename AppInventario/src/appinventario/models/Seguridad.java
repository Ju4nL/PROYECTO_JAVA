package appinventario.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguridad {
    public static String hashPassword(String password) {
        try {
            // Algoritmo SHA-256 para generar el hash del password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convertir los bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
