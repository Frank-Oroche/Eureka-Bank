package MVC_Datos;

public class Interes_Mensual {

    private String moneda_codigo;
    private Float importe;

    public Interes_Mensual() {
    }

    
    public Interes_Mensual(String moneda_codigo, Float importe) {
        this.moneda_codigo = moneda_codigo;
        this.importe = importe;
    }

    public String getMoneda_codigo() {
        return moneda_codigo;
    }

    public void setMoneda_codigo(String moneda_codigo) {
        this.moneda_codigo = moneda_codigo;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }
}
