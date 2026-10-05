package modelo;

public class Pedido {

    private int id_pedido;
    private String direccion;
    private String tipo;
    private EstadoPedido estado;

    public Pedido(int id_pedido, String direccion, String tipo) {
        this.id_pedido = id_pedido;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(int id_pedido, String direccion, String tipo, EstadoPedido estado) {
        this.id_pedido = id_pedido;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido " + id_pedido + " - " + direccion + " - " + tipo + " - " + estado;
    }
}


