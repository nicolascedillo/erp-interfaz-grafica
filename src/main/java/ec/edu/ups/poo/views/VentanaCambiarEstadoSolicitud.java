package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.purchases.DetalleSolicitud;
import ec.edu.ups.poo.models.purchases.SolicitudCompra;
import ec.edu.ups.poo.models.enums.EstadoSolicitud;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarEstadoSolicitud {
    private List<SolicitudCompra> solicitudes;
    private Frame frame;

    public VentanaCambiarEstadoSolicitud(List<SolicitudCompra> solicitudes) {
        this.solicitudes = solicitudes;

        frame = new Frame("Cambiar Estado de Solicitud");
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
        panel.setPreferredSize(new Dimension(725, 50));
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
                String id = txtId.getText().trim();

                if (id.isEmpty()) {
                    mostrarMensajeTempAmarillo(frame,"Por favor ingrese un ID válido.");
                    return;
                }

                SolicitudCompra encontrado = buscarPorID(id);
                panelPrincipal.removeAll();

                if (encontrado != null) {
                    Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                    panelEncontrado.setPreferredSize(new Dimension(500, 400));

                    panelEncontrado.add(new Label("ID:"));
                    TextField txtID = new TextField(encontrado.getId());
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
                    panelCambio.add(new Label());
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
                            switch (nuevoEstado) {
                                case "SOLICITADA":
                                    mostrarMensajeTempAzul(frame, "Estado cambiado a: " + nuevoEstado);
                                    break;
                                case "EN_REVISION":
                                    mostrarMensajeTempAmarillo(frame, "Estado cambiado a: " + nuevoEstado);
                                    break;
                                case "APROVADA":
                                    mostrarMensajeTempVerde(frame, "Estado cambiado a: " + nuevoEstado);
                                    break;
                                case "RECHAZADA":
                                    mostrarMensajeTempRojo(frame, "Estado cambiado a: " + nuevoEstado);
                                    break;
                            }
                        }
                    });

                    panelPrincipal.add(panelEncontrado);
                    panelPrincipal.add(panelCambio);
                    panelPrincipal.add(lblMensaje);
                    frame.revalidate();

                } else {
                    mostrarMensajeTempRojo(frame,"No se encontró la solicitud con ID: " + id);
                    frame.revalidate();
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    private void mostrarMensajeTempVerde(Frame frame, String mensaje) {
        Dialog dialogo = new Dialog(frame, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(frame);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(22, 196, 127));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Label lblTitulo = new Label("¡ ATENCION !");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);

        Label label = new Label(mensaje, Label.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(label);

        Panel panelMensaje = new Panel();
        panelMensaje.setBackground(Color.WHITE);
        panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMensaje.add(lblMensaje);

        Panel panelBoton = new Panel();
        panelBoton.setBackground(Color.WHITE);
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPreferredSize(new Dimension(100, 35));
        btnAceptar.addActionListener(e -> dialogo.dispose());
        panelBoton.add(btnAceptar);

        dialogo.add(panelTitulo, BorderLayout.NORTH);
        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    private void mostrarMensajeTempAmarillo(Frame frame, String mensaje) {
        Dialog dialogo = new Dialog(frame, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(frame);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(255, 233, 154));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Label lblTitulo = new Label("¡ ATENCION !");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitulo.add(lblTitulo);

        Panel panelMensaje = new Panel();
        panelMensaje.setBackground(Color.WHITE);
        panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMensaje.add(lblMensaje);

        Panel panelBoton = new Panel();
        panelBoton.setBackground(Color.WHITE);
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPreferredSize(new Dimension(100, 35));
        btnAceptar.addActionListener(e -> dialogo.dispose());
        panelBoton.add(btnAceptar);

        dialogo.add(panelTitulo, BorderLayout.NORTH);
        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    private void mostrarMensajeTempRojo(Frame frame, String mensaje) {
        Dialog dialogo = new Dialog(frame, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(frame);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(220, 53, 69));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Label lblTitulo = new Label("¡ ATENCION !");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        Panel panelMensaje = new Panel();
        panelMensaje.setBackground(Color.WHITE);
        panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMensaje.add(lblMensaje);

        Panel panelBoton = new Panel();
        panelBoton.setBackground(Color.WHITE);
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPreferredSize(new Dimension(100, 35));
        btnAceptar.addActionListener(e -> dialogo.dispose());
        panelBoton.add(btnAceptar);

        dialogo.add(panelTitulo, BorderLayout.NORTH);
        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    private void mostrarMensajeTempAzul(Frame frame, String mensaje) {
        Dialog dialogo = new Dialog(frame, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(frame);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(84, 9, 218));
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        Label lblTitulo = new Label("¡ ATENCION !");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        Panel panelMensaje = new Panel();
        panelMensaje.setBackground(Color.WHITE);
        panelMensaje.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        Label lblMensaje = new Label(mensaje);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelMensaje.add(lblMensaje);

        Panel panelBoton = new Panel();
        panelBoton.setBackground(Color.WHITE);
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setPreferredSize(new Dimension(100, 35));
        btnAceptar.addActionListener(e -> dialogo.dispose());
        panelBoton.add(btnAceptar);

        dialogo.add(panelTitulo, BorderLayout.NORTH);
        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    public SolicitudCompra buscarPorID(String id) {
        for (SolicitudCompra solicitud : solicitudes) {
            if (solicitud.getId().equals(id)) {
                return solicitud;
            }
        }
        return null;
    }
}