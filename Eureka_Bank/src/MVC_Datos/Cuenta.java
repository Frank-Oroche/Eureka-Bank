package MVC_Datos;

public class Cuenta {

    private String n_cuenta;
    private String moneda;
    private String sucursal;
    private String empleado;
    private String cliente;
    private Float saldo;
    private String fecha_creacion;
    private String estado;
    private int n_movientos;
    private String clave;

    public Cuenta() {
    }

    public Cuenta(String n_cuenta, String moneda, String sucursal, String empleado, String cliente, Float saldo, String fecha_creacion, String estado, int n_movientos, String clave) {
        this.n_cuenta = n_cuenta;
        this.moneda = moneda;
        this.sucursal = sucursal;
        this.empleado = empleado;
        this.cliente = cliente;
        this.saldo = saldo;
        this.fecha_creacion = fecha_creacion;
        this.estado = estado;
        this.n_movientos = n_movientos;
        this.clave = clave;
    }

    public String getN_cuenta() {
        return n_cuenta;
    }

    public void setN_cuenta(String n_cuenta) {
        this.n_cuenta = n_cuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getN_movientos() {
        return n_movientos;
    }

    public void setN_movientos(int n_movientos) {
        this.n_movientos = n_movientos;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
