package ec.edu.ups.poo.models;

import ec.edu.ups.poo.models.enums.*;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Datos {
    private static List<Provedor> provedores;
    private static List<SolicitudCompra> solicitudes;
    private static List<Producto> productos;
    private Empleado nicolas;
    private Empleado mateo;

    private static Empleado empleadoLogueado;

    public Datos(){

        provedores = new ArrayList<>();
        productos = new ArrayList<>();
        solicitudes = new ArrayList<>();

        ProductoConIva producto1 = new ProductoConIva("PD-001", "Computadora",  500.0,"ASUS", Feriado.NO_FERIADO);
        ProductoSinIva producto2 = new ProductoSinIva("PD-002","Sal",2.0,"Mi Sal Querida", "El producto no agraba iva");
        ProductoConIva producto3 = new ProductoConIva("PD-003", "Smartphone", 300.0, "Samsung", Feriado.ANIO_NUEVO);
        ProductoConIva producto4 = new ProductoConIva("PD-004", "Televisor", 800.0, "LG", Feriado.FERIADO_NAVIDAD);
        ProductoSinIva producto5 = new ProductoSinIva("PD-005", "Agua mineral", 1.5, "Agua Pura", "Productoexento de IVA");
        ProductoSinIva producto6 = new ProductoSinIva("PD-006", "Arroz", 1.2, "Grano de Oro", "No grava IVA según ley");
        ProductoConIva producto7 = new ProductoConIva("PD-007", "Impresora", 150.0, "HP", Feriado.FIESTAS_DE_CUENCA);
        ProductoSinIva producto8 = new ProductoSinIva("PD-008", "Medicamento", 10.0, "SaludTotal", "Medicamento libre de IVA");


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

        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);
        productos.add(producto5);
        productos.add(producto6);
        productos.add(producto7);
        productos.add(producto8);

        Departamento departamento = new Departamento("Sistemas");

        mateo = new Empleado("010562892", "Mateo", "Miller", "096 294 2726", "Av Guapondelig", "mateomiller5@gmail.com", departamento, Rol.ADMINISTRATIVO, "MJMMiller", "miller16mateo");
        nicolas = new Empleado("0107456022","Nicolás","Cedillo","099 181 9287","Calle de la verbena","nicocedillo@gmail.com",departamento, Rol.EMPLEADOR,"picola","nicolas1");

        GregorianCalendar fecha = new GregorianCalendar(2018, Calendar.JUNE, 15);
        SolicitudCompra solicitud1 = new SolicitudCompra("SC-001",fecha,"Compras pendientes", nicolas, EstadoSolicitud.SOLICITADA);
        solicitud1.addDetalle("DS-001", producto1, 5, "---Sin Observaciones---");
        solicitud1.addDetalle("DS-002", producto2, 5, "Es marina");

        SolicitudCompra solicitud2 = new SolicitudCompra("SC-002", fecha, "Equipos de oficina", nicolas, EstadoSolicitud.APROVADA);
        solicitud2.addDetalle("DS-001", producto3, 2, "Entrega urgente");
        solicitud2.addDetalle("DS-002", producto4, 1, "Modelo 2023");

        SolicitudCompra solicitud3 = new SolicitudCompra("SC-003", fecha, "Artículos de cocina", nicolas, EstadoSolicitud.EN_REVISION);
        solicitud3.addDetalle("DS-001", producto5, 10, "Uso institucional");
        solicitud3.addDetalle("DS-002", producto6, 15, "Preferencia marca reconocida");

        SolicitudCompra solicitud4 = new SolicitudCompra("SC-004", fecha, "Mantenimiento informático", nicolas, EstadoSolicitud.RECHAZADA);
        solicitud4.addDetalle("DS-001", producto7, 3, "Actualizar inventario");
        solicitud4.addDetalle("DS-002", producto8, 2, "Para reposición");

        SolicitudCompra solicitud5 = new SolicitudCompra("SC-005", fecha, "Papelería", nicolas, EstadoSolicitud.SOLICITADA);
        solicitud5.addDetalle("DS-001", producto1, 50, "Material de oficina");
        solicitud5.addDetalle("DS-002", producto7, 20, "Uso diario");

        solicitudes.add(solicitud1);
        solicitudes.add(solicitud2);
        solicitudes.add(solicitud3);
        solicitudes.add(solicitud4);
        solicitudes.add(solicitud5);
    }

    public static void addProvedor(Provedor provedor) {
        provedores.add(provedor);
    }
    public static void addSolicitud(SolicitudCompra solicitud) {
        solicitudes.add(solicitud);
    }
    public static void addProducto(Producto producto) {
        productos.add(producto);
    }

    public static List<Provedor> getProvedores() {
        return provedores;
    }

    public static List<SolicitudCompra> getSolicitudes() {
        return solicitudes;
    }

    public static List<Producto> getProductos() {
        return productos;
    }

    public static Empleado getNicolas() {
        return new Datos().nicolas;
    }
    public static Empleado getMateo() {
        return new Datos().mateo;
    }

    public static Empleado getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public static void setEmpleadoLogueado(Empleado empleado) {
        empleadoLogueado = empleado;
    }

}
