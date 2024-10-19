package proyectof2.Login;

import java.util.Scanner;

public class subMenu {

    public String seleccionarUsuario() {
        Scanner scanner = new Scanner(System.in);
        String usuario = "";
        boolean esValido = false;
        boolean esPrimeraVez = true;
        final int MAX_INTENTOS = 5;

        while (!esValido) {
            if (esPrimeraVez) {
                System.out.println("Bienvenido. Quien esta ingresando al sistema?");
                esPrimeraVez = false;
            } else {
                System.out.println("Error. Quien esta ingresando al sistema?");
            }

            System.out.println("(1) Jefe");
            System.out.println("(2) Empleado");
            System.out.println("(3) Visitante");
            System.out.print("Seleccione una de las tres opciones: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (verificarContrasena(scanner, "Jefe", 999, MAX_INTENTOS)) {
                        usuario = "Jefe";
                        esValido = true;
                    }
                    break;
                case 2:
                    if (verificarContrasena(scanner, "Empleado", 777, MAX_INTENTOS)) {
                        usuario = "Empleado";
                        esValido = true;
                    }
                    break;
                case 3:
                    System.out.println("Acceso como Visitante. Entrada libre.");
                    usuario = "Visitante";
                    esValido = true;
                    break;
                default:
                  
                    break;
            }
        }
        return usuario;
    }

    private boolean verificarContrasena(Scanner scanner, String usuario, int contrasenaCorrecta, int maxIntentos) {
        int intentosRestantes = maxIntentos;

        while (intentosRestantes > 0) {
            System.out.print("Ingrese la contrasena: ");
            int contrasena = scanner.nextInt();

            if (contrasena == contrasenaCorrecta) {
                System.out.println("Acceso permitido como " + usuario);
                return true;
            } else {
                intentosRestantes--;
                if (intentosRestantes > 0) {
                    System.out.println("Contrasena incorrecta, intente nuevamente.");
                    System.out.println(intentosRestantes + " intentos restantes.");
                } else {
                    System.out.println("Contrasena incorrecta. Cerrando programa.");
                    System.exit(0);  
                }
            }
        }
        return false;
    }
}
