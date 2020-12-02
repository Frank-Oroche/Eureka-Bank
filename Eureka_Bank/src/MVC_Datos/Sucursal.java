package MVC_Datos;

public class Sucursal {

    private String sucursal_codigo;
    private String nombre;
    private String ciudad;
    private String direccion;
    private int n_cuentas;

    public Sucursal(String sucursal_codigo, String nombre, String ciudad, String direccion, int n_cuentas) {
        this.sucursal_codigo = sucursal_codigo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.n_cuentas = n_cuentas;
    }

    public Sucursal() {
    }

    public String getSucursal_codigo() {
        return sucursal_codigo;
    }

    public void setSucursal_codigo(String sucursal_codigo) {
        this.sucursal_codigo = sucursal_codigo;
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

    public int getN_cuentas() {
        return n_cuentas;
    }

    public void setN_cuentas(int n_cuentas) {
        this.n_cuentas = n_cuentas;
    }
}
