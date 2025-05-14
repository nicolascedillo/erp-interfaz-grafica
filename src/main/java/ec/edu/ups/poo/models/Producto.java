package ec.edu.ups.poo.models;

import java.util.Objects;

public abstract class  Producto {

    private int id;
    private String nombre;
    private Double precioUnitario;
    private String marca;

    public Producto(int id, String nombre, Double precioUnitario, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMarca() {return marca;}

    public void setMarca(String marca) {this.marca = marca;}



    @Override
    public String toString() {
        return "\n\t\t\t\t\t\t\t\t|| id=" + id +
                " || nombre='" + nombre + '\'' +
                " || precioUnitario=" + precioUnitario +
                " || marca='" + marca + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public abstract double calcularIVA();
}
