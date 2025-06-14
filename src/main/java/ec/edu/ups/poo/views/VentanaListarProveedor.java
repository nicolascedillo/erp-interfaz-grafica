package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.entities.Provedor;

import java.awt.*;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListarProveedor {

    public VentanaListarProveedor(List<Provedor> proveedores) {

        Frame frame = new Frame("Lista de Proveedores");
        frame.setSize(750, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.CENTER));
        Label titulo = new Label("Listar Proveedores");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        Panel panelSuperior = new Panel(new FlowLayout());
        panelSuperior.add(titulo);
        Button botonSalir = new  Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VentanaMenu ventanaMenu = new VentanaMenu();
            }
        });
        frame.add(panelSuperior, BorderLayout.NORTH);

        ScrollPane scrollPane = new ScrollPane (ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.setSize(580, 350);
        panelPrincipal.setPreferredSize(new Dimension(580, proveedores.size() * 200));

        for (Provedor provedor: proveedores) {
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(650,180));

            panel.add(new Label("RUC:"));
            TextField txtRUC = new TextField(provedor.getId());
            txtRUC.setEditable(false);
            panel.add(txtRUC);

            panel.add(new Label("Nombre:"));
            TextField txtNombre = new TextField(provedor.getNombre());
            txtNombre.setEditable(false);
            panel.add(txtNombre);

            panel.add(new Label("Apellido:"));
            TextField txtApellido = new TextField(provedor.getApellido());
            txtApellido.setEditable(false);
            panel.add(txtApellido);

            panel.add(new Label("Direccion:"));
            TextField txtDireccion = new TextField(provedor.getDireccion());
            txtDireccion.setEditable(false);
            panel.add(txtDireccion);

            panel.add(new Label("Telefono:"));
            TextField txtTelefono = new TextField(provedor.getTelefono());
            txtTelefono.setEditable(false);
            panel.add(txtTelefono);

            panel.add(new Label("Correo:"));
            TextField txtCorreo = new TextField(provedor.getCorreo());
            txtCorreo.setEditable(false);
            panel.add(txtCorreo);

            panel.add(new Label("Productos:"));
            TextField txtProductos = new TextField(provedor.getNombreProductos());
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
