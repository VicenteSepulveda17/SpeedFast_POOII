package vista;

import controlador.GestorPedidos;
import controlador.GestorRepartidores;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaAsignarRepartidor extends JFrame {

    private GestorPedidos gestorPedidos;
    private GestorRepartidores gestorRepartidores;
    private JComboBox<Repartidor> cmbRepartidor;
    private JComboBox<modelo.Pedido> cmbPedido;
    private JButton btnIniciarEntrega;

    public VentanaAsignarRepartidor(
            GestorPedidos gestorPedidos,
            GestorRepartidores gestorRepartidores) {

        this.gestorPedidos = gestorPedidos;
        this.gestorRepartidores = gestorRepartidores;

        setTitle("Asignar repartidor");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Repartidor:"));

        cmbRepartidor = new JComboBox<>();

        for (Repartidor repartidor : gestorRepartidores.getRepartidores()) {
            cmbRepartidor.addItem(repartidor);
        }

        cmbPedido = new JComboBox<>();

        for (modelo.Pedido pedido : gestorPedidos.getPedidos()) {
            cmbPedido.addItem(pedido);
        }

        panel.add(cmbRepartidor);

        panel.add(new JLabel("Pedido:"));
        panel.add(cmbPedido);

        btnIniciarEntrega = new JButton("Iniciar entrega");

        panel.add(new JLabel(""));
        panel.add(btnIniciarEntrega);

        add(panel);

        btnIniciarEntrega.addActionListener(e -> {

            Repartidor repartidor = (Repartidor) cmbRepartidor.getSelectedItem();
            modelo.Pedido pedido = (modelo.Pedido) cmbPedido.getSelectedItem();

            if (repartidor == null || pedido == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe seleccionar un repartidor y un pedido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega iniciada correctamente.\n\n" +
                            "Pedido: " + pedido.getId_pedido() + "\n" +
                            "Dirección: " + pedido.getDireccion() + "\n" +
                            "Repartidor: " + repartidor.getNombre() + "\n" +
                            "ID repartidor: " + repartidor.getIdRepartidor(),
                    "Entrega iniciada",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
