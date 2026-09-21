package vista;

import controlador.GestorPedidos;
import controlador.GestorRepartidores;
import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame {

    private GestorPedidos gestorPedidos;
    private GestorRepartidores gestorRepartidores;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnAsignar;

    public VentanaPrincipal() {
        gestorPedidos = new GestorPedidos();
        gestorRepartidores = new GestorRepartidores();

        setTitle("SpeedFast");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnRegistrar = new JButton("Registrar pedido");
        btnListar = new JButton("Listar pedidos");
        btnAsignar = new JButton("Agisnar repartidor / Iniciar entrega");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        panel.add(btnRegistrar);
        panel.add(btnListar);
        panel.add(btnAsignar);

        add(panel);

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventanaRegistro = new VentanaRegistroPedido(gestorPedidos);
            ventanaRegistro.setVisible(true);

        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos ventanaLista = new VentanaListaPedidos(gestorPedidos);
            ventanaLista.setVisible(true);
        });

        btnAsignar.addActionListener(e -> {
            VentanaAsignarRepartidor ventanaAsignar =
                    new VentanaAsignarRepartidor(gestorPedidos, gestorRepartidores);

            ventanaAsignar.setVisible(true);
        });

    }

}
