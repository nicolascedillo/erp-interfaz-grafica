package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Provedor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBuscarProveedorID {
    private Frame frame;
    private Panel panelPrincipal;
    private List<Provedor> proveedores;

    public VentanaBuscarProveedorID(List<Provedor> proveedores) {
        this.proveedores = proveedores;
        frame = new Frame("Buscar Proveedor");
        frame.setSize(725, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Buscar Proveedor por ID");
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

        Panel panel = new Panel(new GridLayout(0, 2));
        panel.setPreferredSize(new Dimension(725,50));
        panel.add(new Label("Ingrese el RUC del proveedor a buscar:"));
        TextField txtRUC = new TextField(10);
        panel.add(txtRUC);

        Button botonBuscar = new  Button("Buscar");
        Panel panelBuscar = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.add(botonBuscar);
        panel.add(panelBuscar);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ruc = txtRUC.getText();
                Provedor encontrado = BuscarProveedor(ruc);
                if (encontrado != null) {
                    Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                    panelEncontrado.setPreferredSize(new Dimension(700,180));
                    panelEncontrado.add(new Label("ID:"));
                    TextField txtRUC = new TextField(encontrado.getCedula());
                    txtRUC.setEditable(false);
                    panelEncontrado.add(txtRUC);
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
                    panelPrincipal.add(panelEncontrado);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();

                } else{
                    Panel panelNoEncontrado = new Panel(new FlowLayout(FlowLayout.LEFT));
                    Label txtNoEncontrado = new  Label("No existe proveedor con el RUC: " + ruc);
                    panelNoEncontrado.add(txtNoEncontrado);
                    panelPrincipal.add(panelNoEncontrado);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();

                }
            }
        });


        panelPrincipal.add(panel);
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public Provedor BuscarProveedor(String cedula) {
        for (Provedor provedor : proveedores) {
            if(provedor.getCedula().equals(cedula)){
                return provedor;
            }
        }
        return null;
    }

}
