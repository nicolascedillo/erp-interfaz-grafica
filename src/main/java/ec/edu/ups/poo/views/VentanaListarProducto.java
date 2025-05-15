package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Producto;
import ec.edu.ups.poo.models.ProductoConIva;
import ec.edu.ups.poo.models.ProductoSinIva;

import java.util.List;
import java.awt.*;

public class VentanaListarProducto {
    private Frame frame;
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtPrecioUnitario;
    private TextField txtMarca;
    private TextField txtPorcentajeIVA;

    //Producto con iva
    private TextField txtImpuesto;
    private TextField txtMotivoDelPorcentaje;
    //Producto sin iva
    private TextField txtMotivo;

    private List<Producto> productos;

    public VentanaListarProducto(List<Producto> productos) {
        this.productos = productos;

        frame = new Frame("Lista de Productos");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        Panel panelPrincipal = new Panel(new FlowLayout(FlowLayout.LEFT));
        Label titulo = new Label("Listar Productos");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titulo, BorderLayout.NORTH);

        ScrollPane scrollPane = new ScrollPane (ScrollPane.SCROLLBARS_AS_NEEDED);
        scrollPane.setSize(580, 350);
        panelPrincipal.setPreferredSize(new Dimension(560, productos.size() * 160));

        for (Producto producto: productos){
            Panel panel = new Panel(new GridLayout(0, 2));
            panel.setPreferredSize(new Dimension(700,180));
            panel.add(new Label("ID:"));
            txtId = new TextField(String.valueOf(producto.getId()));
            txtId.setEditable(false);
            panel.add(txtId);
            panel.add(new Label("Nombre:"));
            txtNombre = new TextField(producto.getNombre());
            txtNombre.setEditable(false);
            panel.add(txtNombre);
            panel.add(new Label("Precio Unitario:"));
            txtPrecioUnitario = new TextField(String.valueOf(producto.getPrecioUnitario()));
            txtPrecioUnitario.setEditable(false);
            panel.add(txtPrecioUnitario);
            panel.add(new Label("Marca:"));
            txtMarca = new TextField(producto.getMarca());
            txtMarca.setEditable(false);
            panel.add(txtMarca);
            if (producto instanceof ProductoConIva){
                ProductoConIva productoConIva = (ProductoConIva) producto;
                panel.add(new Label("Porcentaje IVA: "));
                txtPorcentajeIVA = new TextField(String.valueOf(productoConIva.getFestividad().getPorcentaje()));
                txtPorcentajeIVA.setEditable(false);
                panel.add(txtPorcentajeIVA);
                panel.add(new Label("Impuesto: "));
                txtImpuesto = new TextField(String.valueOf(productoConIva.calcularIVA()));
                txtImpuesto.setEditable(false);
                panel.add(txtImpuesto);
                panel.add(new Label("Motivo del porcentaje: "));
                txtMotivoDelPorcentaje = new TextField(productoConIva.getFestividad().getFecha());
                txtMotivoDelPorcentaje.setEditable(false);
                panel.add(txtMotivoDelPorcentaje);

            } else{
                ProductoSinIva productoSinIva = (ProductoSinIva) producto;
                panel.add(new Label("Porcentaje IVA: "));
                txtPorcentajeIVA = new TextField(String.valueOf(productoSinIva.calcularIVA()));
                txtPorcentajeIVA.setEditable(false);
                panel.add(txtPorcentajeIVA);
                panel.add(new Label("Motivo del porcentaje: "));
                txtMotivo = new TextField(productoSinIva.getMotivo());
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
