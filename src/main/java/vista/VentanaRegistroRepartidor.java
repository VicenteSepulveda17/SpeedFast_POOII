package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroRepartidor extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JButton btnGuardar;

    public VentanaRegistroRepartidor() {

        setTitle("Registrar repartidor");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("ID:"));
        txtId = new JTextField();
        panel.add(txtId);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        btnGuardar = new JButton("Guardar");
        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarRepartidor());
    }

    private void guardarRepartidor() {

        String idTexto = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (idTexto.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Completa todos los campos.");
            return;
        }

        try {

            int id = Integer.parseInt(idTexto);

            if (id <= 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "El ID debe ser mayor que 0.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Repartidor repartidor = new Repartidor(id, nombre);
            RepartidorDAO repartidorDAO = new RepartidorDAO();

            boolean guardado = repartidorDAO.guardar(repartidor);

            if (guardado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Repartidor registrado correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );

                txtId.setText("");
                txtNombre.setText("");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el repartidor.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "El ID debe ser numérico.");
        }
    }
}
