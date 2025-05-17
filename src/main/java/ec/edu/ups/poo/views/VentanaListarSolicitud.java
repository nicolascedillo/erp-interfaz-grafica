package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.DetalleSolicitud;
import ec.edu.ups.poo.models.Provedor;
import ec.edu.ups.poo.models.SolicitudCompra;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListarSolicitud {
    private Frame frame;
    private TextField txtId;
    private TextField txtFecha;
    private TextField txtComentario;
    private TextField txtEmpleado;
    private TextField txtEstado;
    private TextField txtSubTotal;
    private TextField txtIva;
    private TextField txtTotal;
    private List<SolicitudCompra> solicitudes;
    //Atributos Detalles
    private TextField txtIdDetalle;
    private TextField txtItem;
    private TextField txtCantidad;
    private TextField txtObservacion;
    private TextField txtIvaDetalle;
    private TextField txtSubTotalDetalle;


    public VentanaListarSolicitud(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        frame = new Frame("Lista de Solicitudes");
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
        scrollPane.setSize(580, 350);
        panelPrincipal.setPreferredSize(new Dimension(560, solicitudes.size() * 400));
        for (SolicitudCompra solicitud : solicitudes) {
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700, 400));
            panel.add(new Label("Id:"));
            txtId = new TextField(String.valueOf(solicitud.getId()));
            txtId.setEditable(false);
            panel.add(txtId);
            panel.add(new Label("Fecha:"));
            txtFecha = new TextField(solicitud.getStringFecha());
            txtFecha.setEditable(false);
            panel.add(txtFecha);
            panel.add(new Label("Comentario:"));
            txtComentario = new TextField(solicitud.getComentario());
            txtComentario.setEditable(false);
            panel.add(txtComentario);
            panel.add(new Label("Empleado Solicitante:"));
            txtEmpleado = new TextField(String.valueOf(solicitud.getEmpleadoSolicitante().getNombre() + " "+ solicitud.getEmpleadoSolicitante().getApellido()));
            txtEmpleado.setEditable(false);
            panel.add(txtEmpleado);
            panel.add(new Label("Estado de la solicitud:"));
            txtEstado = new TextField(String.valueOf(solicitud.getEstado()));
            txtEstado.setEditable(false);
            panel.add(txtEstado);
            Label detalles = new Label("Detalles: ");
            detalles.setFont(new Font("Arial", Font.BOLD, 12));
            panel.add(detalles);
            panel.add(new Label("---------------------------------------------------------------------------------"));
            int cont = 1;
            for (DetalleSolicitud detalle: solicitud.getDetalles()) {
                panel.add(new Label(cont + " ---Id:"));
                txtIdDetalle = new TextField(String.valueOf(detalle.getId()));
                txtIdDetalle.setEditable(false);
                panel.add(txtIdDetalle);
                panel.add(new Label(cont + " ---Item:"));
                txtItem = new TextField(String.valueOf(detalle.getItemProducto().getNombre()));
                txtItem.setEditable(false);
                panel.add(txtItem);
                panel.add(new Label(cont + " ---Cantidad:"));
                txtCantidad = new TextField(String.valueOf(detalle.getCantidad()));
                txtCantidad.setEditable(false);
                panel.add(txtCantidad);
                panel.add(new Label(cont + " ---Observacion:"));
                txtObservacion = new TextField(detalle.getObservacion());
                txtObservacion.setEditable(false);
                panel.add(txtObservacion);
                panel.add(new Label(cont + " ---Iva:"));
                txtIvaDetalle = new TextField(String.valueOf(detalle.getIVAdetalle()));
                txtIvaDetalle.setEditable(false);
                panel.add(txtIvaDetalle);
                panel.add(new Label(cont + " ---SubTotal:"));
                txtSubTotalDetalle = new TextField(String.valueOf(detalle.getSubTotalDetalle()));
                txtSubTotalDetalle.setEditable(false);
                panel.add(txtSubTotalDetalle);
                cont += 1;
            }
            panel.add(new Label("IVA:"));
            txtIva = new TextField(String.valueOf(solicitud.getIva()));
            txtIva.setEditable(false);
            panel.add(txtIva);
            panel.add(new Label("SubTotal:"));
            txtSubTotal = new TextField(String.valueOf(solicitud.getSubtotal()));
            txtSubTotal.setEditable(false);
            panel.add(txtSubTotal);
            panel.add(new Label("Total:"));
            txtTotal = new TextField(String.valueOf(solicitud.getTotal()));
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