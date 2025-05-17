package ec.edu.ups.poo.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu extends Frame {

    private Button btnListarProductos;
    private Button btnListarProvedores;
    private Button btnListarSolicitudesCompras;
    private Button btnRegresar;

    public VentanaMenu() {
        Datos datos = new Datos();
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1));

        setTitle("Menú");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        Label titulo = new Label("Menú del sistema");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        Panel panelBotonListarProductos = new Panel(new FlowLayout());
        btnListarProductos = new Button("Listar Productos");
        btnListarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProducto ventanaListarProducto = new VentanaListarProducto(datos.getProductos());
                dispose();

            }
        });

        btnListarProvedores = new Button("Listar Proveedores");
        btnListarProvedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProveedor ventanaListarProveedor = new VentanaListarProveedor(datos.getProvedores());
                dispose();

            }
        });

        btnListarSolicitudesCompras = new Button("Listar Solicitudes de Compra");
        btnListarSolicitudesCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarSolicitud ventanaListarSolicitud = new VentanaListarSolicitud(datos.getSolicitudes());
                dispose();
            }
        });

        btnRegresar = new Button("Regresar");
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaLogIn ventanaLogIn = new VentanaLogIn();
                dispose();
            }
        });

        panel.add(btnListarProductos);
        panel.add(btnListarProvedores);
        panel.add(btnListarSolicitudesCompras);
        panel.add(btnRegresar);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}