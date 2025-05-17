package ec.edu.ups.poo.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogIn extends Frame {

    private TextField txtUsuarioCorre;
    private TextField txtContrasena;

    private Button btnLogIn;
    private Button btnSalir;

    Datos datos = new Datos();

    public VentanaLogIn() {

        setTitle("Log In");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panel = new Panel();
        Label titulo = new Label("Sistema de Gestio ERP");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);
        txtUsuarioCorre = new TextField(20);
        txtContrasena = new TextField(20);
        panel.setLayout(new GridLayout(3, 2));

        Panel panelUsuario = new Panel(new FlowLayout(FlowLayout.RIGHT,0,50));
        panelUsuario.add(new Label("Usuario / Correo: "));
        panel.add(panelUsuario);

        Panel panelUsuarioIngresar = new Panel(new FlowLayout(FlowLayout.LEFT,0,50));
        panelUsuarioIngresar.add(txtUsuarioCorre);
        panel.add(panelUsuarioIngresar);

        Panel panelContrasena = new Panel(new FlowLayout(FlowLayout.RIGHT,0,50));
        panelContrasena.add(new Label("Contraseña: "));
        panel.add(panelContrasena);

        Panel panelContrasenaIngresar = new Panel(new FlowLayout(FlowLayout.LEFT,0,50));
        panelContrasenaIngresar.add(txtContrasena);
        panel.add(panelContrasenaIngresar);

        Panel panelLogIn = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnLogIn = new Button("Log In");
        panelLogIn.add(btnLogIn);
        panel.add(panelLogIn);

        Panel panelSalir = new Panel(new FlowLayout(FlowLayout.CENTER));
        btnSalir = new Button("Salir");
        panelSalir.add(btnSalir);
        panel.add(panelSalir);

        add(panel, BorderLayout.CENTER);

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validarUsuario()){
                    VentanaMenu ventanaMenu = new VentanaMenu();
                    dispose();
                }

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

    public boolean validarUsuario(){
         if (txtUsuarioCorre.getText().equals(datos.getNicolas().getUsername()) && txtContrasena.getText().equals(datos.getNicolas().getPassword())) {
            return true;
        } else if (txtUsuarioCorre.getText().equals(datos.getMateo().getUsername()) && txtContrasena.getText().equals(datos.getMateo().getPassword())) {
             return true;
         }
        txtUsuarioCorre.setBackground(Color.RED);
        txtContrasena.setBackground(Color.RED);
        return false;



    }

}