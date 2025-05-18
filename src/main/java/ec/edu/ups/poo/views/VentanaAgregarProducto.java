package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.models.enums.Feriado;

import java.awt.*;
import java.awt.event.*;

public class VentanaAgregarProducto extends Frame {

    public VentanaAgregarProducto() {
        setTitle("Agregar Producto");
        setSize(800, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label titulo = new Label("Agregar Producto");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelSuperior.add(titulo);

        Button botonSalir = new Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(e -> {
            dispose();
            new VentanaMenu();
        });

        add(panelSuperior, BorderLayout.NORTH);

        Panel panelForm = new Panel(new GridLayout(9, 2, 10, 14)); // filas, columnas, hgap, vgap
        panelForm.setPreferredSize(new Dimension(700, 400));

        panelForm.add(new Label("Categorías disponibles:"));
        Panel panelCategorias = new Panel(new GridLayout(5, 1, 0, 0));
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

        panelForm.add(new Label("Proveedor (Cédula):"));
        TextField txtCedulaProveedor = new TextField();
        panelForm.add(txtCedulaProveedor);

        panelForm.add(new Label(""));
        Button btnRegistrar = new Button("Registrar Producto");
        panelForm.add(btnRegistrar);

        add(panelForm, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            String categoria = txtCategoria.getText().trim();
            String idStr = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecioUnitario.getText().trim();
            String marca = txtMarca.getText().trim();
            String cedula = txtCedulaProveedor.getText().trim();

            if (categoria.isEmpty() || idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || marca.isEmpty() || cedula.isEmpty()) {
                mostrarMensajeTemp("Todos los campos son obligatorios");
                return;
            }

            boolean esIdNumero = true;
            boolean esPrecioNumero = true;
            for (int i = 0; i < idStr.length(); i++) {
                if (!Character.isDigit(idStr.charAt(i))) {
                    esIdNumero = false;
                    break;
                }
            }
            String precioStrAux = precioStr.replace(",", ".");
            int puntoCount = 0;
            for (int i = 0; i < precioStrAux.length(); i++) {
                char c = precioStrAux.charAt(i);
                if (!Character.isDigit(c)) {
                    if (c == '.' && puntoCount == 0) {
                        puntoCount++;
                    } else {
                        esPrecioNumero = false;
                        break;
                    }
                }
            }

            if (!esIdNumero || !esPrecioNumero) {
                mostrarMensajeTemp("ID y Precio deben ser numéricos");
                return;
            }

            Provedor temp = null;
            for (Provedor p : Datos.getProvedores()) {
                if (p.getCedula().equals(cedula)) {
                    temp = p;
                    break;
                }
            }
            if (temp == null) {
                mostrarMensajeTemp("Ingrese la cédula de un proveedor para registrar el producto");
                return;
            }

            int id = Integer.parseInt(idStr);
            double precioUnitario = Double.parseDouble(precioStrAux);

            Producto producto;
            switch (categoria) {
                case "Comida":
                case "Primera_necesidad":
                case "Agricola":
                case "Medicina":
                case "Escolar":
                    producto = new ProductoSinIva(id, nombre, precioUnitario, marca, "El producto no agraba iva");
                    break;
                default:
                    producto = new ProductoConIva(id, nombre, precioUnitario, marca, Feriado.NO_FERIADO);
                    break;
            }
            Datos.getProductos().add(producto);
            temp.addProducto(producto);

            mostrarMensajeTemp("Producto registrado y vinculado a proveedor");

            txtCategoria.setText("");
            txtId.setText("");
            txtNombre.setText("");
            txtPrecioUnitario.setText("");
            txtMarca.setText("");
            txtCedulaProveedor.setText("");
        });

        setVisible(true);
    }

    private void mostrarMensajeTemp(String mensaje){
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        Label label = new Label(mensaje, Label.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(label);
        dialogo.setSize(400, 100);
        dialogo.setLocationRelativeTo(this);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.setVisible(false);
                dialogo.dispose();
            }
        });
        dialogo.setVisible(true);
    }
}