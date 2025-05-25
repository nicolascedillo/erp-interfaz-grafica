package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Datos;
import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoConIva;
import ec.edu.ups.poo.models.ProductoSinIva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.List;

public class VentanaListarProducto {

    public VentanaListarProducto(List<Producto> productos) {

        Frame frame = new Frame("Lista de Productos");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label titulo = new Label("Listar Productos");
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
        panelPrincipal.setPreferredSize(new Dimension(560, productos.size() * 200));

        for (Producto producto: productos){
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700,180));

            panel.add(new Label("ID:"));
            TextField txtId = new TextField(producto.getId());
            txtId.setEditable(false);
            panel.add(txtId);

            panel.add(new Label("Nombre:"));
            TextField txtNombre = new TextField(producto.getNombre());
            txtNombre.setEditable(false);
            panel.add(txtNombre);

            panel.add(new Label("Precio Unitario:"));
            TextField txtPrecioUnitario = new TextField(String.valueOf(producto.getPrecioUnitario()));
            txtPrecioUnitario.setEditable(false);
            panel.add(txtPrecioUnitario);

            panel.add(new Label("Marca:"));
            TextField txtMarca = new TextField(producto.getMarca());
            txtMarca.setEditable(false);
            panel.add(txtMarca);

            TextField txtPorcentajeIVA;
            if (producto instanceof ProductoConIva){
                ProductoConIva productoConIva = (ProductoConIva) producto;

                panel.add(new Label("Porcentaje IVA: "));
                txtPorcentajeIVA = new TextField(String.valueOf(productoConIva.getFestividad().getPorcentaje()));
                txtPorcentajeIVA.setEditable(false);
                panel.add(txtPorcentajeIVA);

                panel.add(new Label("Impuesto: "));
                TextField txtImpuesto = new TextField(String.valueOf(productoConIva.calcularIVA()));
                txtImpuesto.setEditable(false);
                panel.add(txtImpuesto);

                panel.add(new Label("Motivo del porcentaje: "));
                TextField txtMotivoDelPorcentaje = new TextField(productoConIva.getFestividad().getFecha());
                txtMotivoDelPorcentaje.setEditable(false);
                panel.add(txtMotivoDelPorcentaje);

            } else{
                ProductoSinIva productoSinIva = (ProductoSinIva) producto;

                panel.add(new Label("Porcentaje IVA: "));
                txtPorcentajeIVA = new TextField(String.valueOf(productoSinIva.calcularIVA()));
                txtPorcentajeIVA.setEditable(false);
                panel.add(txtPorcentajeIVA);

                panel.add(new Label("Motivo del porcentaje: "));
                TextField txtMotivo = new TextField(productoSinIva.getMotivo());
                txtMotivo.setEditable(false);
                panel.add(txtMotivo);
            }

            panel.add(new Label("---------------------------------------------------------------------------------"));
            panel.add(new Label("---------------------------------------------------------------------------------"));

            panelPrincipal.add(panel);
        }

        scrollPane.add(panelPrincipal);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
