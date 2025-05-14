package ec.edu.ups.poo.controllers;

import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.models.Enums.EstadoSolicitud;
import ec.edu.ups.poo.models.Enums.Rol;
import ec.edu.ups.poo.models.Enums.Feriado;

import java.time.Month;
import java.util.*;

public class ControlSolicitud {
    ControlProducto cPro = new ControlProducto();
    Scanner sc = new Scanner(System.in);
    private static List<SolicitudCompra> solicitudes;

    public ControlSolicitud() {
        solicitudes = new ArrayList<>();

        Departamento departamento = new Departamento("Sistemas");

        Empleado empleado = new Empleado("0107456022","Nicolás","Cedillo","099 181 9287","Calle de la verbena","nicocedillo@gmail.com",departamento, Rol.EMPLEADOR,"nicolascedillo@gmail.com","nicolas1");

        ControlProducto cPro= new ControlProducto();

        GregorianCalendar fecha = new GregorianCalendar(2018, Calendar.JUNE, 15);
        SolicitudCompra solicitud1 = new SolicitudCompra(1,fecha,"Compras pendientes",empleado, EstadoSolicitud.SOLICITADA);
        solicitud1.addDetalle(1, cPro.getProductos(1), 5, "---Sin Observaciones---");
        solicitud1.addDetalle(2, cPro.getProductos(2), 5, "Es marina");

        SolicitudCompra solicitud2 = new SolicitudCompra(2, fecha, "Equipos de oficina", empleado, EstadoSolicitud.APROVADA);
        solicitud2.addDetalle(1, cPro.getProductos(3), 2, "Entrega urgente");
        solicitud2.addDetalle(2, cPro.getProductos(4), 1, "Modelo 2023");

        SolicitudCompra solicitud3 = new SolicitudCompra(3, fecha, "Artículos de cocina", empleado, EstadoSolicitud.EN_REVISION);
        solicitud3.addDetalle(1, cPro.getProductos(5), 10, "Uso institucional");
        solicitud3.addDetalle(2, cPro.getProductos(6), 15, "Preferencia marca reconocida");

        SolicitudCompra solicitud4 = new SolicitudCompra(4, fecha, "Mantenimiento informático", empleado, EstadoSolicitud.RECHAZADA);
        solicitud4.addDetalle(1, cPro.getProductos(7), 3, "Actualizar inventario");
        solicitud4.addDetalle(2, cPro.getProductos(8), 2, "Para reposición");

        SolicitudCompra solicitud5 = new SolicitudCompra(5, fecha, "Papelería", empleado, EstadoSolicitud.SOLICITADA);
        solicitud5.addDetalle(1, cPro.getProductos(1), 50, "Material de oficina");
        solicitud5.addDetalle(2, cPro.getProductos(5), 20, "Uso diario");

        solicitudes.add(solicitud1);
        solicitudes.add(solicitud2);
        solicitudes.add(solicitud3);
        solicitudes.add(solicitud4);
        solicitudes.add(solicitud5);

    }

    public SolicitudCompra getSolicitud(int n) {
        return solicitudes.get(n-1);
    }

    public void agregarSolicitud(Empleado empleadoLogueado) {


        System.out.print("Ingrese la cantidad de solicitudes a registrar: ");
        int numeroSolicitudes = sc.nextInt();

        for (int i = 0; i < numeroSolicitudes; i++){

            System.out.println("\t\tIngrese los datos de la solicitud " + (i+1) + ": ");
            System.out.print("Ingrese el ID de la solicitud: ");
            int idSolicitud = sc.nextInt();
            System.out.print("Ingrese el año: ");
            int año  = sc.nextInt();
            System.out.print("Ingrese el mes ( del 1 al 12 ): ");
            int mes = sc.nextInt() -1;
            System.out.print("Ingrese el día: ");
            int dia = sc.nextInt();
            GregorianCalendar fecha = new GregorianCalendar(año, mes, dia);
            sc.nextLine();
            System.out.print("Ingrese el comentario: ");
            String comentario = sc.nextLine();

            solicitudes.add( new SolicitudCompra(idSolicitud,fecha,comentario,empleadoLogueado,EstadoSolicitud.SOLICITADA));

            System.out.println("\nIngrese detalles de la solicitud: ");
            boolean continuar = true;
            while (continuar) {
                System.out.println("1: Ingrese un detalle");
                System.out.println("2: Dejar de ingresar detalles");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el ID del detalle: ");
                        int idDetalle = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Ingrese el ID del producto: ");
                        int idProducto = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Ingrese las observaciones: ");
                        String observaciones = sc.nextLine();

                        Producto productoSeleccionado = cPro.getProductos(idProducto);

                        SolicitudCompra solicitudActual = solicitudes.get(solicitudes.size() - 1);
                        GregorianCalendar fechaSolicitud = solicitudActual.getFecha();

                        if (productoSeleccionado instanceof ProductoConIva) {
                            ProductoConIva productoConIva = (ProductoConIva) productoSeleccionado;

                            int mesSolicitud = fechaSolicitud.get(Calendar.MONTH) + 1;
                            int diaSolicitud = fechaSolicitud.get(Calendar.DAY_OF_MONTH);

                            Month mesEnum = Month.of(mesSolicitud);
                            Feriado feriadoCorrespondiente = Feriado.obtenerFeriado(mesEnum, diaSolicitud);

                            productoConIva.setFestividad(feriadoCorrespondiente);
                        }

                        solicitudActual.addDetalle(idDetalle, productoSeleccionado, cantidad, observaciones);
                        break;

                    case 2:
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }

            System.out.println("Solicitud registrada correctamente.");
            System.out.println("---------------------------------------------");
        }
    }

    public void printSolicitudes() {
        System.out.println("Lista de solicitudes: ");
        for (SolicitudCompra solicitud : solicitudes) {
            System.out.println(solicitud);
        }
    }

    public void buscarSolicitudPorID(int id) {
        for (SolicitudCompra sol : solicitudes) {
            if (sol.getId() == id) {
                System.out.println("Solicitud encontrada: " + "\n" +sol);
                System.out.println("---------------------------------------------");
                return;
            }
        }
        System.out.println("Solicitud no encontrada.");
    }

    public void buscarSolicitudPorEstado(String estado) {
        String estadoBuscado = estado.toUpperCase();

        switch (estadoBuscado) {
            case "SOLICITADA":
            case "EN_REVISION":
            case "APROVADA":
            case "RECHAZADA":

                for (SolicitudCompra sol : solicitudes) {
                    if (sol.getEstado().toString().equalsIgnoreCase(estadoBuscado)) {
                        System.out.println("Solicitud encontrada: " + "\n" + sol);

                    }
                }
                break;
            default:
                System.out.println("No se encontraron solicitudes con el estado: " + estadoBuscado);
                break;
        }
    }

    public void cambiarEstadoSolicitud(int id, String nuevoEstado) {
        for (SolicitudCompra sol : solicitudes) {
            if (sol.getId() == id) {
                sol.setEstado(EstadoSolicitud.valueOf(nuevoEstado.toUpperCase()));
                System.out.println("Estado de la solicitud " + id + "actualizado a: " + nuevoEstado);
                return;
            }
        }
        System.out.println("Solicitud no encontrada.");
    }

}