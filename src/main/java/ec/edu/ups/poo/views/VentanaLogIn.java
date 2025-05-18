package ec.edu.ups.poo.views;
import java.awt.*;
import java.awt.event.*;

public class VentanaLogIn extends Frame {

    private TextField txtUsuarioCorre;
    private TextField txtContrasena;
    private Button btnLogIn;
    private Button btnSalir;
    private Button btnVerContrasena;
    private boolean contrasenaVisible;
    Datos datos = new Datos();

    public VentanaLogIn() {
        contrasenaVisible = false;
        setTitle("Log In");
        setSize(500, 250);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label titulo = new Label("Sistema de Gestio ERP", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        Panel panelCentral = new Panel(new FlowLayout(FlowLayout.CENTER, 1, 20));

        Panel panelUsuario = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        Label lblUsuario = new Label("Correo:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUsuarioCorre = new TextField(22);
        txtUsuarioCorre.setFont(new Font("Arial", Font.PLAIN, 16));
        panelUsuario.add(lblUsuario);
        panelUsuario.add(txtUsuarioCorre);

        Panel panelContrasena = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        Label lblContrasena = new Label("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContrasena = new TextField(18);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        txtContrasena.setEchoChar('●');
        btnVerContrasena = new Button("Mostrar");
        btnVerContrasena.setFont(new Font("-", Font.PLAIN, 12));
        btnVerContrasena.setPreferredSize(new Dimension(50, 25));
        panelContrasena.add(lblContrasena);
        panelContrasena.add(txtContrasena);
        panelContrasena.add(btnVerContrasena);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        btnLogIn = new Button("Log In");
        btnLogIn.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogIn.setPreferredSize(new Dimension(110, 35));
        btnSalir = new Button("Salir");
        btnSalir.setFont(new Font("Arial", Font.PLAIN, 15));
        btnSalir.setPreferredSize(new Dimension(110, 35));
        panelBotones.add(btnLogIn);
        panelBotones.add(btnSalir);

        panelCentral.add(panelUsuario);
        panelCentral.add(panelContrasena);
        panelCentral.add(panelBotones);

        add(panelCentral, BorderLayout.CENTER);

        btnVerContrasena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contrasenaVisible = !contrasenaVisible;
                if (contrasenaVisible) {
                    txtContrasena.setEchoChar((char)0);
                    btnVerContrasena.setLabel("Ocultar");
                } else {
                    txtContrasena.setEchoChar('●');
                    btnVerContrasena.setLabel("Mostrar");
                }
            }
        });

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