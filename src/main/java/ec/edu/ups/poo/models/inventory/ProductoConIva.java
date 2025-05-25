package ec.edu.ups.poo.models.inventory;

import ec.edu.ups.poo.models.enums.Feriado;

public class ProductoConIva extends Producto {
    private Feriado festividad;

    public ProductoConIva(String id, String nombre, Double precioUnitario, String marca, Feriado festividad) {
        super(id, nombre, precioUnitario, marca);
        this.festividad = festividad;
    }

    public Feriado getFestividad() {
        return festividad;
    }

    public void setFestividad(Feriado festividad) {
        this.festividad = festividad;
    }

    @Override
    public String toString() {
        return   "Producto Con IVA { " +
                super.toString() +
                " || porcentaje del IVA = " + festividad.getPorcentaje() +
                " || impuesto =  " + calcularIVA() +
                " || motivo del porcentaje =  " + festividad.getFecha() + " ||"
                ;
    }

    @Override
    public double calcularIVA() {
        return getPrecioUnitario()*festividad.getPorcentaje() ;
    }
}
