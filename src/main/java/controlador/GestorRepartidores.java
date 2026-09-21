package controlador;

import java.util.ArrayList;
import modelo.Repartidor;

public class GestorRepartidores {

    private ArrayList<Repartidor> repartidores;

    public GestorRepartidores() {
        repartidores = new ArrayList<>();

        repartidores.add(new Repartidor(1, "Juan Pérez"));
        repartidores.add(new Repartidor(2, "Carlos González"));
        repartidores.add(new Repartidor(3, "Pedro Soto"));
    }

    public ArrayList<Repartidor> getRepartidores() {
        return repartidores;
    }
}

