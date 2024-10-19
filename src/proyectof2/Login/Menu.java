package proyectof2.Login;

import proyectof2.Storage.Almacen;
import proyectof2.Items.Producto;
import proyectof2.Extras.CodigoGenerator;
import proyectof2.Storage.Seccion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private String usuario;
    private ArrayList<Almacen> almacenes;

    public Menu(String usuario) {
        this.usuario = usuario;
        this.almacenes = new ArrayList<>();
        // Crear dos almacenes por defecto
        almacenes.add(new Almacen(CodigoGenerator.generarCodigo(), "Locacion A"));
        almacenes.add(new Almacen(CodigoGenerator.generarCodigo(), "Locacion B"));
    }

    public void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("(1) Visualizar almacenes");
            if (usuario.equals("Jefe")) {
                System.out.println("(2) Crear nuevo almacen");
                System.out.println("(3) Modificar productos");
                System.out.println("(4) Buscar productos");
                System.out.println("(5) Reportes de inventario");
            }
            System.out.println("(6) Ayuda");
            System.out.println("(0) Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    visualizarAlmacenes();
                    break;
                case 2:
                    if (usuario.equals("Jefe")) {
                        crearAlmacen();
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;
                case 3:
                    if (usuario.equals("Jefe")) {
                        modificarProductos();
                    } else {
                        System.out.println("Acceso denegado.");
                    }
                    break;
                case 4:
                    buscarProductos();
                    break;
                case 5:
                    reportesInventario();
                    break;
                case 6:
                    ayuda();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Cerrando programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    // Método para visualizar almacenes
    public void visualizarAlmacenes() {
        for (Almacen almacen : almacenes) {
            almacen.visualizarAlmacen();
        }
    }

    // Método para crear un nuevo almacén
    public void crearAlmacen() {
        Scanner scanner = new Scanner(System.in);
        String codigo = CodigoGenerator.generarCodigo();
        System.out.print("Ingrese la locacion del nuevo almacen: ");
        String locacion = scanner.nextLine();
        Almacen nuevoAlmacen = new Almacen(codigo, locacion);
        almacenes.add(nuevoAlmacen);
        System.out.println("Almacen creado: " + nuevoAlmacen.getCodigo());
    }

    // Método para modificar productos
    public void modificarProductos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el almacén para modificar productos:");
        for (int i = 0; i < almacenes.size(); i++) {
            System.out.println((i + 1) + ". " + almacenes.get(i).getCodigo());
        }
        int opcionAlmacen = scanner.nextInt();
        if (opcionAlmacen < 1 || opcionAlmacen > almacenes.size()) {
            System.out.println("Opción inválida.");
            return;
        }
        Almacen almacenSeleccionado = almacenes.get(opcionAlmacen - 1);

        System.out.println("(1) Agregar producto");
        System.out.println("(2) Modificar producto");
        System.out.println("(3) Eliminar producto");
        System.out.print("Seleccione una opción: ");
        int opcionProducto = scanner.nextInt();
        scanner.nextLine();

        switch (opcionProducto) {
            case 1:
                System.out.print("Ingrese el nombre del producto: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese el precio del producto: ");
                double precio = scanner.nextDouble();
                System.out.print("Ingrese el stock del producto: ");
                int stock = scanner.nextInt();
                System.out.print("Ingrese la fecha de vencimiento (AAAA-MM-DD): ");
                String fechaStr = scanner.next();
                LocalDate fechaVencimiento = LocalDate.parse(fechaStr);
                Producto nuevoProducto = new Producto(nombre, precio, stock, fechaVencimiento);
                almacenSeleccionado.getSecciones().get(0).agregarProducto(nuevoProducto); // Agregar a una sección
                System.out.println("Producto agregado.");
                break;
            case 2:
                System.out.println("Seleccione la sección:");
                for (int i = 0; i < almacenSeleccionado.getSecciones().size(); i++) {
                    System.out.println((i + 1) + ". " + almacenSeleccionado.getSecciones().get(i).getNombre());
                }
                int opcionSeccion = scanner.nextInt();
                if (opcionSeccion < 1 || opcionSeccion > almacenSeleccionado.getSecciones().size()) {
                    System.out.println("Opción inválida.");
                    return;
                }
                Seccion seccionSeleccionada = almacenSeleccionado.getSecciones().get(opcionSeccion - 1);

                System.out.println("Seleccione el producto a modificar:");
                for (int i = 0; i < seccionSeleccionada.getProductos().size(); i++) {
                    System.out.println((i + 1) + ". " + seccionSeleccionada.getProductos().get(i).getNombre());
                }
                int opcionModificar = scanner.nextInt();
                if (opcionModificar < 1 || opcionModificar > seccionSeleccionada.getProductos().size()) {
                    System.out.println("Opción inválida.");
                    return;
                }
                Producto productoAModificar = seccionSeleccionada.getProductos().get(opcionModificar - 1);

                System.out.print("Ingrese el nuevo nombre del producto: ");
                scanner.nextLine(); // Consumir el salto de línea
                String nuevoNombre = scanner.nextLine();

                System.out.print("Ingrese el nuevo precio del producto: ");
                double nuevoPrecio = scanner.nextDouble();

                System.out.print("Ingrese el nuevo stock del producto: ");
                int nuevoStock = scanner.nextInt();

                System.out.print("Ingrese la nueva fecha de vencimiento (AAAA-MM-DD): ");
                String nuevaFechaStr = scanner.next();
                LocalDate nuevaFechaVencimiento = LocalDate.parse(nuevaFechaStr);

                productoAModificar.setNombre(nuevoNombre);
                productoAModificar.setPrecio(nuevoPrecio);
                productoAModificar.setStock(nuevoStock);
                productoAModificar.setFechaVencimiento(nuevaFechaVencimiento);

                System.out.println("Producto modificado.");
                break;

            case 3:
                System.out.println("Seleccione la sección:");
                for (int i = 0; i < almacenSeleccionado.getSecciones().size(); i++) {
                    System.out.println((i + 1) + ". " + almacenSeleccionado.getSecciones().get(i).getNombre());
                }
                opcionSeccion = scanner.nextInt();
                if (opcionSeccion < 1 || opcionSeccion > almacenSeleccionado.getSecciones().size()) {
                    System.out.println("Opción inválida.");
                    return;
                }
                seccionSeleccionada = almacenSeleccionado.getSecciones().get(opcionSeccion - 1);

                System.out.println("Seleccione el producto a eliminar:");
                for (int i = 0; i < seccionSeleccionada.getProductos().size(); i++) {
                    System.out.println((i + 1) + ". " + seccionSeleccionada.getProductos().get(i).getNombre());
                }
                int opcionEliminar = scanner.nextInt();
                if (opcionEliminar < 1 || opcionEliminar > seccionSeleccionada.getProductos().size()) {
                    System.out.println("Opción inválida.");
                    return;
                }
                seccionSeleccionada.eliminarProducto(opcionEliminar - 1);
                System.out.println("Producto eliminado.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    // Métodos de ejemplo para buscar productos, reportes de inventario y ayuda (vacíos por ahora)
    public void buscarProductos() {
        System.out.println("Función de buscar productos aún no implementada.");
    }

    public void reportesInventario() {
        System.out.println("Función de reportes de inventario aún no implementada.");
    }

    public void ayuda() {
        System.out.println("Función de ayuda aún no implementada.");
    }
}
