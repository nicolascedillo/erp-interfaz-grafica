package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.entities.Empleado;
import ec.edu.ups.poo.models.enums.EstadoSolicitud;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;
import java.time.Month;
import ec.edu.ups.poo.models.enums.Feriado;
import ec.edu.ups.poo.data.Datos;
import ec.edu.ups.poo.models.inventory.Producto;
import ec.edu.ups.poo.models.inventory.ProductoConIva;
import ec.edu.ups.poo.models.purchases.SolicitudCompra;

public class VentanaAgregarSolicitud extends Frame {

    private List<Producto> productos;
    private static List<SolicitudCompra> solicitudes;

    private TextField txtEmpleado, txtIDSol, txtComentario, txtIDDetalle, txtCantidad, txtObservacion;
    private Choice choiceProductos, choiceDia, choiceMes, choiceAnio;
    private boolean generada;
    private int contadorDetalles = 0;

    public VentanaAgregarSolicitud(List<SolicitudCompra> solicitudes, List<Producto> productos, Empleado empleadoLogueado, String id) {
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
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaMenu();
            }
        });

        panelSuperior.add(botonSalir);
        panelSuperior.setPreferredSize(new Dimension(750, 50));
        add(panelSuperior);

        Panel panelForm = new Panel(new GridLayout(8, 2, 5, 5));
        panelForm.setPreferredSize(new Dimension(700, 200));

        panelForm.add(new Label("Empleado solicitante:"));
        txtEmpleado = new TextField(empleadoLogueado.getNombre() + " " + empleadoLogueado.getApellido());
        txtEmpleado.setEditable(false);
        panelForm.add(txtEmpleado);

        panelForm.add(new Label("ID Solicitud:"));
        txtIDSol = new TextField(id);
        panelForm.add(txtIDSol);
        txtIDSol.setEditable(false);

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

        panelForm.add(new Label("Detalles de la Solicitud",Font.BOLD));
        panelForm.add(new Label(""));

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
        txtIDDetalle.setEditable(false);
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
        Button btnNuevaSolicitud = new Button("Nueva Solicitud");
        generada = false;
        contadorDetalles = 0;

        btnGuardarDetalle.setEnabled(false);
        btnNuevoDetalle.setEnabled(false);
        btnNuevaSolicitud.setEnabled(false);

        deshabilitarCamposDetalle();

        btnGuardarDetalle.setPreferredSize(new Dimension(140, 30));
        btnNuevoDetalle.setPreferredSize(new Dimension(140, 30));
        btnGuardarSolicitud.setPreferredSize(new Dimension(140, 30));
        btnNuevaSolicitud.setPreferredSize(new Dimension(140, 30));

        panelBotones.add(btnGuardarDetalle);
        panelBotones.add(btnNuevoDetalle);
        panelBotones.add(btnGuardarSolicitud);
        panelBotones.add(btnNuevaSolicitud);

        add(panelBotones);

        btnGuardarDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (txtIDDetalle.getText().isEmpty() || choiceProductos.getSelectedIndex() == 0
                        || txtCantidad.getText().isEmpty() || txtObservacion.getText().isEmpty()) {
                    mostrarMensajeTempAmarillo("Todos los campos son obligatorios");
                    return;
                }
                Producto producto = productos.get(choiceProductos.getSelectedIndex() - 1);
                GregorianCalendar fechaSolicitud = solicitudes.getLast().getFecha();
                if (producto instanceof ProductoConIva) {
                    ProductoConIva productoConIva = (ProductoConIva) producto;

                    int mesSolicitud = fechaSolicitud.get(Calendar.MONTH) + 1;
                    int diaSolicitud = fechaSolicitud.get(Calendar.DAY_OF_MONTH);

                    Month mesEnum = Month.of(mesSolicitud);
                    Feriado feriadoCorrespondiente = Feriado.obtenerFeriado(mesEnum, diaSolicitud);

                    productoConIva.setFestividad(feriadoCorrespondiente);
                }

                String cantidadStr = txtCantidad.getText().trim();

                if (!cantidadStr.matches("\\d+")) {
                    mostrarMensajeTempAmarillo("La cantidad debe ser un número entero");
                    return;
                }

                solicitudes.getLast().addDetalle(txtIDDetalle.getText(),producto,
                        Integer.parseInt(txtCantidad.getText()), txtObservacion.getText());

                mostrarMensajeTempVerde("Detalle guardado exitosamente");
                deshabilitarCamposDetalle();
                btnNuevaSolicitud.setEnabled(true);
                btnGuardarDetalle.setEnabled(false);
                btnNuevoDetalle.setEnabled(true);
            }
        });

        btnNuevaSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposDetalle();
                txtIDDetalle.setText("");
                limpiarCamposSolicitud();
                deshabilitarCamposDetalle();
                btnNuevaSolicitud.setEnabled(false);
                btnNuevoDetalle.setEnabled(false);
                btnGuardarDetalle.setEnabled(false);
                btnGuardarSolicitud.setEnabled(true);
            }
        });

        btnNuevoDetalle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIDDetalle.setText(generarSiguienteIdDetalle());
                limpiarCamposDetalle();
                habilitarCamposDetalle();
                btnGuardarDetalle.setEnabled(true);
                btnNuevoDetalle.setEnabled(false);
                btnNuevaSolicitud.setEnabled(false);
            }
        });

        btnGuardarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtIDSol.getText().isEmpty() || choiceDia.getSelectedIndex() == 0 || choiceMes.getSelectedIndex() == 0
                        || choiceAnio.getSelectedIndex() == 0 ) {
                    mostrarMensajeTempAmarillo("Todos los campos son obligatorios");
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
                mostrarMensajeTempVerde("Solicitud guardada exitosamente");
                mostrarMensajeTempVerde("Ahora puede agregar detalles a la solicitud");
                generada = true;
                contadorDetalles = 0;
                txtIDDetalle.setText(generarSiguienteIdDetalle());

                btnGuardarDetalle.setEnabled(true);
                btnNuevoDetalle.setEnabled(false);
                btnGuardarDetalle.setEnabled(true);
                btnNuevoDetalle.setEnabled(false);
                btnGuardarSolicitud.setEnabled(false);
                habilitarCamposDetalle();
            }
        });

        setVisible(true);
    }

    private void limpiarCamposDetalle() {
        txtObservacion.setText("");
        txtCantidad.setText("");
        choiceProductos.select(0);
    }

    public void limpiarCamposSolicitud() {
        txtIDSol.setText(generarSiguienteIdSolicitudCompra());
        txtComentario.setText("");
        choiceDia.select(0);
        choiceMes.select(0);
        choiceAnio.select(0);
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
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.dispose();
            }
        });
        panelBoton.add(btnAceptar);

        dialogo.add(panelMensaje, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void mostrarMensajeTempRojo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(this);

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

    private void mostrarMensajeTempAmarillo(String mensaje) {
        Dialog dialogo = new Dialog(this, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(this);

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

    private void mostrarMensajeTempVerde(String mensaje) {
        Dialog dialogo = new Dialog(this, "Alerta", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new BorderLayout());
        dialogo.setLocationRelativeTo(this);

        Panel panelTitulo = new Panel();
        panelTitulo.setBackground(new Color(22, 196, 127));
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

    private void deshabilitarCamposDetalle(){
        txtCantidad.setEnabled(false);
        txtIDDetalle.setEnabled(false);
        choiceProductos.setEnabled(false);
        txtObservacion.setEnabled(false);
    }
    private void habilitarCamposDetalle(){
        txtCantidad.setEnabled(true);
        txtIDDetalle.setEnabled(true);
        choiceProductos.setEnabled(true);
        txtObservacion.setEnabled(true);
    }

    private String generarSiguienteIdSolicitudCompra() {
        int max = 0;
        for (SolicitudCompra sC : Datos.getSolicitudes()) {
            String id = sC.getId();
            if (id.startsWith("SC-")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("SC-%03d", max + 1);
    }

    private String generarSiguienteIdDetalle() {
        contadorDetalles++;
        return String.format("DS-%03d", contadorDetalles);
    }
}

