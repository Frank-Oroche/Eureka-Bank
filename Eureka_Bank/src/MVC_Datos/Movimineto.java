package MVC_Datos;

public class Movimineto {

    private String cuenta_codigo;
    private int id;
    private String fecha;
    private String empleado_codigo;
    private String tipo_movimiento;
    private Float importe;
    private String cuenta_referencial;

    public Movimineto(String cuenta_codigo, int id, String fecha, String empleado_codigo, String tipo_movimiento, Float importe, String cuenta_referencial) {
        this.cuenta_codigo = cuenta_codigo;
        this.id = id;
        this.fecha = fecha;
        this.empleado_codigo = empleado_codigo;
        this.tipo_movimiento = tipo_movimiento;
        this.importe = importe;
        this.cuenta_referencial = cuenta_referencial;
    }

    public String getCuenta_codigo() {
        return cuenta_codigo;
    }

    public void setCuenta_codigo(String cuenta_codigo) {
        this.cuenta_codigo = cuenta_codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmpleado_codigo() {
        return empleado_codigo;
    }

    public void setEmpleado_codigo(String empleado_codigo) {
        this.empleado_codigo = empleado_codigo;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getCuenta_referencial() {
        return cuenta_referencial;
    }

    public void setCuenta_referencial(String cuenta_referencial) {
        this.cuenta_referencial = cuenta_referencial;
    }
}
