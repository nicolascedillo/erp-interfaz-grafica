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