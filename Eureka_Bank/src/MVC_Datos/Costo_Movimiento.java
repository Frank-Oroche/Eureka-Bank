package MVC_Datos;

public class Costo_Movimiento {

    private String codigo_moneda;
    private Float importe;

    public Costo_Movimiento() {
    }

    public Costo_Movimiento(String codigo_moneda, Float importe) {
        this.codigo_moneda = codigo_moneda;
        this.importe = importe;
    }

    public String getCodigo_moneda() {
        return codigo_moneda;
    }

    public void setCodigo_moneda(String codigo_moneda) {
        this.codigo_moneda = codigo_moneda;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }
}
