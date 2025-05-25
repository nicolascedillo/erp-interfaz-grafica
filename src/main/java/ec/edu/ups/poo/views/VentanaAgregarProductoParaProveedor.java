package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.*;
import ec.edu.ups.poo.models.enums.Feriado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarProductoParaProveedor extends Frame {

    public VentanaAgregarProductoParaProveedor(String ruc, String idGenerado) {
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
            new VentanaMenu();
            dispose();
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
        TextField txtId = new TextField(idGenerado);
        panelForm.add(txtId);
        txtId.setEditable(false);

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
        panelForm.add(txtRucProveedor);

        panelForm.add(new Label(""));

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button btnRegistrar = new Button("Guardar Producto");
        btnRegistrar.setPreferredSize(new Dimension(180, 30));
        panelBoton.add(btnRegistrar);

        Button btnNuevo = new Button("Nuevo Producto");
        btnNuevo.setPreferredSize(new Dimension(180, 30));
        panelBoton.add(btnNuevo);
        btnNuevo.setEnabled(false);

        panelCentro.add(panelForm);
        add(panelCentro, BorderLayout.CENTER);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = txtCategoria.getText().trim();
                String idStr = txtId.getText().trim();
                String nombre = txtNombre.getText().trim();
                String precioStr = txtPrecioUnitario.getText().trim();
                String marca = txtMarca.getText().trim();
                String cedula = txtRucProveedor.getText().trim();

                if (categoria.isEmpty() || idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || marca.isEmpty()) {
                    mostrarMensajeTempAmarillo("Todos los campos son obligatorios");
                    return;
                }

                String precioStrAux = precioStr.replace(',', '.');
                boolean esPrecioValido = true;
                int contadorPuntos = 0;

                for (int i = 0; i < precioStrAux.length(); i++) {
                    char c = precioStrAux.charAt(i);
                    if (Character.isDigit(c)) {
                        continue;
                    } else if (c == '.') {
                        contadorPuntos++;
                        if (contadorPuntos > 1) {
                            esPrecioValido = false;
                            break;
                        }
                    } else {
                        esPrecioValido = false;
                        break;
                    }
                }

                if (!esPrecioValido) {
                    mostrarMensajeTempAmarillo("El precio debe ser numérico");
                    return;
                }

                Provedor proveedor = null;
                for (Provedor p : Datos.getProvedores()) {
                    if (p.getId().equals(cedula)) {
                        proveedor = p;
                        break;
                    }
                }

                if (proveedor == null) {
                    mostrarMensajeTempRojo("Proveedor no encontrado");
                    return;
                }

                double precioUnitario = Double.parseDouble(precioStrAux);

                Producto producto;

                switch (categoria) {
                    case "Comida":
                    case "Primera_necesidad":
                    case "Agricola":
                    case "Medicina":
                    case "Escolar":
                        producto = new ProductoSinIva(idStr, nombre, precioUnitario, marca, "El producto no agrava IVA");
                        break;
                    default:
                        producto = new ProductoConIva(idStr, nombre, precioUnitario, marca, Feriado.NO_FERIADO);
                        break;
                }

                Datos.getProductos().add(producto);
                proveedor.addProducto(producto);

                mostrarMensajeTempVerde("Producto agregado correctamente al proveedor");

                txtCategoria.setEditable(false);
                txtNombre.setEditable(false);
                txtPrecioUnitario.setEditable(false);
                txtMarca.setEditable(false);

                btnNuevo.setEnabled(true);
                btnRegistrar.setEnabled(false);
            }
        });

        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCategoria.setEditable(true);
                txtNombre.setEditable(true);
                txtPrecioUnitario.setEditable(true);
                txtMarca.setEditable(true);

                txtCategoria.setText("");
                txtNombre.setText("");
                txtPrecioUnitario.setText("");
                txtMarca.setText("");
                txtId.setText(generarSiguienteIdProducto());

                btnRegistrar.setEnabled(true);
                btnNuevo.setEnabled(false);

            }
        });

        panelForm.add(panelBoton);
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

    private String generarSiguienteIdProducto() {
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