package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.entities.Provedor;

import java.awt.*;
import java.util.List;

public class VentanaBuscarProveedorID {
    private List<Provedor> proveedores;
    private Frame frame;

    public VentanaBuscarProveedorID(List<Provedor> proveedores) {
        this.proveedores = proveedores;
        frame = new Frame("Buscar Proveedor");
        frame.setSize(725, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Buscar Proveedor por RUC");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        Panel panelSuperior = new Panel(new FlowLayout());
        panelSuperior.add(titulo);

        Button botonSalir = new Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu();
        });

        frame.add(panelSuperior, BorderLayout.NORTH);

        Panel panel = new Panel(new GridLayout(0, 2));
        panel.setPreferredSize(new Dimension(600, 50));
        panel.add(new Label("Ingrese el RUC del proveedor a buscar:"));
        TextField txtRUC = new TextField(10);
        panel.add(txtRUC);

        Button botonBuscar = new Button("Buscar");
        Panel panelBuscar = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.add(botonBuscar);
        panel.add(panelBuscar);

        botonBuscar.addActionListener(e -> {
            String ruc = txtRUC.getText();
            Provedor encontrado = buscarProveedor(ruc);
            if (encontrado != null) {
                Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                panelEncontrado.setPreferredSize(new Dimension(600, 180));

                panelEncontrado.add(new Label("ID:"));
                TextField txtRUCField = new TextField(encontrado.getId());
                txtRUCField.setEditable(false);
                panelEncontrado.add(txtRUCField);

                panelEncontrado.add(new Label("Nombre:"));
                TextField txtNombre = new TextField(encontrado.getNombre());
                txtNombre.setEditable(false);
                panelEncontrado.add(txtNombre);

                panelEncontrado.add(new Label("Apellido:"));
                TextField txtApellido = new TextField(encontrado.getApellido());
                txtApellido.setEditable(false);
                panelEncontrado.add(txtApellido);

                panelEncontrado.add(new Label("Direccion:"));
                TextField txtDireccion = new TextField(encontrado.getDireccion());
                txtDireccion.setEditable(false);
                panelEncontrado.add(txtDireccion);

                panelEncontrado.add(new Label("Telefono:"));
                TextField txtTelefono = new TextField(encontrado.getTelefono());
                txtTelefono.setEditable(false);
                panelEncontrado.add(txtTelefono);

                panelEncontrado.add(new Label("Correo:"));
                TextField txtCorreo = new TextField(encontrado.getCorreo());
                txtCorreo.setEditable(false);
                panelEncontrado.add(txtCorreo);

                panelEncontrado.add(new Label("Productos:"));
                TextField txtProductos = new TextField(encontrado.getNombreProductos());
                txtProductos.setEditable(false);
                panelEncontrado.add(txtProductos);

                panelPrincipal.removeAll();
                panelPrincipal.add(panelEncontrado);
                frame.revalidate();
                frame.repaint();

            } else {
                mostrarMensajeTempRojo("No existe proveedor con el RUC: " + ruc);
            }
        });

        panelPrincipal.add(panel);
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public Provedor buscarProveedor(String cedula) {
        for (Provedor provedor : proveedores) {
            if (provedor.getId().equals(cedula)) {
                return provedor;
            }
        }
        return null;
    }

    private void mostrarMensajeTempRojo(String mensaje) {
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

}