package ec.edu.ups.poo.models;

import ec.edu.ups.poo.models.Enums.Feriado;

public class ProductoConIva extends Producto {
    private Feriado festividad;

    public ProductoConIva(int id, String nombre, Double precioUnitario, String marca, Feriado festividad) {
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
        return   "\n\t\t\t\t\t\t\t\tProducto Con IVA { " +
                super.toString() +
                " || porcentaje del IVA = " + festividad.getPorcentaje() +
                " || impuesto =  " + calcularIVA() +
                " || motivo del porcentaje =  " + festividad.getFecha() + " ||"+
                "\n\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------";
    }

    @Override
    public double calcularIVA() {
        return getPrecioUnitario()*festividad.getPorcentaje() ;
    }
}
