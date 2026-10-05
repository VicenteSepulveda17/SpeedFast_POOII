package vista;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroCliente extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JButton btnGuardar;

    public VentanaRegistroCliente() {

        setTitle("Registrar cliente");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        panel.add(new JLabel("ID cliente:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        btnGuardar = new JButton("Guardar");
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarCliente());
    }

    private void guardarCliente() {

        String idTexto = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (idTexto.isEmpty() || nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Completa todos los campos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id;

        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número entero.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Cliente cliente = new Cliente(id, nombre);

        ClienteDAO clienteDAO = new ClienteDAO();

        boolean guardado = clienteDAO.guardar(cliente);

        if (guardado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente registrado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

            txtId.setText("");
            txtNombre.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el cliente.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
