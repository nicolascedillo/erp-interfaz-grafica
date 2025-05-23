package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.DetalleSolicitud;
import ec.edu.ups.poo.models.SolicitudCompra;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBuscarSolicitudID {
    private List<SolicitudCompra> solicitudes;

    public VentanaBuscarSolicitudID(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        Frame frame = new Frame("Buscar Solicitud");
        frame.setSize(725, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Buscar Solicitud por ID");
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

        Panel panel = new Panel(new GridLayout(0, 2));
        panel.setPreferredSize(new Dimension(725,50));
        panel.add(new Label("Ingrese el ID de la solicitud a buscar:"));
        TextField txtId = new TextField(10);
        panel.add(txtId);

        Button botonBuscar = new Button("Buscar");
        Panel panelBuscar = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.add(botonBuscar);
        panel.add(panelBuscar);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                SolicitudCompra encontrado = buscarPorID(id);
                if (encontrado != null) {
                    Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                    panelEncontrado.setPreferredSize(new Dimension(500,400));

                    panelEncontrado.add(new Label("ID:"));
                    TextField txtID = new TextField(String.valueOf(encontrado.getId()));
                    txtID.setEditable(false);
                    panelEncontrado.add(txtID);

                    panelEncontrado.add(new Label("Fecha:"));
                    TextField txtFecha = new TextField(encontrado.getStringFecha());
                    txtFecha.setEditable(false);
                    panelEncontrado.add(txtFecha);

                    panelEncontrado.add(new Label("Comentario:"));
                    TextField txtComentario = new TextField(encontrado.getComentario() );
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
                    for (DetalleSolicitud detalle: encontrado.getDetalles()) {

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

                    panelPrincipal.removeAll();
                    panelPrincipal.add(panelEncontrado);
                    frame.revalidate();

                } else {

                    Label mensaje = new Label("No se encontró la solicitud con ID: " + id);
                    panelPrincipal.removeAll();
                    panelPrincipal.add(mensaje);
                    frame.revalidate();

                }
            }
        });

        panelPrincipal.add(panel);
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public SolicitudCompra buscarPorID(int id) {

        for (SolicitudCompra solicitud : solicitudes) {
            if(solicitud.getId() == id) {
                return solicitud;
            }
        }
        return null;
    }

}
