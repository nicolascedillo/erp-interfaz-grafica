package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Datos;
import ec.edu.ups.poo.models.Provedor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaAgregarProveedor extends Frame {

    private TextField txtRuc, txtNombre, txtApellido, txtTelefono, txtDireccion, txtCorreo;
    private String rucActual = null;
    private List<Provedor> provedores;

    public VentanaAgregarProveedor(List<Provedor> provedores) {
        this.provedores = provedores;
        setTitle("Agregar Proveedor");
        setSize(800, 450);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new BorderLayout());

        Label titulo = new Label("Registro de Proveedores", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(titulo, BorderLayout.CENTER);

        Panel panelBotonSalir = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button botonSalir = new Button("Salir");
        botonSalir.addActionListener(e -> {
            new VentanaMenu();
            dispose();
        });
        panelBotonSalir.add(botonSalir);
        panelSuperior.add(panelBotonSalir, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        Panel panelFormulario = new Panel();
        panelFormulario.setLayout(new GridLayout(6, 2, 5, 8));
        panelFormulario.setPreferredSize(new Dimension(700, 250));

        panelFormulario.add(new Label("RUC:"));
        txtRuc = new TextField();
        panelFormulario.add(txtRuc);

        panelFormulario.add(new Label("Nombre:"));
        txtNombre = new TextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new Label("Apellido:"));
        txtApellido = new TextField();
        panelFormulario.add(txtApellido);

        panelFormulario.add(new Label("Teléfono:"));
        txtTelefono = new TextField();
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new Label("Dirección:"));
        txtDireccion = new TextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new Label("Correo electrónico:"));
        txtCorreo = new TextField();
        panelFormulario.add(txtCorreo);

        Panel panelCentro = new Panel(new GridBagLayout());
        panelCentro.add(panelFormulario);
        add(panelCentro, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setPreferredSize(new Dimension(800, 60));

        Button btnCrearProveedor = new Button("Crear Proveedor");
        btnCrearProveedor.setPreferredSize(new Dimension(180, 35));
        panelBotones.add(btnCrearProveedor);

        Button btnAgregarProducto = new Button("Agregar Producto");
        btnAgregarProducto.setPreferredSize(new Dimension(180, 35));
        panelBotones.add(btnAgregarProducto);

        add(panelBotones, BorderLayout.SOUTH);

        btnCrearProveedor.addActionListener(e -> {
            String ruc = txtRuc.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String correo = txtCorreo.getText().trim();

            if (ruc.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                    telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
                mostrarMensajeTemp("Todos los campos son obligatorios");
                return;
            }

            for (Provedor p : Datos.getProvedores()) {
                if (p.getId().equals(ruc)) {
                    mostrarMensajeTemp("Ya existe un proveedor con ese RUC");
                    return;
                }
            }

            Provedor nuevoProveedor = new Provedor(ruc, nombre, apellido, telefono, direccion, correo);
            provedores.add(nuevoProveedor);
            rucActual = ruc;

            mostrarMensajeTemp("Proveedor agregado correctamente");
            limpiarCampos();
        });

        btnAgregarProducto.addActionListener(e -> {
            if (rucActual != null) {
                new VentanaAgregarProductoParaProveedor(rucActual);
            } else {
                mostrarMensajeTemp("Primero cree un proveedor para asociar el producto");
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void limpiarCampos() {
        txtRuc.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
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