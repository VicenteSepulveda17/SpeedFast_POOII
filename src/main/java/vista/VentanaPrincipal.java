package vista;

import controlador.GestorPedidos;
import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame {

    private GestorPedidos gestorPedidos;
    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnAsignar;
    private JButton btnRegistrarRepartidor;

    public VentanaPrincipal() {
        gestorPedidos = new GestorPedidos();

        setTitle("SpeedFast");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnRegistrar = new JButton("Registrar pedido");
        btnRegistrarRepartidor = new JButton("Registrar repartidor");
        btnListar = new JButton("Listar pedidos");
        btnAsignar = new JButton("Agisnar repartidor / Iniciar entrega");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(btnRegistrar);
        panel.add(btnRegistrarRepartidor);
        panel.add(btnListar);
        panel.add(btnAsignar);

        add(panel);

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventanaRegistro = new VentanaRegistroPedido(gestorPedidos);
            ventanaRegistro.setVisible(true);

        });

        btnRegistrarRepartidor.addActionListener(e -> {
            VentanaRegistroRepartidor ventanaRegistro =
                    new VentanaRegistroRepartidor();
            ventanaRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos ventanaLista = new VentanaListaPedidos();
            ventanaLista.setVisible(true);
        });

        btnAsignar.addActionListener(e -> {
            VentanaAsignarRepartidor ventanaAsignar =
                    new VentanaAsignarRepartidor();

            ventanaAsignar.setVisible(true);
        });

    }

}
