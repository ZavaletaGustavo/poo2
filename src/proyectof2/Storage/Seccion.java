package proyectof2.Storage;

import proyectof2.Items.Producto;
import java.util.ArrayList;

public class Seccion {
    private String nombre;
    private ArrayList<Producto> productos;

    public Seccion(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    
    public void eliminarProducto(int index) {
        if (index >= 0 && index < productos.size()) {
            productos.remove(index);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }
    
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seccion: ").append(nombre).append(", Productos: ");
        for (Producto producto : productos) {
            sb.append(producto.getNombre()).append(", ");
        }
        return sb.toString();
    }
}
