package ec.edu.ups.poo.models;

import ec.edu.ups.poo.models.Enums.Rol;

public class Empleado extends Persona{

    private Departamento departamento;
    private Rol cargo;
    private String username;
    private String password;

    public Empleado(String cedula, String nombre, String apellido, String telefono, String direccion, String correo, Departamento departamento, Rol cargo, String username, String password) {
        super(cedula, nombre, apellido, telefono, direccion, correo);
        this.departamento = departamento;
        this.cargo = cargo;
        this.username = username;
        this.password = password;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Rol getCargo() {
        return cargo;
    }

    public void setCargo(Rol cargo) {
        this.cargo = cargo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                super.toString() +
                "\n\t\t\t\t\t|| departamento=" + departamento.getName() +
                " || cargo=" + cargo +
                " ||username='" + username + '\'' + " ||";
    }
}
