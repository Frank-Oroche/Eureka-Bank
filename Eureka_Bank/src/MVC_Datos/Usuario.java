package MVC_Datos;

public class Usuario {

    private String empleado_codigo;
    private String usuario;
    private String clave;
    private String estado;
    private String numero_permisos;
    private String permisos[];

    public Usuario() {
    }

    public Usuario(String empleado_codigo, String usuario, String clave, String estado) {
        this.empleado_codigo = empleado_codigo;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }

    public String getEmpleado_codigo() {
        return empleado_codigo;
    }

    public void setEmpleado_codigo(String empleado_codigo) {
        this.empleado_codigo = empleado_codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
