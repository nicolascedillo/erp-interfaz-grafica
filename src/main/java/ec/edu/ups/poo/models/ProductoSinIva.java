package ec.edu.ups.poo.models;

public class ProductoSinIva extends  Producto {
    private String motivo;

    public ProductoSinIva(int id, String nombre, Double precioUnitario, String marca, String motivo) {
        super(id, nombre, precioUnitario, marca);
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "\n\t\t\t\t\t\t\t\tProducto Sin IVA {" +
                super.toString() +
                " || porcantajeIVA= " + calcularIVA() +
                " || motivo='" + motivo + '\''+ " ||"+
                "\n\t\t\t\t\t\t----------------------------------------------------------------------------------------------------------";
    }

    public double calcularIVA() {
        return 0;
    }

}
