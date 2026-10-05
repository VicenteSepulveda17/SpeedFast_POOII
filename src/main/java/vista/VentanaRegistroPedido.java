package vista;

import controlador.GestorPedidos;
import modelo.Pedido;
import modelo.EstadoPedido;
import javax.swing.*;
import java.awt.*;
import dao.PedidoDAO;

public class VentanaRegistroPedido extends JFrame {

    private GestorPedidos gestorPedidos;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<EstadoPedido> cmbEstado;
    private JButton btnGuardar;

    private JLabel lblId;
    private JLabel lblDireccion;
    private JLabel lblTipo;
    private JLabel lblEstado;

    public VentanaRegistroPedido(GestorPedidos gestorPedidos){
        this.gestorPedidos = gestorPedidos;

        setTitle("Registrar Pedido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtId = new JTextField();
        txtDireccion = new JTextField();

        cmbTipo = new JComboBox<>(new String[]{
                "COMIDA",
                "ENCOMIENDA",
                "EXPRESS"

        });

        cmbEstado = new JComboBox<>(EstadoPedido.values());

        btnGuardar = new JButton("Guardar");

        lblId = new JLabel("ID:");
        lblDireccion = new JLabel("Direccion: ");
        lblTipo = new JLabel("Tipo: ");
        lblEstado = new JLabel("Estado: ");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(lblId);
        panel.add(txtId);

        panel.add(lblDireccion);
        panel.add(txtDireccion);

        panel.add(lblTipo);
        panel.add(cmbTipo);

        panel.add(lblEstado);
        panel.add(cmbEstado);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> {
            String idTexto = txtId.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

            if (idTexto.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Debe completar todos los campos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            int idPedido;

            try {
                idPedido = Integer.parseInt(idTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "El ID debe ser un número.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (idPedido <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "El ID del pedido debe ser mayor que 0.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Pedido pedido = new Pedido(idPedido, direccion, tipo, estado);

            PedidoDAO pedidoDAO = new PedidoDAO();

            boolean guardado = pedidoDAO.guardar(pedido);

            if (guardado) {

                gestorPedidos.agregarPedido(pedido);

                JOptionPane.showMessageDialog(
                        this,
                        "Pedido registrado correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );

                txtId.setText("");
                txtDireccion.setText("");
                cmbTipo.setSelectedIndex(0);
                cmbEstado.setSelectedIndex(0);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el pedido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
