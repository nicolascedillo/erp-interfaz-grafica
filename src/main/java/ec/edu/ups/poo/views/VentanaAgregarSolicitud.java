package ec.edu.ups.poo.views;

import ec.edu.ups.poo.models.Producto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaAgregarSolicitud extends Frame {

    private List<Producto> productos;
    private TextField txtID, txtComentario;

    public VentanaAgregarSolicitud() {
        setTitle("Agregar Solicitud");
        setSize(800, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label titulo = new Label("Registrar Solicitud");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        Panel panelSuperior = new Panel(new FlowLayout());
        panelSuperior.add(titulo);
        Button botonSalir = new  Button("Salir");
        panelSuperior.add(botonSalir);
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VentanaMenu ventanaMenu = new VentanaMenu();
            }
        });
        add(panelSuperior, BorderLayout.NORTH);

        Panel panelFormulario = new Panel();
        panelFormulario.setLayout(new GridLayout(5,2,5,5));

        panelFormulario.add(new Label("ID: "));
        txtID = new TextField();
        panelFormulario.add(txtID);

        panelFormulario.add(new Label("Día: "));
        Choice choiceDia = new Choice();

        for (int i = 1; i <= 30; i++) {
            choiceDia.add(String.valueOf(i));
            panelFormulario.add(choiceDia);

        }

        panelFormulario.add(new Label("Mes: "));
        Choice choiceMes = new Choice();

        for (int i = 1; i <= 12; i++) {
            choiceMes.add(String.valueOf(i));
            panelFormulario.add(choiceMes);

        }

        panelFormulario.add(new Label("Año: "));
        Choice choiceAnio = new Choice();

        choiceAnio.add("2024");
        choiceAnio.add("2025");
        choiceAnio.add("2026");
        panelFormulario.add(choiceAnio);

        panelFormulario.add(new Label("Comentario: "));
        txtComentario = new TextField();
        panelFormulario.add(txtComentario);

        add(panelFormulario, BorderLayout.CENTER);



        setVisible(true);

    }
}
