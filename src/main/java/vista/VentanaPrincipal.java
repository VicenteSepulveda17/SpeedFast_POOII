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
    private JButton btnListarRepartidores;
    private JButton btnListarEntregas;
    private JButton btnRegistrarCliente;
    private JButton btnListarClientes;

    public VentanaPrincipal() {
        gestorPedidos = new GestorPedidos();

        setTitle("SpeedFast");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnRegistrar = new JButton("Registrar pedido");
        btnRegistrarRepartidor = new JButton("Registrar repartidor");
        btnListar = new JButton("Listar pedidos");
        btnAsignar = new JButton("Asignar repartidor / Iniciar entrega");
        btnListarRepartidores = new JButton("Listar Repartidores");
        btnListarEntregas = new JButton("Listar Entregas");
        btnRegistrarCliente = new JButton("Registrar Cliente");
        btnListarClientes = new JButton("Listar Clientes");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1,10,10));

        panel.add(btnRegistrar);
        panel.add(btnRegistrarRepartidor);
        panel.add(btnRegistrarCliente);
        panel.add(btnAsignar);
        panel.add(btnListar);
        panel.add(btnListarRepartidores);
        panel.add(btnListarEntregas);
        panel.add(btnListarClientes);

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
            VentanaListaPedidos ventanaLista =
                    new VentanaListaPedidos();

            ventanaLista.setVisible(true);
        });

        btnAsignar.addActionListener(e -> {
            VentanaAsignarRepartidor ventanaAsignar =
                    new VentanaAsignarRepartidor();

            ventanaAsignar.setVisible(true);
        });

        btnListarRepartidores.addActionListener(e -> {
            VentanaListaRepartidores ventanaListaRepartidores =
                    new VentanaListaRepartidores();

            ventanaListaRepartidores.setVisible(true);
        });

        btnListarEntregas.addActionListener(e ->{
            VentanaListaEntregas ventanaListaEntregas =
                    new VentanaListaEntregas();

            ventanaListaEntregas.setVisible(true);
        });

        btnRegistrarCliente.addActionListener(e -> {
            VentanaRegistroCliente ventanaRegistroCliente =
                    new VentanaRegistroCliente();

            ventanaRegistroCliente.setVisible(true);
        });

        btnListarClientes.addActionListener(e -> {
            VentanaListaClientes ventanaListaClientes = new VentanaListaClientes();
            ventanaListaClientes.setVisible(true);
        });

    }
}
