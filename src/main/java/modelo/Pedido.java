package modelo;

public class Pedido {

    private int id_pedido;
    private String direccion;
    private String tipo;

    public Pedido(int id_pedido, String direccion, String tipo) {
        this.id_pedido = id_pedido;
        this.direccion = direccion;
        this.tipo = tipo;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Pedido " + id_pedido + " - " + direccion;
    }
}


