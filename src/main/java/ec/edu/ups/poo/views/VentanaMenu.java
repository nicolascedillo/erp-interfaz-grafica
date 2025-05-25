package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Datos;
import ec.edu.ups.poo.models.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMenu extends Frame {

    public VentanaMenu() {
        setTitle("Menú");
        setSize(1000, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label titulo = new Label("Menú del sistema - Admin", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        Panel panelGrid = new Panel(new GridLayout(1, 5, 30, 0));

        Panel panelRegistrar = new Panel(new GridLayout(0, 1, 0, 10));
        panelRegistrar.add(new Label("Registrar:", Label.CENTER));

        Button btnAgregarProveedor = new Button("Proveedor");
        btnAgregarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarProveedor ventanaAgregarProveedor = new VentanaAgregarProveedor(Datos.getProvedores());
                dispose();
            }
        });

        Button btnAgregarProducto = new Button("Producto");
        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarProducto ventanaAgregarProducto = new VentanaAgregarProducto(Datos.getProductos(),Datos.getProvedores(),generarSiguienteIdProducto());
                dispose();
            }
        });

        Button btnRegistrarSolicitud = new Button("Solicitud");
        btnRegistrarSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaAgregarSolicitud ventanaAgregarSolicitud = new VentanaAgregarSolicitud(Datos.getSolicitudes(), Datos.getProductos(), Datos.getEmpleadoLogueado());
                dispose();
            }
        });

        panelRegistrar.add(btnAgregarProveedor);
        panelRegistrar.add(btnAgregarProducto);
        panelRegistrar.add(btnRegistrarSolicitud);

        Panel panelListar = new Panel(new GridLayout(0, 1, 0, 10));
        panelListar.add(new Label("Listar:", Label.CENTER));
        Button btnListarProvedores = new Button("Proveedores");
        btnListarProvedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProveedor ventanaListarProveedor = new VentanaListarProveedor(Datos.getProvedores());
                dispose();
            }
        });

        Button btnListarProductos = new Button("Productos");
        btnListarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarProducto ventanaListarProducto = new VentanaListarProducto(Datos.getProductos());
                dispose();
            }
        });

        Button btnListarSolicitudesCompras = new Button("Solicitudes de Compra");
        btnListarSolicitudesCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaListarSolicitud ventanaListarSolicitud = new VentanaListarSolicitud(Datos.getSolicitudes());
                dispose();
            }
        });

        panelListar.add(btnListarProvedores);
        panelListar.add(btnListarProductos);
        panelListar.add(btnListarSolicitudesCompras);

        Panel panelBuscarID = new Panel(new GridLayout(0, 1, 0, 10));
        panelBuscarID.add(new Label("Buscar por ID:", Label.CENTER));
        Button btnBuscarProveedorPorId = new Button("Proveedor");
        btnBuscarProveedorPorId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarProveedorID ventanaBuscarProveedorID = new VentanaBuscarProveedorID(Datos.getProvedores());
                dispose();
            }
        });

        Button btnBuscarProductoPorId = new Button("Producto");
        btnBuscarProductoPorId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarProductoID ventanaBuscarProductoID = new VentanaBuscarProductoID(Datos.getProductos());
                dispose();
            }
        });

        Button btnBuscarSolicitudID = new Button("Solicitud");
        btnBuscarSolicitudID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarSolicitudID ventanaBuscarSolicitudID = new VentanaBuscarSolicitudID(Datos.getSolicitudes());
                dispose();
            }
        });
        panelBuscarID.add(btnBuscarProveedorPorId);
        panelBuscarID.add(btnBuscarProductoPorId);
        panelBuscarID.add(btnBuscarSolicitudID);

        Panel panelBuscarNombre = new Panel(new GridLayout(0, 1, 0, 10));
        panelBuscarNombre.add(new Label("Buscar por Nombre:", Label.CENTER));
        Button btnBuscarProveedorPorNombre = new Button("Proveedor");
        btnBuscarProveedorPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarProveedorNombre ventanaBuscarProveedorNombre = new VentanaBuscarProveedorNombre(Datos.getProvedores());
                dispose();
            }
        });
        Button btnBuscarProductoPorNombre = new Button("Producto");
        btnBuscarProductoPorNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarProductoNombre ventanaBuscarProductoNombre = new VentanaBuscarProductoNombre(Datos.getProductos());
                dispose();
            }
        });
        panelBuscarNombre.add(btnBuscarProveedorPorNombre);
        panelBuscarNombre.add(btnBuscarProductoPorNombre);

        Panel panelOtros = new Panel(new GridLayout(0, 1, 0, 10));
        panelOtros.add(new Label("Otros:", Label.CENTER));
        Button btnBuscarSolicitudEstado = new Button("Buscar Solicitud por Estado");
        btnBuscarSolicitudEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaBuscarSolicitudEstado ventanaBuscarSolicitudEstado = new VentanaBuscarSolicitudEstado(Datos.getSolicitudes());
                dispose();
            }
        });

        Button btnCambiarEstadoSolicitud = new Button("Cambiar estado solicitud");
        btnCambiarEstadoSolicitud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCambiarEstadoSolicitud ventanaCambiarEstadoSolicitud = new VentanaCambiarEstadoSolicitud(Datos.getSolicitudes());
                dispose();
            }
        });

        panelOtros.add(btnBuscarSolicitudEstado);
        panelOtros.add(btnCambiarEstadoSolicitud);

        panelGrid.add(panelRegistrar);
        panelGrid.add(panelListar);
        panelGrid.add(panelBuscarID);
        panelGrid.add(panelBuscarNombre);
        panelGrid.add(panelOtros);

        Panel panelCentro = new Panel(new BorderLayout());
        Panel panelGridContainer = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        panelGridContainer.add(panelGrid);
        panelCentro.add(panelGridContainer, BorderLayout.NORTH);

        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setPreferredSize(new Dimension(200, 40));
        btnCerrarSesion.addActionListener(e -> {
            new VentanaLogIn();
            dispose();
        });
        Panel panelCerrarSesion = new Panel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCerrarSesion.add(btnCerrarSesion);
        panelCentro.add(panelCerrarSesion, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        setVisible(true);
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