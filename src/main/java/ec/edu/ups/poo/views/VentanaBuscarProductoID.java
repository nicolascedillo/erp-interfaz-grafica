package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoConIva;
import ec.edu.ups.poo.models.ProductoSinIva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBuscarProductoID {
    private Frame frame;
    private Panel panelPrincipal;
    private List<Producto> productos;

    public VentanaBuscarProductoID(List<Producto> productos) {
        this.productos = productos;
        frame = new Frame("Buscar Producto");
        frame.setSize(725, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        panelPrincipal = new Panel(new FlowLayout());
        Label titulo = new Label("Buscar Producto por ID");
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
        panel.add(new Label("Ingrese el ID del producto a buscar:"));
        TextField txtId = new TextField(10);
        panel.add(txtId);

        Button botonBuscar = new  Button("Buscar");
        Panel panelBuscar = new Panel(new FlowLayout(FlowLayout.RIGHT));
        panelBuscar.add(botonBuscar);
        panel.add(panelBuscar);
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                Producto encontrado = BuscarProductoID(id);
                if (encontrado != null) {
                    Panel panelEncontrado = new Panel(new GridLayout(0, 2));
                    panelEncontrado.setPreferredSize(new Dimension(700,180));
                    panelEncontrado.add(new Label("ID:"));
                    TextField txtId = new TextField(String.valueOf(encontrado.getId()));
                    txtId.setEditable(false);
                    panelEncontrado.add(txtId);
                    panelEncontrado.add(new Label("Nombre:"));
                    TextField txtNombre = new TextField(encontrado.getNombre());
                    txtNombre.setEditable(false);
                    panelEncontrado.add(txtNombre);
                    panelEncontrado.add(new Label("Precio Unitario:"));
                    TextField txtPrecioUnitario = new TextField(String.valueOf(encontrado.getPrecioUnitario()));
                    txtPrecioUnitario.setEditable(false);
                    panelEncontrado.add(txtPrecioUnitario);
                    panelEncontrado.add(new Label("Marca:"));
                    TextField txtMarca = new TextField(encontrado.getMarca());
                    txtMarca.setEditable(false);
                    panelEncontrado.add(txtMarca);
                    if (encontrado instanceof ProductoConIva){
                        ProductoConIva productoConIva = (ProductoConIva) encontrado;
                        panelEncontrado.add(new Label("Porcentaje IVA: "));
                        TextField txtPorcentajeIVA = new TextField(String.valueOf(productoConIva.getFestividad().getPorcentaje()));
                        txtPorcentajeIVA.setEditable(false);
                        panelEncontrado.add(txtPorcentajeIVA);
                        panelEncontrado.add(new Label("Impuesto: "));
                        TextField txtImpuesto = new TextField(String.valueOf(productoConIva.calcularIVA()));
                        txtImpuesto.setEditable(false);
                        panelEncontrado.add(txtImpuesto);
                        panelEncontrado.add(new Label("Motivo del porcentaje: "));
                        TextField txtMotivoDelPorcentaje = new TextField(productoConIva.getFestividad().getFecha());
                        txtMotivoDelPorcentaje.setEditable(false);
                        panelEncontrado.add(txtMotivoDelPorcentaje);
                    }else{
                        ProductoSinIva productoSinIva = (ProductoSinIva) encontrado;
                        panelEncontrado.add(new Label("Porcentaje IVA: "));
                        TextField txtPorcentajeIVA = new TextField(String.valueOf(productoSinIva.calcularIVA()));
                        txtPorcentajeIVA.setEditable(false);
                        panelEncontrado.add(txtPorcentajeIVA);
                        panelEncontrado.add(new Label("Motivo del porcentaje: "));
                        TextField txtMotivo = new TextField(productoSinIva.getMotivo());
                        txtMotivo.setEditable(false);
                        panelEncontrado.add(txtMotivo);
                    }

                    panelPrincipal.add(panelEncontrado);
                    panelPrincipal.revalidate();
                    panelPrincipal.repaint();
                } else{

                    Panel panelNoEncontrado = new Panel(new FlowLayout(FlowLayout.LEFT));
                    Label txtNoEncontrado = new  Label("No existe producto con el ID: " + id);
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

    public Producto BuscarProductoID(int id) {
        for (Producto producto : productos) {
            if(producto.getId() == id){
                return producto;
            }
        }
        return null;
    }
}
