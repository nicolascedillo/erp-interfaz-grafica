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
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        setTitle("Menú Administrador");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        btnListarProductos = new Button("Listar Productos");
        btnListarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProducto ventanaListarProducto = new VentanaListarProducto();
                ventanaListarProducto.setVisible(true);
                dispose();
            }
        });

        btnListarProvedores = new Button("Listar Proveedores");
        btnListarProvedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProveedor ventanaListarProveedor = new VentanaListarProveedor();
                ventanaListarProveedor.setVisible(true);
                dispose();
            }
        });

        btnListarSolicitudesCompras = new Button("Listar Solicitudes de Compra");
        btnListarSolicitudesCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarSolicitud ventanaListarSolicitud = new VentanaListarSolicitud();
                ventanaListarSolicitud.setVisible(true);
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