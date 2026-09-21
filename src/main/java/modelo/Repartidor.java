package modelo;

public class Repartidor {

    private int idRepartidor;
    private String nombre;

    public Repartidor(int idRepartidor, String nombre) {
        this.idRepartidor = idRepartidor;
        this.nombre = nombre;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " - ID: " + idRepartidor;
    }
}
