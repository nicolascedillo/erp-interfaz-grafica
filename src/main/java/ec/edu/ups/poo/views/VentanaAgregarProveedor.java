package ec.edu.ups.poo.views;

import ec.edu.ups.poo.data.Datos;
import ec.edu.ups.poo.models.inventory.Producto;
import ec.edu.ups.poo.models.entities.Provedor;
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

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.CENTER));

        Label titulo = new Label("Registro de Proveedores", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(titulo);

        Button botonSalir = new Button("Salir");
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaMenu ventanaMenu = new VentanaMenu();
            }
        });
        panelSuperior.add(botonSalir);

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

        Button btnCrearProveedor = new Button("Guardar Proveedor");
        btnCrearProveedor.setPreferredSize(new Dimension(180, 35));
        panelBotones.add(btnCrearProveedor);

        Button btnNuevoProvedor = new Button("Nuevo Proveedor");
        btnNuevoProvedor.setPreferredSize(new Dimension(180, 35));
        panelBotones.add(btnNuevoProvedor);
        btnNuevoProvedor.setEnabled(false);

        Button btnAgregarProducto = new Button("Agregar Producto");
        btnAgregarProducto.setPreferredSize(new Dimension(180, 35));
        panelBotones.add(btnAgregarProducto);
        btnAgregarProducto.setEnabled(false);

        btnNuevoProvedor.addActionListener(e -> {

            txtRuc.setText("");
            txtNombre.setText("");
            txtApellido.setText("");
            txtTelefono.setText("");
            txtDireccion.setText("");
            txtCorreo.setText("");

            txtRuc.setEditable(true);
            txtNombre.setEditable(true);
            txtApellido.setEditable(true);
            txtTelefono.setEditable(true);
            txtDireccion.setEditable(true);
            txtCorreo.setEditable(true);

            btnNuevoProvedor.setEnabled(false);
            btnAgregarProducto.setEnabled(false);
            btnCrearProveedor.setEnabled(true);
        });

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
                mostrarMensajeTempAmarillo("Todos los campos son obligatorios");
                return;
            }

            if (!ruc.matches("\\d+")) {
                mostrarMensajeTempAmarillo("El RUC debe contener solo números");
                return;
            }

            if (!telefono.matches("\\d+")) {
                mostrarMensajeTempAmarillo("El teléfono debe contener solo números");
                return;
            }

            for (Provedor p : Datos.getProvedores()) {
                if (p.getId().equals(ruc)) {
                    mostrarMensajeTempAmarillo("Ya existe un proveedor con ese RUC");
                    return;
                }
            }

            Provedor nuevoProveedor = new Provedor(ruc, nombre, apellido, telefono, direccion, correo);
            provedores.add(nuevoProveedor);
            rucActual = ruc;

            mostrarMensajeTempVerde("Proveedor agregado correctamente");

            txtRuc.setEditable(false);
            txtNombre.setEditable(false);
            txtApellido.setEditable(false);
            txtTelefono.setEditable(false);
            txtDireccion.setEditable(false);
            txtCorreo.setEditable(false);

            btnCrearProveedor.setEnabled(false);
            btnNuevoProvedor.setEnabled(true);
            btnAgregarProducto.setEnabled(true);

        });


        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idGenerado = generarSiguienteIdProducto(Datos.getProductos());
                if (rucActual != null) {
                    new VentanaAgregarProductoParaProveedor(rucActual,idGenerado);
                } else {
                    mostrarMensajeTempAmarillo("Primero cree un proveedor para asociar el producto");
                }
                btnAgregarProducto.setEnabled(false);
                btnNuevoProvedor.setEnabled(false);
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
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

    private String generarSiguienteIdProducto(List<Producto> productos) {
        int max = 0;
        for (Producto p : Datos.getProductos()) {
            String id = p.getId();
            if (id.startsWith("PD-")) {
                try {
                    int num = Integer.parseInt(id.substring(3));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        return String.format("PD-%03d", max + 1);
    }
}