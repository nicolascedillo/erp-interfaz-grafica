package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.models.enums.Feriado;

import java.awt.*;
import java.awt.event.*;

public class VentanaAgregarProductoParaProveedor extends Frame {

    public VentanaAgregarProductoParaProveedor(String ruc) {
        setTitle("Agregar Producto para Proveedor");
        setSize(800, 400);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new BorderLayout());

        Label titulo = new Label("Agregar Producto al Proveedor", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(titulo, BorderLayout.CENTER);

        Panel panelBotonSalir = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button botonSalir = new Button("Salir");
        botonSalir.addActionListener(e -> {
            dispose();
            new VentanaAgregarProveedor();
        });
        panelBotonSalir.add(botonSalir);
        panelSuperior.add(panelBotonSalir, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);

        Panel panelCentro = new Panel(new GridBagLayout());

        Panel panelForm = new Panel();
        panelForm.setLayout(new GridLayout(9, 2, 5, 8));
        panelForm.setPreferredSize(new Dimension(750, 350));

        panelForm.add(new Label("Categorías disponibles:"));
        Panel panelCategorias = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelCategorias.add(new Label("Comida / Primera_necesidad / Agricola / Medicina / Escolar / ETC"));
        panelForm.add(panelCategorias);

        panelForm.add(new Label("Ingrese Categoría:"));
        TextField txtCategoria = new TextField();
        panelForm.add(txtCategoria);

        panelForm.add(new Label("ID:"));
        TextField txtId = new TextField();
        panelForm.add(txtId);

        panelForm.add(new Label("Nombre:"));
        TextField txtNombre = new TextField();
        panelForm.add(txtNombre);

        panelForm.add(new Label("Precio Unitario:"));
        TextField txtPrecioUnitario = new TextField();
        panelForm.add(txtPrecioUnitario);

        panelForm.add(new Label("Marca:"));
        TextField txtMarca = new TextField();
        panelForm.add(txtMarca);

        panelForm.add(new Label("Proveedor (RUC):"));
        TextField txtRucProveedor = new TextField(ruc);
        txtRucProveedor.setEditable(false);
        txtRucProveedor.setBackground(Color.LIGHT_GRAY);
        panelForm.add(txtRucProveedor);

        panelForm.add(new Label(""));

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button btnRegistrar = new Button("Registrar Producto");
        btnRegistrar.setPreferredSize(new Dimension(180, 30));
        panelBoton.add(btnRegistrar);
        panelForm.add(panelBoton);

        panelCentro.add(panelForm);
        add(panelCentro, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            String categoria = txtCategoria.getText().trim();
            String idStr = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecioUnitario.getText().trim();
            String marca = txtMarca.getText().trim();
            String cedula = txtRucProveedor.getText().trim();

            if (categoria.isEmpty() || idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || marca.isEmpty()) {
                mostrarMensajeTemp("Todos los campos son obligatorios");
                return;
            }

            if (!idStr.matches("\\d+") || !precioStr.matches("[\\d.,]+")) {
                mostrarMensajeTemp("ID y Precio deben ser numéricos");
                return;
            }

            Provedor proveedor = null;
            for (Provedor p : Datos.getProvedores()) {
                if (p.getCedula().equals(cedula)) {
                    proveedor = p;
                    break;
                }
            }

            if (proveedor == null) {
                mostrarMensajeTemp("Proveedor no encontrado");
                return;
            }

            int id = Integer.parseInt(idStr);
            double precioUnitario = Double.parseDouble(precioStr.replace(",", "."));

            Producto producto;
            switch (categoria) {
                case "Comida":
                case "Primera_necesidad":
                case "Agricola":
                case "Medicina":
                case "Escolar":
                    producto = new ProductoSinIva(id, nombre, precioUnitario, marca, "El producto no agrava IVA");
                    break;
                default:
                    producto = new ProductoConIva(id, nombre, precioUnitario, marca, Feriado.NO_FERIADO);
                    break;
            }

            Datos.getProductos().add(producto);
            proveedor.addProducto(producto);

            mostrarMensajeTemp("Producto agregado correctamente al proveedor");
            txtCategoria.setText("");
            txtId.setText("");
            txtNombre.setText("");
            txtPrecioUnitario.setText("");
            txtMarca.setText("");
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
}