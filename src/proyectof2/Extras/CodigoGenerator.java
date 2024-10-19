package proyectof2.Extras;

import java.util.Random;

public class CodigoGenerator {
    public static String generarCodigo() {
        Random random = new Random();
        int numero = random.nextInt(1000); // Genera un número entre 0 y 999
        return "A" + String.format("%03d", numero); // Formatea el número a 3 dígitos
    }
}
