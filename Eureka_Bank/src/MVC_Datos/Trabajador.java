package MVC_Datos;

public class Trabajador {

    private String empleado_codigo;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombre;
    private String ciudad;
    private String direccion;

    public Trabajador() {
    }

    public Trabajador(String empleado_codigo, String apellido_paterno, String apellido_materno, String nombre, String ciudad, String direccion) {
        this.empleado_codigo = empleado_codigo;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    public String getEmpleado_codigo() {
        return empleado_codigo;
    }

    public void setEmpleado_codigo(String empleado_codigo) {
        this.empleado_codigo = empleado_codigo;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
