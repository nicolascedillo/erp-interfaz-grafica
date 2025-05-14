package ec.edu.ups.poo.models;

import java.util.ArrayList;
import java.util.List;

public class Provedor extends Persona{

    private List<Producto> productos;

    public Provedor(String cedula, String nombre, String apellido, String telefono, String direccion, String correo) {
        super(cedula, nombre, apellido, telefono, direccion, correo);
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    @Override
    public String toString() {
        return "Provedor{" +
                super.toString() +
                "\n\t\t\t\tproductos=" + getProductos() +
                '}';
    }
}
