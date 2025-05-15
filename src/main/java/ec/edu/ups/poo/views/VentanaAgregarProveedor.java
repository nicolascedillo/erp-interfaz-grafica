package ec.edu.ups.poo.views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.Provedor;
import java.util.List;

public class VentanaAgregarProveedor extends Frame{

    private TextField txtRuc;
    private TextField txtNombreProvedor;
    private TextField txtApellido;
    private TextField txtTelefono;
    private TextField txtDireccion;
    private TextField txtCorreo;

    private TextField txtCategoria;
    private TextField txtNombre;
    private TextField txtPrecioU;
    private TextField txtMarca;

    private Button btnCrearProveedor;
    private Button btnAñadirProducto;
    private Button btnSalir;

    private Provedor provedor;
    private List<Provedor> provedores;

    public VentanaAgregarProveedor(List<Provedor> provedores){

        super("Agregar proveedor");

        this.provedores = provedores;
        provedor = new Provedor(getName(), getName(), getName(), getTitle(), getWarningString(), getName());

        Panel panel = new Panel();

        txtRuc = new TextField(10);
        txtNombreProvedor = new TextField(10);
        txtApellido  = new TextField(10);
        txtTelefono = new TextField(10);
        txtDireccion = new TextField(10);
        txtCorreo = new TextField(10);

        panel.add(new Label("Ruc: "));
        panel.add(txtRuc);
        panel.add(new Label("Nombre: "));
        panel.add(txtNombreProvedor);
        panel.add(new Label("Apellido: "));
        panel.add(txtApellido);
        panel.add(new Label("Telefono: "));
        panel.add(txtTelefono);
        panel.add(new Label("Direccion: "));
        panel.add(txtDireccion);
        panel.add(new Label("Correo: "));
        panel.add(txtCorreo);

        btnCrearProveedor = new Button("Crear");
        btnAñadirProducto = new Button("Añadir Producto");
        btnSalir = new Button("Salir");

        panel.add(btnCrearProveedor);
        panel.add(btnAñadirProducto);
        panel.add(btnSalir);

        btnCrearProveedor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                provedores.add(provedor);
            }

        });

        btnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });

        add(panel);
        setSize(500, 500);
        setVisible(true);

    }
}
