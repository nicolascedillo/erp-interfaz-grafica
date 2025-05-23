package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.DetalleSolicitud;
import ec.edu.ups.poo.models.enums.EstadoSolicitud;
import ec.edu.ups.poo.models.SolicitudCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaBuscarSolicitudEstado {
    private List<SolicitudCompra> solicitudes;

    public VentanaBuscarSolicitudEstado(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        Frame frame = new Frame("Buscar Solicitud");
        frame.setSize(725, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Buscar Solicitud por Estado");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        Panel panelSuperior = new Panel(new FlowLayout());
        panelSuperior.add(titulo);

        Button botonSalir = new Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VentanaMenu ventanaMenu = new VentanaMenu();
            }
        });
        frame.add(panelSuperior, BorderLayout.NORTH);

        Panel panelCentral = new Panel(new GridLayout(2, 2,10,10));
        panelCentral.setPreferredSize(new Dimension(600,50));
        panelCentral.add(new Label("Ingrese el Estado de la solicitud a buscar:"));

        Choice listaEstado = new Choice();
        listaEstado.addItem("SOLICITADA");
        listaEstado.addItem("APROVADA");
        listaEstado.addItem("EN_REVISION");
        listaEstado.addItem("RECHAZADA");
        panelCentral.add(listaEstado);

        Button botonBuscar = new Button("Buscar");
        Panel panelBuscar = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.add(botonBuscar);
        panelCentral.add(panelBuscar);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoSolicitud estado = EstadoSolicitud.valueOf(listaEstado.getSelectedItem());
                List<SolicitudCompra> encontrados = buscarPorEstado(estado);

                if (!encontrados.isEmpty()) {
                    Panel panelResultados = new Panel(new GridLayout(encontrados.size(), 1));

                    for (SolicitudCompra encontrado : encontrados) {
                        Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                        panelEncontrado.setPreferredSize(new Dimension(400, 400));

                        panelEncontrado.add(new Label("ID:"));
                        TextField txtID = new TextField(String.valueOf(encontrado.getId()));
                        txtID.setEditable(false);
                        panelEncontrado.add(txtID);

                        panelEncontrado.add(new Label("Fecha:"));
                        TextField txtFecha = new TextField(encontrado.getStringFecha());
                        txtFecha.setEditable(false);
                        panelEncontrado.add(txtFecha);

                        panelEncontrado.add(new Label("Comentario:"));
                        TextField txtComentario = new TextField(encontrado.getComentario());
                        txtComentario.setEditable(false);
                        panelEncontrado.add(txtComentario);

                        panelEncontrado.add(new Label("Empleado Solicitante:"));
                        TextField txtEmpleado = new TextField(encontrado.getEmpleadoSolicitante().getNombre() + " " + encontrado.getEmpleadoSolicitante().getApellido());
                        txtEmpleado.setEditable(false);
                        panelEncontrado.add(txtEmpleado);

                        panelEncontrado.add(new Label("Estado de la solicitud:"));
                        TextField txtEstado = new TextField(String.valueOf(encontrado.getEstado()));
                        txtEstado.setEditable(false);
                        panelEncontrado.add(txtEstado);

                        Label detalles = new Label("Detalles: ");
                        detalles.setFont(new Font("Arial", Font.BOLD, 12));
                        panelEncontrado.add(detalles);
                        panelEncontrado.add(new Label("---------------------------------------------------------------------------------"));
                        int cont = 1;
                        for (DetalleSolicitud detalle : encontrado.getDetalles()) {

                            panelEncontrado.add(new Label(cont + " ---Id:"));
                            TextField txtIdDetalle = new TextField(String.valueOf(detalle.getId()));
                            txtIdDetalle.setEditable(false);
                            panelEncontrado.add(txtIdDetalle);

                            panelEncontrado.add(new Label(cont + " ---Item:"));
                            TextField txtItem = new TextField(String.valueOf(detalle.getItemProducto().getNombre()));
                            txtItem.setEditable(false);
                            panelEncontrado.add(txtItem);

                            panelEncontrado.add(new Label(cont + " ---Cantidad:"));
                            TextField txtCantidad = new TextField(String.valueOf(detalle.getCantidad()));
                            txtCantidad.setEditable(false);
                            panelEncontrado.add(txtCantidad);

                            panelEncontrado.add(new Label(cont + " ---Observacion:"));
                            TextField txtObservacion = new TextField(detalle.getObservacion());
                            txtObservacion.setEditable(false);
                            panelEncontrado.add(txtObservacion);

                            panelEncontrado.add(new Label(cont + " ---Iva:"));
                            TextField txtIvaDetalle = new TextField(String.valueOf(detalle.getIVAdetalle()));
                            txtIvaDetalle.setEditable(false);
                            panelEncontrado.add(txtIvaDetalle);

                            panelEncontrado.add(new Label(cont + " ---SubTotal:"));
                            TextField txtSubTotalDetalle = new TextField(String.valueOf(detalle.getSubTotalDetalle()));
                            txtSubTotalDetalle.setEditable(false);
                            panelEncontrado.add(txtSubTotalDetalle);

                            cont += 1;
                        }

                        panelEncontrado.add(new Label("IVA:"));
                        TextField txtIva = new TextField(String.valueOf(encontrado.getIva()));
                        txtIva.setEditable(false);
                        panelEncontrado.add(txtIva);

                        panelEncontrado.add(new Label("SubTotal:"));
                        TextField txtSubTotal = new TextField(String.valueOf(encontrado.getSubtotal()));
                        txtSubTotal.setEditable(false);
                        panelEncontrado.add(txtSubTotal);

                        panelEncontrado.add(new Label("Total:"));
                        TextField txtTotal = new TextField(String.valueOf(encontrado.getTotal()));
                        txtTotal.setEditable(false);
                        panelEncontrado.add(txtTotal);

                        panelEncontrado.add(new Label("---------------------------------------------------------------------------------"));
                        panelEncontrado.add(new Label("---------------------------------------------------------------------------------"));

                        panelResultados.add(panelEncontrado);
                    }


                    ScrollPane scroll = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
                    scroll.setPreferredSize(new Dimension(700, 500));
                    scroll.add(panelResultados);
                    panelPrincipal.removeAll();

                    panelPrincipal.add(scroll);


                } else {

                    Label mensaje = new Label("No se encontró la solicitud con Estado: " + estado);
                    panelPrincipal.removeAll();
                    panelPrincipal.add(mensaje);


                }

                frame.revalidate();

            }
        });

        panelPrincipal.add(panelCentral);
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public List<SolicitudCompra> buscarPorEstado(EstadoSolicitud estado) {
        List<SolicitudCompra> resultados = new ArrayList<>();
        for (SolicitudCompra solicitud : solicitudes) {
            if (solicitud.getEstado() == estado) {
                resultados.add(solicitud);
            }
        }
        return resultados;
    }
}