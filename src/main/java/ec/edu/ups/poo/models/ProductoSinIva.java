package ec.edu.ups.poo.models;

public class ProductoSinIva extends  Producto {
    private String motivo;

    public ProductoSinIva(String id, String nombre, Double precioUnitario, String marca, String motivo) {
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
        return "Producto Sin IVA {" +
                super.toString() +
                " || porcantajeIVA= " + calcularIVA() +
                " || motivo='" + motivo + '\''+ " ||";
    }

    public double calcularIVA() {
        return 0;
    }

}
