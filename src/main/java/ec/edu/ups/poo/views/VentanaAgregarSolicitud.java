package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.models.enums.EstadoSolicitud;

import java.awt.*;
import java.util.GregorianCalendar;
import java.util.List;

public class VentanaAgregarSolicitud extends Frame {

    private List<Producto> productos;
    private static List<SolicitudCompra> solicitudes;

    private TextField txtEmpleado, txtIDSol, txtComentario, txtIDDetalle, txtCantidad, txtObservacion;
    private Choice choiceProductos, choiceDia, choiceMes, choiceAnio;

    public VentanaAgregarSolicitud(List<SolicitudCompra> solicitudes, List<Producto> productos, Empleado empleadoLogueado) {
        this.productos = productos;
        this.solicitudes = solicitudes;

        setTitle("Agregar Solicitud");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        Label titulo = new Label("Registrar Solicitud", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(titulo);

        Button botonSalir = new Button("Salir");
        botonSalir.addActionListener(e -> {
            dispose();
            new VentanaMenu();
        });
        panelSuperior.add(botonSalir);
        panelSuperior.setPreferredSize(new Dimension(750, 50));
        add(panelSuperior);

        Panel panelForm = new Panel(new GridLayout(7, 2, 5, 5));
        panelForm.setPreferredSize(new Dimension(700, 200));

        panelForm.add(new Label("Empleado solicitante:"));
        txtEmpleado = new TextField(empleadoLogueado.getNombre() + " " + empleadoLogueado.getApellido());
        txtEmpleado.setEditable(false);
        panelForm.add(txtEmpleado);

        panelForm.add(new Label("ID Solicitud:"));
        txtIDSol = new TextField();
        panelForm.add(txtIDSol);

        panelForm.add(new Label("Día:"));
        choiceDia = new Choice();
        choiceDia.add("");
        for (int i = 1; i <= 31; i++) {
            choiceDia.add(String.valueOf(i));
        }
        panelForm.add(choiceDia);

        panelForm.add(new Label("Mes:"));
        choiceMes = new Choice();
        choiceMes.add("");
        for (int i = 1; i <= 12; i++) {
            choiceMes.add(String.valueOf(i));
        }
        panelForm.add(choiceMes);

        panelForm.add(new Label("Año:"));
        choiceAnio = new Choice();
        choiceAnio.add("");
        choiceAnio.add("2024");
        choiceAnio.add("2025");
        choiceAnio.add("2026");
        panelForm.add(choiceAnio);

        panelForm.add(new Label("Comentario:"));
        txtComentario = new TextField();
        panelForm.add(txtComentario);

        panelForm.add(new Label("--------------------------------------------------------------------------------------"));
        panelForm.add(new Label("--------------------------------------------------------------------------------------"));

        add(panelForm);

        Panel panelDetalle = new Panel(new GridLayout(2, 4, 5, 0));
        panelDetalle.setPreferredSize(new Dimension(700, 65));

        panelDetalle.add(new Label("ID", Label.CENTER));
        panelDetalle.add(new Label("Productos", Label.CENTER));
        panelDetalle.add(new Label("Cantidad", Label.CENTER));
        panelDetalle.add(new Label("Observación", Label.CENTER));

        Panel panelID = new Panel(new FlowLayout(FlowLayout.CENTER));
        txtIDDetalle = new TextField(10);
        panelID.add(txtIDDetalle);
        panelDetalle.add(panelID);

        Panel panelProductos = new Panel(new FlowLayout(FlowLayout.CENTER));
        choiceProductos = new Choice();
        choiceProductos.add("");
        for (Producto producto : productos) {
            choiceProductos.add(producto.getNombre());
        }
        panelProductos.add(choiceProductos);
        panelDetalle.add(panelProductos);

        Panel panelCantidad = new Panel(new FlowLayout(FlowLayout.CENTER));
        txtCantidad = new TextField(10);
        panelCantidad.add(txtCantidad);
        panelDetalle.add(panelCantidad);

        Panel panelObservacion = new Panel(new FlowLayout(FlowLayout.CENTER));
        txtObservacion = new TextField(10);
        panelObservacion.add(txtObservacion);
        panelDetalle.add(panelObservacion);

        add(panelDetalle);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setPreferredSize(new Dimension(700, 35));

        Button btnGuardarDetalle = new Button("Guardar Detalle");
        Button btnNuevoDetalle = new Button("Nuevo Detalle");
        Button btnGuardarSolicitud = new Button("Guardar Solicitud");

        btnGuardarDetalle.setPreferredSize(new Dimension(140, 30));
        btnNuevoDetalle.setPreferredSize(new Dimension(140, 30));
        btnGuardarSolicitud.setPreferredSize(new Dimension(140, 30));

        panelBotones.add(btnGuardarDetalle);
        panelBotones.add(btnNuevoDetalle);
        panelBotones.add(btnGuardarSolicitud);

        add(panelBotones);

        btnGuardarDetalle.addActionListener(e -> {
            if (txtIDDetalle.getText().isEmpty() || choiceProductos.getSelectedIndex() == 0
                    || txtCantidad.getText().isEmpty() || txtObservacion.getText().isEmpty()) {
                mostrarMensajeTemp("Todos los campos son obligatorios");
                return;
            }
            mostrarMensajeTemp("Detalle guardado exitosamente");
            choiceProductos.setEnabled(false);
            txtIDDetalle.setEnabled(false);
            txtObservacion.setEnabled(false);
            txtCantidad.setEnabled(false);
        });

        btnNuevoDetalle.addActionListener(e -> {
            limpiarCampos();
            choiceProductos.select(0);
            choiceProductos.setEnabled(true);
            txtIDDetalle.setEnabled(true);
            txtObservacion.setEnabled(true);
            txtCantidad.setEnabled(true);
        });

        btnGuardarSolicitud.addActionListener(e -> {
            if (txtIDSol.getText().isEmpty() || choiceDia.getSelectedIndex() == 0 || choiceMes.getSelectedIndex() == 0
                    || choiceAnio.getSelectedIndex() == 0 || txtIDDetalle.getText().isEmpty()
                    || choiceProductos.getSelectedIndex() == 0 || txtCantidad.getText().isEmpty()
                    || txtObservacion.getText().isEmpty()) {
                mostrarMensajeTemp("Todos los campos son obligatorios");
                return;
            }

            String idSolicitud = txtIDSol.getText();
            String comentario = txtComentario.getText();
            Empleado empleado = empleadoLogueado;

            int anio = Integer.parseInt(choiceAnio.getSelectedItem());
            int mes = Integer.parseInt(choiceMes.getSelectedItem()) - 1;
            int dia = Integer.parseInt(choiceDia.getSelectedItem());

            GregorianCalendar fecha = new GregorianCalendar(anio, mes, dia);

            solicitudes.add(new SolicitudCompra(idSolicitud, fecha, comentario, empleado, EstadoSolicitud.SOLICITADA));
            mostrarMensajeTemp("Solicitud guardada exitosamente");
        });

        setVisible(true);
    }

    private void limpiarCampos() {
        txtIDDetalle.setText("");
        txtObservacion.setText("");
        txtCantidad.setText("");
    }

    private void mostrarMensajeTemp(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setSize(350, 120);
        dialogo.setLocationRelativeTo(this);

        Panel panelMensaje = new Panel();
        panelMensaje.add(new Label(mensaje, Label.CENTER));

        Panel panelBoton = new Panel();
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.addActionListener(e -> dialogo.dispose());
        panelBoton.add(btnAceptar);

        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}