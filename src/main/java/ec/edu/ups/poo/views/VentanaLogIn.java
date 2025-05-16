package ec.edu.ups.poo.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogIn extends Frame {

    private TextField txtUsuarioCorre;
    private TextField txtContrasena;

    private Button btnLogIn;
    private Button btnSalir;

    public VentanaLogIn() {

        setTitle("Log In");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panel = new Panel();
        Label titulo = new Label("Inicio Sesión");

        txtUsuarioCorre = new TextField(10);
        txtContrasena = new TextField(10);

        panel.setLayout(new GridLayout(3, 2));
        panel.add(new Label("Usuario / Correo: "));
        panel.add(txtUsuarioCorre);
        panel.add(new Label("Contraseña: "));
        panel.add(txtContrasena);

        btnLogIn = new Button("Log In");
        btnSalir = new Button("Salir");

        panel.add(btnLogIn);
        panel.add(btnSalir);

        add(panel, BorderLayout.CENTER);

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMenu ventanaMenu = new VentanaMenu();
                ventanaMenu.setVisible(true);
                dispose();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaLogIn();
    }
}