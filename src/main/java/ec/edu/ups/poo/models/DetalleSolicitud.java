package ec.edu.ups.poo.models;

import ec.edu.ups.poo.models.Interface.Calculable;

import java.util.Objects;

public class DetalleSolicitud implements Calculable {

    private int id;
    private Producto itemProducto;
    private int cantidad;
    private String observacion;
    private Double IVAdetalle;
    private Double SubTotalDetalle;

    public DetalleSolicitud(int id, Producto itemProducto, int cantidad, String observacion) {
        this.id = id;
        this.itemProducto = itemProducto;
        this.cantidad = cantidad;
        this.observacion = observacion;
        calcularSubTotal();
        calcularIva();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getItemProducto() {
        return itemProducto;
    }

    public void setItemProducto(Producto itemProducto) {
        this.itemProducto = itemProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getIVAdetalle() {
        return IVAdetalle;
    }

    public void setIVAdetalle(Double IVAdetalle) {
        this.IVAdetalle = IVAdetalle;
    }

    public Double getSubTotalDetalle() {
        return SubTotalDetalle;
    }

    public void setSubTotalDetalle(Double subTotalDetalle) {
        SubTotalDetalle = subTotalDetalle;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\tDetalleSolicitud{" +
                "\n\t\t\t\t\t\t|| id=" + id +
                "\t|| cantidad=" + cantidad +
                " || observacion='" + observacion + '\'' +
                " || IVA detalle=" + IVAdetalle +
                " || SubTotal=" + SubTotalDetalle + " ||" +
                "\n\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------"+
                "\n\t\t\t\t\t\t|| Producto=" + itemProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DetalleSolicitud that = (DetalleSolicitud) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public void calcularSubTotal(){
        setSubTotalDetalle(itemProducto.getPrecioUnitario()*cantidad);
    }
    public void calcularIva(){
        setIVAdetalle(itemProducto.calcularIVA()*cantidad);
    }
}
