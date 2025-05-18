package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.DetalleSolicitud;
import ec.edu.ups.poo.models.SolicitudCompra;
import ec.edu.ups.poo.models.enums.EstadoSolicitud;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarEstadoSolicitud {
    private List<SolicitudCompra> solicitudes;

    public VentanaCambiarEstadoSolicitud(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        Frame frame = new Frame("Cambiar Estado de Solicitud");
        frame.setSize(750, 650);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Cambiar Estado de Solicitud");
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

        panelPrincipal.add(panel);
        frame.add(panelPrincipal, BorderLayout.CENTER);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = txtId.getText();
                int id = 0;
                boolean esNumero = true;
                for (int i = 0; i < idStr.length(); i++) {
                    if (!Character.isDigit(idStr.charAt(i))) {
                        esNumero = false;
                        break;
                    }
                }

                if (!esNumero || idStr.isEmpty()) {
                    mostrarMensajeTempError(frame, "Por favor ingrese un ID válido.");
                    return;
                } else {
                    id = Integer.parseInt(idStr);
                }

                SolicitudCompra encontrado = buscarPorID(id);
                panelPrincipal.removeAll();

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
                    TextField txtComentario = new TextField(encontrado.getComentario());
                    txtComentario.setEditable(false);
                    panelEncontrado.add(txtComentario);

                    panelEncontrado.add(new Label("Empleado Solicitante:"));
                    TextField txtEmpleado = new TextField(
                            encontrado.getEmpleadoSolicitante().getNombre() + " " +
                                    encontrado.getEmpleadoSolicitante().getApellido()
                    );
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

                    // Panel para cambiar estado
                    Panel panelCambio = new Panel(new GridLayout(0, 2));
                    panelCambio.setPreferredSize(new Dimension(500, 50));
                    panelCambio.add(new Label("Cambiar estado a: "));
                    Choice choiceEstado = new Choice();
                    choiceEstado.add("SOLICITADA");
                    choiceEstado.add("EN_REVISION");
                    choiceEstado.add("APROVADA");
                    choiceEstado.add("RECHAZADA");
                    panelCambio.add(choiceEstado);

                    Button btnCambiarEstado = new Button("Cambiar Estado");
                    panelCambio.add(new Label()); // Espacio vacío
                    panelCambio.add(btnCambiarEstado);

                    Label lblMensaje = new Label("");
                    lblMensaje.setFont(new Font("Arial", Font.BOLD, 13));
                    lblMensaje.setForeground(Color.BLUE);

                    btnCambiarEstado.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String nuevoEstado = choiceEstado.getSelectedItem();
                            encontrado.setEstado(EstadoSolicitud.valueOf(nuevoEstado.toUpperCase()));
                            txtEstado.setText(nuevoEstado);
                            mostrarMensajeTempEstado(frame, "Estado cambiado a: " + nuevoEstado);
                        }
                    });

                    panelPrincipal.add(panelEncontrado);
                    panelPrincipal.add(panelCambio);
                    panelPrincipal.add(lblMensaje);
                    frame.revalidate();

                } else {
                    mostrarMensajeTempError(frame, "No se encontró la solicitud con ID: " + txtId.getText());
                    frame.revalidate();
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent){
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void mostrarMensajeTempEstado(Frame frame, String mensaje){
        Dialog dialogo = new Dialog(frame, "Mensaje", true);
        Label label = new Label(mensaje, Label.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(label);
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(frame);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.setVisible(false);
                dialogo.dispose();
            }
        });
        dialogo.setVisible(true);
    }

    private void mostrarMensajeTempError(Frame frame, String mensaje){
        Dialog dialogo = new Dialog(frame, "Mensaje", true);
        Label label = new Label(mensaje, Label.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(label);
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(frame);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.setVisible(false);
                dialogo.dispose();
            }
        });
        dialogo.setVisible(true);
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