package vista;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarCliente extends JFrame {

    private JTextField txtNombre;
    private JButton btnGuardar;

    private Cliente cliente;
    private VentanaListaClientes ventanaLista;

    public VentanaEditarCliente(Cliente cliente, VentanaListaClientes ventanaLista) {

        this.cliente = cliente;
        this.ventanaLista = ventanaLista;

        setTitle("Editar cliente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        panel.add(new JLabel("Nombre:"));

        txtNombre = new JTextField(cliente.getNombre());
        panel.add(txtNombre);

        btnGuardar = new JButton("Guardar cambios");

        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> actualizarCliente());
    }

    private void actualizarCliente() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "El nombre no puede estar vacío.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        cliente.setNombre(nombre);

        ClienteDAO clienteDAO = new ClienteDAO();

        boolean actualizado = clienteDAO.actualizar(cliente);

        if (actualizado) {

            ventanaLista.cargarClientes();

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente actualizado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el cliente.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
