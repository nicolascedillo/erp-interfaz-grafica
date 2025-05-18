package ec.edu.ups.poo.views;

import java.awt.*;

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
        btnAgregarProveedor.addActionListener(e -> {
            new VentanaAgregarProveedor(Datos.getProvedores());
            dispose();
        });
        Button btnAgregarProducto = new Button("Producto");
        btnAgregarProducto.addActionListener(e -> {
            new VentanaAgregarProducto();
            dispose();
        });




        Button btnRegistrarSolicitud = new Button("Solicitud");
        btnRegistrarSolicitud.addActionListener(e -> {

        });



        panelRegistrar.add(btnAgregarProveedor);
        panelRegistrar.add(btnAgregarProducto);
        panelRegistrar.add(btnRegistrarSolicitud);

        Panel panelListar = new Panel(new GridLayout(0, 1, 0, 10));
        panelListar.add(new Label("Listar:", Label.CENTER));
        Button btnListarProvedores = new Button("Proveedores");
        btnListarProvedores.addActionListener(e -> {
            new VentanaListarProveedor(Datos.getProvedores());
            dispose();
        });
        Button btnListarProductos = new Button("Productos");
        btnListarProductos.addActionListener(e -> {
            new VentanaListarProducto(Datos.getProductos());
            dispose();
        });
        Button btnListarSolicitudesCompras = new Button("Solicitudes de Compra");
        btnListarSolicitudesCompras.addActionListener(e -> {
            new VentanaListarSolicitud(Datos.getSolicitudes());
            dispose();
        });
        panelListar.add(btnListarProvedores);
        panelListar.add(btnListarProductos);
        panelListar.add(btnListarSolicitudesCompras);

        Panel panelBuscarID = new Panel(new GridLayout(0, 1, 0, 10));
        panelBuscarID.add(new Label("Buscar por ID:", Label.CENTER));
        Button btnBuscarProveedorPorId = new Button("Proveedor");
        btnBuscarProveedorPorId.addActionListener(e -> {
            new VentanaBuscarProveedorID(Datos.getProvedores());
            dispose();
        });
        Button btnBuscarProductoPorId = new Button("Producto");
        btnBuscarProductoPorId.addActionListener(e -> {
            new VentanaBuscarProductoID(Datos.getProductos());
            dispose();
        });
        Button btnBuscarSolicitudID = new Button("Solicitud");
        btnBuscarSolicitudID.addActionListener(e -> {
            new VentanaBuscarSolicitudID(Datos.getSolicitudes());
            dispose();
        });
        panelBuscarID.add(btnBuscarProveedorPorId);
        panelBuscarID.add(btnBuscarProductoPorId);
        panelBuscarID.add(btnBuscarSolicitudID);

        Panel panelBuscarNombre = new Panel(new GridLayout(0, 1, 0, 10));
        panelBuscarNombre.add(new Label("Buscar por Nombre:", Label.CENTER));
        Button btnBuscarProveedorPorNombre = new Button("Proveedor");
        btnBuscarProveedorPorNombre.addActionListener(e -> {
            new VentanaBuscarProveedorNombre(Datos.getProvedores());
            dispose();
        });
        Button btnBuscarProductoPorNombre = new Button("Producto");
        btnBuscarProductoPorNombre.addActionListener(e -> {
            new VentanaBuscarProductoNombre(Datos.getProductos());
            dispose();
        });
        panelBuscarNombre.add(btnBuscarProveedorPorNombre);
        panelBuscarNombre.add(btnBuscarProductoPorNombre);

        Panel panelOtros = new Panel(new GridLayout(0, 1, 0, 10));
        panelOtros.add(new Label("Otros:", Label.CENTER));
        Button btnBuscarSolicitudEstado = new Button("Buscar Solicitud por Estado");
        btnBuscarSolicitudEstado.addActionListener(e -> {
            new VentanaBuscarSolicitudEstado(Datos.getSolicitudes());
            dispose();
        });
        Button btnCambiarEstadoSolicitud = new Button("Cambiar estado solicitud");
        btnCambiarEstadoSolicitud.addActionListener(e -> {
            new VentanaCambiarEstadoSolicitud(Datos.getSolicitudes());
            dispose();
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
}