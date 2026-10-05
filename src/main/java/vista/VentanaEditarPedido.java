package vista;

import dao.PedidoDAO;
import modelo.EstadoPedido;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarPedido extends JFrame {

    private VentanaListaPedidos ventanaLista;

    private Pedido pedido;

    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JComboBox<EstadoPedido> cmbEstado;
    private JButton btnGuardar;

    public VentanaEditarPedido(Pedido pedido, VentanaListaPedidos ventanaLista) {

        this.pedido = pedido;
        this.ventanaLista = ventanaLista;

        setTitle("Editar Pedido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID: " + pedido.getId_pedido());
        JLabel lblDireccion = new JLabel("Dirección:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblEstado = new JLabel("Estado:");

        txtDireccion = new JTextField(pedido.getDireccion());

        cmbTipo = new JComboBox<>(new String[]{
                "COMIDA",
                "ENCOMIENDA",
                "EXPRESS"
        });
        cmbTipo.setSelectedItem(pedido.getTipo());

        cmbEstado = new JComboBox<>(EstadoPedido.values());
        cmbEstado.setSelectedItem(pedido.getEstado());

        btnGuardar = new JButton("Guardar cambios");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        panel.add(lblId);
        panel.add(new JLabel(""));

        panel.add(lblDireccion);
        panel.add(txtDireccion);

        panel.add(lblTipo);
        panel.add(cmbTipo);

        panel.add(lblEstado);
        panel.add(cmbEstado);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void guardarCambios() {

        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        EstadoPedido estado = (EstadoPedido) cmbEstado.getSelectedItem();

        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "La dirección no puede estar vacía.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        pedido.setDireccion(direccion);
        pedido.setTipo(tipo);
        pedido.setEstado(estado);

        PedidoDAO pedidoDAO = new PedidoDAO();

        boolean actualizado = pedidoDAO.actualizar(pedido);

        if (actualizado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido actualizado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );
            ventanaLista.cargarPedidos();
            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
