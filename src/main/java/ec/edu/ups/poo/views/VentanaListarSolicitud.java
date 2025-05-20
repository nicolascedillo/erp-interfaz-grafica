package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.DetalleSolicitud;
import ec.edu.ups.poo.models.Provedor;
import ec.edu.ups.poo.models.SolicitudCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListarSolicitud {

    public VentanaListarSolicitud(List<SolicitudCompra> solicitudes) {

        Frame frame = new Frame("Lista de Solicitudes");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label titulo = new Label("Listar Solicitudes");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        Panel panelSuperior = new Panel(new FlowLayout());
        panelSuperior.add(titulo);
        Button botonSalir = new  Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VentanaMenu ventanaMenu = new VentanaMenu();
            }
        });
        frame.add(panelSuperior, BorderLayout.NORTH);

        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.setSize(500, 350);
        panelPrincipal.setPreferredSize(new Dimension(500, solicitudes.size() * 400));
        for (SolicitudCompra solicitud : solicitudes) {
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700, 400));

            panel.add(new Label("Id:"));
            TextField txtId = new TextField(String.valueOf(solicitud.getId()));
            txtId.setEditable(false);
            panel.add(txtId);

            panel.add(new Label("Fecha:"));
            TextField txtFecha = new TextField(solicitud.getStringFecha());
            txtFecha.setEditable(false);
            panel.add(txtFecha);

            panel.add(new Label("Comentario:"));
            TextField txtComentario = new TextField(solicitud.getComentario());
            txtComentario.setEditable(false);
            panel.add(txtComentario);

            panel.add(new Label("Empleado Solicitante:"));
            TextField txtEmpleado = new TextField(String.valueOf(solicitud.getEmpleadoSolicitante().getNombre() + " " + solicitud.getEmpleadoSolicitante().getApellido()));
            txtEmpleado.setEditable(false);
            panel.add(txtEmpleado);

            panel.add(new Label("Estado de la solicitud:"));
            TextField txtEstado = new TextField(String.valueOf(solicitud.getEstado()));
            txtEstado.setEditable(false);
            panel.add(txtEstado);

            Label detalles = new Label("Detalles: ");
            detalles.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(detalles);
            panel.add(new Label("---------------------------------------------------------------------------------"));
            int cont = 1;
            for (DetalleSolicitud detalle: solicitud.getDetalles()) {

                panel.add(new Label(cont + " ---Id:"));
                //Atributos Detalles
                TextField txtIdDetalle = new TextField(String.valueOf(detalle.getId()));
                txtIdDetalle.setEditable(false);
                panel.add(txtIdDetalle);

                panel.add(new Label(cont + " ---Item:"));
                TextField txtItem = new TextField(String.valueOf(detalle.getItemProducto().getNombre()));
                txtItem.setEditable(false);
                panel.add(txtItem);

                panel.add(new Label(cont + " ---Cantidad:"));
                TextField txtCantidad = new TextField(String.valueOf(detalle.getCantidad()));
                txtCantidad.setEditable(false);
                panel.add(txtCantidad);

                panel.add(new Label(cont + " ---Observacion:"));
                TextField txtObservacion = new TextField(detalle.getObservacion());
                txtObservacion.setEditable(false);
                panel.add(txtObservacion);

                panel.add(new Label(cont + " ---Iva:"));
                TextField txtIvaDetalle = new TextField(String.valueOf(detalle.getIVAdetalle()));
                txtIvaDetalle.setEditable(false);
                panel.add(txtIvaDetalle);

                panel.add(new Label(cont + " ---SubTotal:"));
                TextField txtSubTotalDetalle = new TextField(String.valueOf(detalle.getSubTotalDetalle()));
                txtSubTotalDetalle.setEditable(false);
                panel.add(txtSubTotalDetalle);
                cont += 1;
            }

            panel.add(new Label("IVA:"));
            TextField txtIva = new TextField(String.valueOf(solicitud.getIva()));
            txtIva.setEditable(false);
            panel.add(txtIva);

            panel.add(new Label("SubTotal:"));
            TextField txtSubTotal = new TextField(String.valueOf(solicitud.getSubtotal()));
            txtSubTotal.setEditable(false);
            panel.add(txtSubTotal);

            panel.add(new Label("Total:"));
            TextField txtTotal = new TextField(String.valueOf(solicitud.getTotal()));
            txtTotal.setEditable(false);
            panel.add(txtTotal);
            panel.add(new Label("---------------------------------------------------------------------------------"));
            panel.add(new Label("---------------------------------------------------------------------------------"));

            panelPrincipal.add(panel);
        }

        scrollPane.add(panelPrincipal);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}