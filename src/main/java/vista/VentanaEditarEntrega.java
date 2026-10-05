package vista;

import dao.EntregaDAO;
import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaEditarEntrega extends JFrame {

    private Entrega entrega;

    private JComboBox<Repartidor> cmbRepartidor;
    private JComboBox<Pedido> cmbPedido;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JButton btnGuardar;

    public VentanaEditarEntrega(Entrega entrega) {

        this.entrega = entrega;

        setTitle("Editar Entrega");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID: " + entrega.getId());
        JLabel lblRepartidor = new JLabel("Repartidor:");
        JLabel lblPedido = new JLabel("Pedido:");
        JLabel lblFecha = new JLabel("Fecha (AAAA-MM-DD):");
        JLabel lblHora = new JLabel("Hora (HH:MM:SS):");

        cmbRepartidor = new JComboBox<>();
        cmbPedido = new JComboBox<>();

        txtFecha = new JTextField(entrega.getFecha().toString());
        txtHora = new JTextField(entrega.getHora().toString());

        btnGuardar = new JButton("Guardar cambios");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        panel.add(lblId);
        panel.add(new JLabel(""));

        panel.add(lblRepartidor);
        panel.add(cmbRepartidor);

        panel.add(lblPedido);
        panel.add(cmbPedido);

        panel.add(lblFecha);
        panel.add(txtFecha);

        panel.add(lblHora);
        panel.add(txtHora);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        cargarDatos();

        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void cargarDatos() {

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        for (Repartidor repartidor : repartidorDAO.listarTodos()) {
            cmbRepartidor.addItem(repartidor);

            if (repartidor.getIdRepartidor() == entrega.getIdRepartidor()) {
                cmbRepartidor.setSelectedItem(repartidor);
            }
        }

        PedidoDAO pedidoDAO = new PedidoDAO();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            cmbPedido.addItem(pedido);

            if (pedido.getId_pedido() == entrega.getIdPedido()) {
                cmbPedido.setSelectedItem(pedido);
            }
        }
    }

    private void guardarCambios() {

        String fechaTexto = txtFecha.getText().trim();
        String horaTexto = txtHora.getText().trim();

        if (fechaTexto.isEmpty() || horaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "La fecha y la hora no pueden estar vacías.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {

            LocalDate fecha = LocalDate.parse(fechaTexto);
            LocalTime hora = LocalTime.parse(horaTexto);

            Repartidor repartidor =
                    (Repartidor) cmbRepartidor.getSelectedItem();

            Pedido pedido =
                    (Pedido) cmbPedido.getSelectedItem();

            if (repartidor == null || pedido == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe seleccionar un pedido y un repartidor.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            entrega.setIdPedido(pedido.getId_pedido());
            entrega.setIdRepartidor(repartidor.getIdRepartidor());
            entrega.setFecha(fecha);
            entrega.setHora(hora);

            EntregaDAO entregaDAO = new EntregaDAO();
            boolean actualizada = entregaDAO.actualizar(entrega);

            if (actualizada) {
                JOptionPane.showMessageDialog(
                        this,
                        "Entrega actualizada correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar la entrega.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (java.time.format.DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha debe tener formato AAAA-MM-DD y la hora HH:MM:SS.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
