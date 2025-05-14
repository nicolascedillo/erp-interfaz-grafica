package ec.edu.ups.poo.controllers;

import ec.edu.ups.poo.models.Enums.Feriado;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoConIva;
import ec.edu.ups.poo.models.ProductoSinIva;
import ec.edu.ups.poo.models.Provedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlProvedor {
    Scanner sc = new Scanner(System.in);
    private static List<Provedor> provedores;
    private List<Producto> productosTemp;

    public ControlProvedor() {

        provedores = new ArrayList<>();
        productosTemp = new ArrayList<>();

        ProductoConIva producto1 = new ProductoConIva(1, "Computadora",  500.0,"ASUS", Feriado.NO_FERIADO);
        ProductoSinIva producto2 = new ProductoSinIva(2,"Sal",2.0,"Mi Sal Querida", "El producto no agraba iva");
        ProductoConIva producto3 = new ProductoConIva(3, "Smartphone", 300.0, "Samsung", Feriado.ANIO_NUEVO);
        ProductoConIva producto4 = new ProductoConIva(4, "Televisor", 800.0, "LG", Feriado.FERIADO_NAVIDAD);
        ProductoSinIva producto5 = new ProductoSinIva(5, "Agua mineral", 1.5, "Agua Pura", "Productoexento de IVA");
        ProductoSinIva producto6 = new ProductoSinIva(6, "Arroz", 1.2, "Grano de Oro", "No grava IVA según ley");
        ProductoConIva producto7 = new ProductoConIva(7, "Impresora", 150.0, "HP", Feriado.FIESTAS_DE_CUENCA);
        ProductoSinIva producto8 = new ProductoSinIva(8, "Medicamento", 10.0, "SaludTotal", "Medicamento libre de IVA");


        Provedor provedor = new Provedor("0105236525","Juan","Perez","095 542 6252","Av de las Americas","juanperez@gmail.com");
        Provedor provedor2 = new Provedor("0108987654", "María", "Gomez", "096 123 4567", "Calle Larga", "mariagomez@hotmail.com");
        Provedor provedor3 = new Provedor("0101122334", "Carlos", "Vera", "098 765 4321", "Av Solano", "carlosvera@yahoo.com");
        Provedor provedor4 = new Provedor("0105566778", "Lucía", "Mendoza", "097 334 2211", "Av Ordóñez Lasso", "luciamendoza@gmail.com");
        Provedor provedor5 = new Provedor("0103344556", "Andrés", "Salazar", "096 778 8899", "Calle Bolívar", "andressalazar@outlook.com");
        Provedor provedor6 = new Provedor("0106677889", "Patricia", "Rivas", "095 112 3344", "Av Loja", "patriciarivas@hotmail.com");
        Provedor provedor7 = new Provedor("0102233445", "Diego", "Martínez", "098 556 7788", "Camino del Tejar", "diegomartinez@yahoo.com");
        Provedor provedor8 = new Provedor("0104455667", "Ana", "Cárdenas", "097 665 4433", "Av Remigio Crespo", "anacardenas@gmail.com");

        provedores.add(provedor);
        provedores.add(provedor2);
        provedores.add(provedor3);
        provedores.add(provedor4);
        provedores.add(provedor5);
        provedores.add(provedor6);
        provedores.add(provedor7);
        provedores.add(provedor8);


        provedor.addProducto(producto1);
        provedor2.addProducto(producto2);
        provedor3.addProducto(producto3);
        provedor4.addProducto(producto4);
        provedor5.addProducto(producto5);
        provedor6.addProducto(producto6);
        provedor7.addProducto(producto7);
        provedor8.addProducto(producto8);

        productosTemp.add(producto1);
        productosTemp.add(producto2);
        productosTemp.add(producto3);
        productosTemp.add(producto4);
        productosTemp.add(producto5);
        productosTemp.add(producto6);
        productosTemp.add(producto7);
        productosTemp.add(producto8);

    }

    public Provedor getProvedores(int n) {
        return provedores.get(n-1);
    }

    public void agregarProveedor() {
        System.out.print("Ingrese la cantidad de proveedores a registrar: ");
        int numeroProvedores = sc.nextInt();

        for (int i = 0; i < numeroProvedores; i++){
            System.out.println("\t\tIngrese los datos del proveedor " + (i+1) + ": ");
            System.out.print("\t\t\tCédula: ");
            String cedula = sc.next();
            System.out.print("\t\t\tNombre: ");
            String nombre = sc.next();
            System.out.print("\t\t\tApellido: ");
            String apellido = sc.next();
            System.out.print("\t\t\tTeléfono: ");
            String telefono = sc.next();
            System.out.print("\t\t\tDirección: ");
            String direccion = sc.next();
            System.out.print("\t\t\tCorreo: ");
            String correo = sc.next();
            provedores.add(new Provedor(cedula, nombre, apellido, telefono, direccion, correo));
        }
        System.out.println("Proveedores registrados correctamente.");
        System.out.println("---------------------------------------------");

    }

    public void printProvedores() {

       System.out.println("Lista de proveedores: ");
        for (Provedor provedor : provedores) {
            System.out.println("\t" + provedor);

            System.out.println("---------------------------------------------");
        }
    }

    public void buscarProvedorPorID(String id) {
        for (Provedor prov : provedores) {
            if (prov.getCedula().equals(id)) {
                System.out.println("Proveedor encontrado: " + "\n" +prov);
                System.out.println("---------------------------------------------");
                return;
            }
        }
        System.out.println("Proveedor no encontrado.");
    }

    public void buscarProvedorPorNombre(String nombre) {
        for (Provedor prov : provedores) {
            if (prov.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Proveedor encontrado: " + "\n" +prov);
                System.out.println("---------------------------------------------");
                return;
            }
        }
        System.out.println("Proveedor no encontrado.");
    }

    public Provedor buscarProveedorPorCedula(String cedula) {
        for (Provedor prov : provedores) {
            if (prov.getCedula().equals(cedula)) {
                return prov;
            }
        }
        return null;
    }

    public void actualizarProveedor(Provedor proveedorActualizado) {
        for (int i = 0; i < provedores.size(); i++) {
            if (provedores.get(i).getCedula().equals(proveedorActualizado.getCedula())) {
                provedores.set(i, proveedorActualizado);
                break;
            }
        }
    }
    public List<Producto> getProductosTemp() {
        return productosTemp;
    }
}