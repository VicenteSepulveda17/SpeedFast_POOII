package vista;


import dao.EntregaDAO;
import modelo.Entrega;
import modelo.Repartidor;
import dao.RepartidorDAO;
import dao.PedidoDAO;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaAsignarRepartidor extends JFrame {

    private JComboBox<Repartidor> cmbRepartidor;
    private JComboBox<modelo.Pedido> cmbPedido;
    private JButton btnIniciarEntrega;

    public VentanaAsignarRepartidor() {

        setTitle("Asignar repartidor");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Repartidor:"));

        cmbRepartidor = new JComboBox<>();

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        for (Repartidor repartidor : repartidorDAO.listarTodos()) {
            cmbRepartidor.addItem(repartidor);
        }

        cmbPedido = new JComboBox<>();

        PedidoDAO pedidoDAO = new PedidoDAO();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            cmbPedido.addItem(pedido);
        }

        panel.add(cmbRepartidor);

        panel.add(new JLabel("Pedido:"));
        panel.add(cmbPedido);

        btnIniciarEntrega = new JButton("Iniciar entrega");

        panel.add(new JLabel(""));
        panel.add(btnIniciarEntrega);

        btnIniciarEntrega.addActionListener(e -> {

            Repartidor repartidor = (Repartidor) cmbRepartidor.getSelectedItem();
            Pedido pedido = (Pedido) cmbPedido.getSelectedItem();

            Entrega entrega = new Entrega(
                    pedido.getId_pedido(),
                    repartidor.getIdRepartidor(),
                    LocalDate.now(),
                    LocalTime.now()
            );

            EntregaDAO entregaDAO = new EntregaDAO();
            entregaDAO.guardar(entrega);

            JOptionPane.showMessageDialog(this, "Entrega registrada correctamente.");
        });

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
