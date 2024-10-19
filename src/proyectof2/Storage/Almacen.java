package proyectof2.Storage;

import proyectof2.Items.Producto;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Almacen {
    private String codigo;
    private String locacion;
    private List<Seccion> secciones;

    public Almacen(String codigo, String locacion) {
        this.codigo = codigo;
        this.locacion = locacion;
        this.secciones = new ArrayList<>();
        inicializarSecciones();
        inicializarProductosPorDefecto();
    }

    private void inicializarSecciones() {
        secciones.add(new Seccion("Perecibles"));
        secciones.add(new Seccion("Refrigerantes"));
        secciones.add(new Seccion("Bebidas"));
        secciones.add(new Seccion("Secos"));
    }

    private void inicializarProductosPorDefecto() {
        secciones.get(0).agregarProducto(new Producto("Leche", 0.90, 50, LocalDate.now().plusMonths(3)));
        secciones.get(0).agregarProducto(new Producto("Yogur", 1.20, 30, LocalDate.now().plusMonths(2)));
        secciones.get(1).agregarProducto(new Producto("Carne", 5.00, 20, LocalDate.now().plusMonths(1)));
        secciones.get(1).agregarProducto(new Producto("Pollo", 4.00, 15, LocalDate.now().plusMonths(1)));
        secciones.get(2).agregarProducto(new Producto("Jugo de Naranja", 1.50, 30, LocalDate.now().plusMonths(4)));
        secciones.get(2).agregarProducto(new Producto("Cerveza", 1.80, 40, LocalDate.now().plusMonths(6)));
        secciones.get(3).agregarProducto(new Producto("Arroz", 1.50, 100, LocalDate.now().plusMonths(6)));
        secciones.get(3).agregarProducto(new Producto("Pasta", 1.00, 60, LocalDate.now().plusMonths(12)));
        secciones.get(3).agregarProducto(new Producto("Sal", 0.50, 100, LocalDate.now().plusMonths(24)));
    }

    public List<Seccion> getSecciones() {
        return secciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getLocacion() {
        return locacion;
    }

    public void visualizarAlmacen() {
        System.out.println("Almacen: " + codigo + " | Locacion: " + locacion);
        for (Seccion seccion : secciones) {
            System.out.println(seccion);
        }
        
}




}

