package ec.edu.ups.poo.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.Provedor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class VentanaAgregarProveedor extends Frame {

    private TextField txtRuc;
    private TextField txtNombreProvedor;
    private TextField txtApellido;
    private TextField txtTelefono;
    private TextField txtDireccion;
    private TextField txtCorreo;

    private TextField txtIdProducto;
    private TextField txtNombreProducto;
    private TextField txtPrecioUnitadio;
    private TextField txtMarca;
    private Checkbox chkAgregarProducto;

    private Button btnCrearProveedor;
    private Button btnLimpiar;
    private Button btnSalir;

    private List<Provedor> provedores;
    private List<Producto> productos;

    public VentanaAgregarProveedor(List<Provedor> provedores) {
        super("Agregar proveedor");
        this.provedores = provedores;

        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new BorderLayout());
        Panel panelTitulo = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(panelTitulo, BorderLayout.NORTH);
        Label titulo = new Label("Agregar Proveedor", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelTitulo.add(titulo);
        Panel panelCampos = new Panel(new GridLayout(6, 2, 10, 10));
        txtRuc = new TextField(20);
        txtNombreProvedor = new TextField(20);
        txtApellido = new TextField(20);
        txtTelefono = new TextField(20);
        txtDireccion = new TextField(20);
        txtCorreo = new TextField(20);

        panelCampos.add(new Label("RUC:"));
        panelCampos.add(txtRuc);
        panelCampos.add(new Label("Nombre:"));
        panelCampos.add(txtNombreProvedor);
        panelCampos.add(new Label("Apellido:"));
        panelCampos.add(txtApellido);
        panelCampos.add(new Label("Teléfono:"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new Label("Dirección:"));
        panelCampos.add(txtDireccion);
        panelCampos.add(new Label("Correo:"));
        panelCampos.add(txtCorreo);

        panelSuperior.add(panelCampos, BorderLayout.CENTER);
        add(panelSuperior, BorderLayout.NORTH);

        Panel panelProductos = new Panel(new GridLayout(5, 2, 10, 10));
        chkAgregarProducto = new Checkbox("¿Agregar producto para este proveedor?");
        panelProductos.add(new Label());
        panelProductos.add(chkAgregarProducto);

        txtIdProducto = new TextField(20);
        txtNombreProducto = new TextField(20);
        txtPrecioUnitadio = new TextField(20);
        txtMarca = new TextField(20);

        panelProductos.add(new Label("ID Producto:"));
        panelProductos.add(txtIdProducto);
        panelProductos.add(new Label("Nombre Producto:"));
        panelProductos.add(txtNombreProducto);
        panelProductos.add(new Label("Precio Unitario:"));
        panelProductos.add(txtPrecioUnitadio);
        panelProductos.add(new Label("Marca:"));
        panelProductos.add(txtMarca);

        add(panelProductos, BorderLayout.SOUTH);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        btnCrearProveedor = new Button("Crear");
        btnLimpiar = new Button("Limpiar");
        btnSalir = new Button("Salir");
        btnLimpiar.setPreferredSize(new Dimension(100, 30));
        btnCrearProveedor.setPreferredSize(new Dimension(100, 30));
        btnSalir.setPreferredSize(new Dimension(100, 30));        panelBotones.add(btnCrearProveedor);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.CENTER);

        btnCrearProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtRuc.getText().trim().isEmpty() ||
                        txtNombreProvedor.getText().trim().isEmpty() ||
                        txtApellido.getText().trim().isEmpty() ||
                        txtTelefono.getText().trim().isEmpty() ||
                        txtDireccion.getText().trim().isEmpty() ||
                        txtCorreo.getText().trim().isEmpty()) {
                    mostrarMensajeTemp("Ingrese todos los campos, son obligatorios");
                    return;
                }

                String ruc = txtRuc.getText();
                String nombre = txtNombreProvedor.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String direccion = txtDireccion.getText();
                String correo = txtCorreo.getText();

                Provedor nuevoProveedor = new Provedor(ruc, nombre, apellido, telefono, direccion, correo);
                Datos.addProvedor(nuevoProveedor);

                mostrarMensajeTemp("Proveedor agregado correctamente");

                limpiarCampos();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaMenu();
                dispose();
            }
        });

        setVisible(true);
    }

    private void limpiarCampos() {
        txtRuc.setText("");
        txtNombreProvedor.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
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