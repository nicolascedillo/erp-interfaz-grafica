package ec.edu.ups.poo.models.Enums;

 import java.time.Month;

public enum Feriado {

    FERIADO_NAVIDAD(Month.DECEMBER, 25,0.08),
    NO_FERIADO(null,0,0.15),
    ANIO_NUEVO(Month.JANUARY, 1,0.1),
    FIESTAS_DE_CUENCA(Month.APRIL, 12,0.12);

    private Month mes;
    private int dia;
    private double porcentaje;

    Feriado(Month mes, int dia, double porcentaje) {
        this.mes = mes;
        this.dia = dia;
        this.porcentaje = porcentaje;
    }

    public int getDia() {
        return dia;
    }

    public Month getMes() {
        return mes;
    }

    public String getFecha(){

        if (this == NO_FERIADO) {
            return "Día normal";
        }
        return mes + " " + dia;
    }

    public double getPorcentaje(){return porcentaje;}

    public static Feriado obtenerFeriado(Month mes, int dia) {
        for (Feriado feriado : Feriado.values()) {
            if (feriado.getMes() != null && feriado.getMes().equals(mes) && feriado.getDia() == dia) {
                return feriado;
            }
        }
        return NO_FERIADO;
    }
}
