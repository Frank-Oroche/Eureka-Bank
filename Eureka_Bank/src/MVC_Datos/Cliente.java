package MVC_Datos;

public class Cliente {

    private String cliente_codigo;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;
    private String DNI;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;

    public Cliente() {
    }

    public Cliente(String cliente_codigo, String apellido_paterno, String apellido_materno, String nombres, String DNI, String ciudad, String direccion, String telefono, String email) {
        this.cliente_codigo = cliente_codigo;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
        this.DNI = DNI;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCliente_codigo(String cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
