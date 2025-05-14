package ec.edu.ups.poo.controllers;

import ec.edu.ups.poo.models.Enums.Feriado;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoConIva;
import ec.edu.ups.poo.models.ProductoSinIva;
import ec.edu.ups.poo.models.Provedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControlProducto {
    private static List<Producto> productos;
    Scanner sc = new Scanner(System.in);
    ControlProvedor cP = new ControlProvedor();

    public ControlProducto() {
        productos = new ArrayList<>();

        for (Producto p:cP.getProductosTemp()){
            productos.add(p);
        }

    }

    public Producto getProductos(int n) {
        return productos.get(n-1);
    }

    public void agregarProducto() {
        System.out.print("Ingrese la cantidad de productos a registrar: ");
        int numeroProductos = sc.nextInt();

        for (int i = 0; i < numeroProductos; i++){

            System.out.println("\t\tIngrese los datos del producto " + (i+1) + ": ");
            System.out.print("Categoría ( Comida / Primera_necesidad / Agricola / Medicina / Escolar / Etc ): ");
            String categoria = sc.next();
            System.out.print("\t\t\tID: ");
            int id = sc.nextInt();
            System.out.print("\t\t\tNombre: ");
            String nombre = sc.next();
            System.out.print("\t\t\tPrecio Unitario (,): ");
            Double precioUnitario = sc.nextDouble();
            sc.nextLine();
            System.out.print("\t\t\tMarca: ");
            String marca = sc.nextLine();

            System.out.print("\t\t\tProveedor (Ingrese su cédula): ");
            String cedula = sc.next();

            switch (categoria){
                case "Comida" , "Primera_necesidad" , "Agricola" , "Medicina" , "Escolar":
                    productos.add(new ProductoSinIva( id, nombre, precioUnitario, marca, "El producto no agraba iva"));
                    break;
                default:
                    productos.add(new ProductoConIva(id, nombre, precioUnitario, marca, Feriado.NO_FERIADO));
                    break;
            }

            Provedor temp =  cP.buscarProveedorPorCedula(cedula);
            if (temp != null) {
                temp.addProducto(productos.getLast());
                cP.actualizarProveedor(temp);
            } else {
                System.out.println("Proveedor no encontrado, producto sin proveedor");
            }

            System.out.println("Producto registrado correctamente.");
            System.out.println("---------------------------------------------");
        }
    }

    public void printProductos() {
        System.out.println("Lista de productos: ");
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public void buscarProductoPorID(int id) {
        for (Producto prod : productos) {
            if (prod.getId() == id) {
                System.out.println("Producto encontrado: " + "\n" +prod);
                System.out.println("---------------------------------------------");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public void buscarProductoPorNombre(String nombre) {
        for (Producto prod : productos) {
            if (prod.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Producto encontrado: " + "\n" +prod);
                System.out.println("---------------------------------------------");
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }


}
