package ec.edu.ups.poo.models;

import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private String name;
    private List<Empleado> empleados;

    public Departamento( String name) {
        this.empleados = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                " name='" + name + '\'' +
                " empleados=" + empleados +
                '}';
    }
}
