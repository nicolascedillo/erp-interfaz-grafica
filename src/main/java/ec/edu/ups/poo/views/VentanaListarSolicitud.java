package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Provedor;
import ec.edu.ups.poo.models.SolicitudCompra;

import java.awt.*;
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

    public VentanaListarSolicitud(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        frame = new Frame("Lista de Proveedores");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label titulo = new Label("Listar Proveedores");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        ScrollPane scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.setSize(580, 350);
        panelPrincipal.setPreferredSize(new Dimension(560, solicitudes.size() * 160));
        for (SolicitudCompra solicitud : solicitudes) {
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700, 180));
            panel.add(new Label("Id:"));
            txtId = new TextField(String.valueOf(solicitud.getId()));
            txtId.setEditable(false);
            panel.add(txtId);
            panel.add(new Label("Fecha:"));
            txtFecha = new TextField(String.valueOf(solicitud.getFecha()));
            txtFecha.setEditable(false);
            panel.add(txtFecha);
            panel.add(new Label("Comentario:"));
            txtComentario = new TextField(solicitud.getComentario());
            txtComentario.setEditable(false);
            panel.add(txtComentario);
            panel.add(new Label("Empleado Solicitante:"));
            txtEmpleado = new TextField(String.valueOf(solicitud.getEmpleadoSolicitante()));
            txtEmpleado.setEditable(false);
            panel.add(txtEmpleado);
            panel.add(new Label("Estado de la solicitud:"));
            txtEstado = new TextField(String.valueOf(solicitud.getEstado()));
            txtEstado.setEditable(false);
            panel.add(txtEstado);
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
