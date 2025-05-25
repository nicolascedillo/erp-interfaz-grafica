package ec.edu.ups.poo.views;
import ec.edu.ups.poo.models.Datos;

import java.awt.*;
import java.awt.event.*;

public class VentanaLogIn extends Frame {

    private TextField txtUsuarioCorre;
    private TextField txtContrasena;
    private Button btnLogIn;
    private Button btnSalir;

    public VentanaLogIn() {
        setTitle("Log In");
        setSize(700, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label titulo = new Label("Sistema de Gestión ERP", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 20));

        Panel panelFormulario = new Panel(new GridLayout(2, 2, 0, 10));
        Label lblUsuario = new Label("Usuario:");
        txtUsuarioCorre = new TextField(22);
        Label lblContrasena = new Label("Contraseña:");
        txtContrasena = new TextField(22);
        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuarioCorre);
        panelFormulario.add(lblContrasena);
        panelFormulario.add(txtContrasena);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 40, 10));
        btnLogIn = new Button("Log In");
        btnLogIn.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLogIn.setPreferredSize(new Dimension(110, 35));
        btnSalir = new Button("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 12));
        btnSalir.setPreferredSize(new Dimension(110, 35));
        panelBotones.add(btnLogIn);
        panelBotones.add(btnSalir);

        panelCentral.add(panelFormulario);
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarUsuario()) {
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
        if (txtUsuarioCorre.getText().equals(Datos.getNicolas().getUsername()) &&
                txtContrasena.getText().equals(Datos.getNicolas().getPassword())) {

            Datos.setEmpleadoLogueado(Datos.getNicolas());
            return true;

        } else if (txtUsuarioCorre.getText().equals(Datos.getMateo().getUsername()) &&
                txtContrasena.getText().equals(Datos.getMateo().getPassword())) {

            Datos.setEmpleadoLogueado(Datos.getMateo());
            return true;
        }
        mostrarMensajeTemp("Usuario o contraseña Incorrecto");
        return false;
    }

    private void mostrarMensajeTemp(String mensaje){
        Dialog dialogo = new Dialog(this, "Error", true);
        Label label = new Label(mensaje, Label.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        dialogo.add(label);
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(this);

        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialogo.setVisible(false);
                dialogo.dispose();
            }
        });
        dialogo.setVisible(true);
    }

}