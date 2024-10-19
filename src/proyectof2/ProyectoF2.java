package proyectof2;

import proyectof2.Login.Menu;
import proyectof2.Login.subMenu;

public class ProyectoF2 {

    public static void main(String[] args) {
        subMenu menu = new subMenu();
        String usuario = menu.seleccionarUsuario(); 
        Menu menuPrincipal = new Menu(usuario);
        menuPrincipal.mostrarMenuPrincipal();
    }
}
