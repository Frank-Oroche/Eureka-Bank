package MVC_Datos;

public class Cargo_Mantenimiento {

    private String moneda_codigo;
    private Float cargMontoMaximo;
    private Float cargImporte;

    public Cargo_Mantenimiento() {
    }

    
    public Cargo_Mantenimiento(String moneda_codigo, Float cargMontoMaximo, Float cargImporte) {
        this.moneda_codigo = moneda_codigo;
        this.cargMontoMaximo = cargMontoMaximo;
        this.cargImporte = cargImporte;
    }

    public String getMoneda_codigo() {
        return moneda_codigo;
    }

    public void setMoneda_codigo(String moneda_codigo) {
        this.moneda_codigo = moneda_codigo;
    }

    public Float getCargMontoMaximo() {
        return cargMontoMaximo;
    }

    public void setCargMontoMaximo(Float cargMontoMaximo) {
        this.cargMontoMaximo = cargMontoMaximo;
    }

    public Float getCargImporte() {
        return cargImporte;
    }

    public void setCargImporte(Float cargImporte) {
        this.cargImporte = cargImporte;
    }
}
