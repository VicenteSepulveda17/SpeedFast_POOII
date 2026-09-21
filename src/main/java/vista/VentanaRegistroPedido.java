package vista;

import controlador.GestorPedidos;
import modelo.Pedido;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private GestorPedidos gestorPedidos;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JButton btnGuardar;

    private JLabel lblId;
    private JLabel lblDireccion;
    private JLabel lblTipo;

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

        btnGuardar = new JButton("Guardar");

        lblId = new JLabel("ID:");
        lblDireccion = new JLabel("Direccion: ");
        lblTipo = new JLabel("Tipo: ");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(lblId);
        panel.add(txtId);

        panel.add(lblDireccion);
        panel.add(txtDireccion);

        panel.add(lblTipo);
        panel.add(cmbTipo);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> {
            String idTexto = txtId.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();

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

            Pedido pedido = new Pedido(idPedido, direccion, tipo);

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
        });
    }
}
