package MVC_Datos;

public class Parametro {

    private String codigo;
    private String descripcion;
    private String valor;
    private String estado;

    public Parametro(String codigo, String descripcion, String valor, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
