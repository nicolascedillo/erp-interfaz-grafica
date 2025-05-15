package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Provedor;

import java.awt.*;
import java.awt.ScrollPane;
import java.util.List;

public class VentanaListarProveedor {
    private Frame frame;
    private TextField txtRUC;
    private TextField txtNombre;
    private TextField txtApellido;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtCorreo;
    private TextField txtProductos;
    private List<Provedor> proveedores;

    public VentanaListarProveedor(List<Provedor> proveedores) {
        this.proveedores = proveedores;


        frame = new Frame("Lista de Proveedores");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label titulo = new Label("Listar Proveedores");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        ScrollPane scrollPane = new ScrollPane (ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.setSize(580, 350);
        panelPrincipal.setPreferredSize(new Dimension(560, proveedores.size() * 160));
        for (Provedor provedor: proveedores) {
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700,180));
            panel.add(new Label("RUC:"));
            txtRUC = new TextField(provedor.getCedula());
            txtRUC.setEditable(false);
            panel.add(txtRUC);
            panel.add(new Label("Nombre:"));
            txtNombre = new TextField(provedor.getNombre());
            txtNombre.setEditable(false);
            panel.add(txtNombre);
            panel.add(new Label("Apellido:"));
            txtApellido = new TextField(provedor.getApellido());
            txtApellido.setEditable(false);
            panel.add(txtApellido);
            panel.add(new Label("Direccion:"));
            txtDireccion = new TextField(provedor.getDireccion());
            txtDireccion.setEditable(false);
            panel.add(txtDireccion);
            panel.add(new Label("Telefono:"));
            txtTelefono = new TextField(provedor.getTelefono());
            txtTelefono.setEditable(false);
            panel.add(txtTelefono);
            panel.add(new Label("Correo:"));
            txtCorreo = new TextField(provedor.getCorreo());
            txtCorreo.setEditable(false);
            panel.add(txtCorreo);
            panel.add(new Label("Productos:"));
            txtProductos = new TextField(provedor.getNombreProductos());
            txtProductos.setEditable(false);
            panel.add(txtProductos);
            panel.add(new Label("---------------------------------------------------------------------------------"));
            panel.add(new Label("---------------------------------------------------------------------------------"));

            panelPrincipal.add(panel);
        }

        scrollPane.add(panelPrincipal);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
