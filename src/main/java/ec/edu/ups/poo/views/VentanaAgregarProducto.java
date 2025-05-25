package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.entities.Provedor;
import ec.edu.ups.poo.models.enums.Feriado;
import ec.edu.ups.poo.data.Datos;
import ec.edu.ups.poo.models.inventory.Producto;
import ec.edu.ups.poo.models.inventory.ProductoConIva;
import ec.edu.ups.poo.models.inventory.ProductoSinIva;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarProducto extends Frame {

    private List<Producto> productos;
    private List<Provedor> provedor;

    public VentanaAgregarProducto(List<Producto> productos,List<Provedor> provedor, String idGenerado) {
        this.productos = productos;
        this.provedor = provedor;
        setTitle("Agregar Producto");
        setSize(800, 450);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new FlowLayout());
        Label titulo = new Label("Agregar Producto", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
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
        txtId.setEditable(false);
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
        Choice choiceProvedor = new Choice();
        choiceProvedor.add("");

        for (Provedor p : provedor) {
            choiceProvedor.addItem(p.getNombre() +" (" + p.getId() + ")");
        }
        panelForm.add(choiceProvedor);
        panelForm.add(new Label(""));

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));

        Button btnRegistrar = new Button("Guardar Producto");
        btnRegistrar.setPreferredSize(new Dimension(180, 30));
        panelBoton.add(btnRegistrar);

        Button btnNuevoProducto = new Button("Nuevo Producto");
        btnNuevoProducto.setPreferredSize(new Dimension(180, 30));
        panelBoton.add(btnNuevoProducto);
        btnNuevoProducto.setEnabled(false);

        panelForm.add(panelBoton);

        btnNuevoProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtId.setEditable(false);
                txtCategoria.setEditable(true);
                txtNombre.setEditable(true);
                txtPrecioUnitario.setEditable(true);
                txtMarca.setEditable(true);
                choiceProvedor.setEnabled(true);

                txtId.setText(generarSiguienteIdProducto());
                txtCategoria.setText("");
                txtNombre.setText("");
                txtPrecioUnitario.setText("");
                txtMarca.setText("");
                choiceProvedor.select(0);
                btnNuevoProducto.setEnabled(false);
                btnRegistrar.setEnabled(true);
            }
        });


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
                String idProvedor = obtenerIdProvedorChoice(choiceProvedor);

                if (categoria.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || marca.isEmpty() || choiceProvedor.getSelectedIndex() == 0) {
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

                Provedor temp = null;
                for (Provedor p : provedor) {
                    if (p.getId().equals(idProvedor)) {
                        temp = p;
                        break;
                    }
                }

                if (temp == null) {
                    mostrarMensajeTempAmarillo("Ingrese la cédula de un proveedor existente para registrar el producto");
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
                        producto = new ProductoSinIva(idStr, nombre, precioUnitario, marca, "El producto no grava iva");
                        break;
                    default:
                        producto = new ProductoConIva(idStr, nombre, precioUnitario, marca, Feriado.NO_FERIADO);
                        break;
                }

                productos.add(producto);
                temp.addProducto(producto);

                mostrarMensajeTempVerde("Producto registrado y vinculado a proveedor");

                txtId.setEditable(false);
                txtCategoria.setEditable(false);
                txtNombre.setEditable(false);
                txtPrecioUnitario.setEditable(false);
                txtMarca.setEditable(false);
                choiceProvedor.setEnabled(false);

                btnRegistrar.setEnabled(false);
                btnNuevoProducto.setEnabled(true);
            }
        });

        setVisible(true);
    }

    private void mostrarMensajeTemp(String mensaje) {
        Dialog dialogo = new Dialog(this, "Mensaje", true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setSize(400, 120);
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

    private String obtenerIdProvedorChoice(Choice choiceProvedor) {
        String seleccionado = choiceProvedor.getSelectedItem();
        int inicio = seleccionado.indexOf('(');
        int fin = seleccionado.indexOf(')');
        if (inicio != -1 && fin != -1 && inicio < fin) {
            return seleccionado.substring(inicio + 1, fin).trim();
        }
        return "";
    }

}