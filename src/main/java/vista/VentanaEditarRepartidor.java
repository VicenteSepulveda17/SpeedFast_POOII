package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaEditarRepartidor extends JFrame {

    private Repartidor repartidor;
    private  VentanaListaRepartidores ventanaLista;

    private JTextField txtNombre;
    private JButton btnGuardar;

    public VentanaEditarRepartidor(Repartidor repartidor, VentanaListaRepartidores ventanaLista) {

        this.repartidor = repartidor;
        this.ventanaLista = ventanaLista;

        setTitle("Editar Repartidor");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID: " + repartidor.getIdRepartidor());
        JLabel lblNombre = new JLabel("Nombre:");

        txtNombre = new JTextField(repartidor.getNombre());

        btnGuardar = new JButton("Guardar cambios");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(lblId);
        panel.add(new JLabel(""));

        panel.add(lblNombre);
        panel.add(txtNombre);

        panel.add(new JLabel(""));
        panel.add(btnGuardar);

        add(panel);

        btnGuardar.addActionListener(e -> guardarCambios());
    }

    private void guardarCambios() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "El nombre no puede estar vacío.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        repartidor.setNombre(nombre);

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        boolean actualizado = repartidorDAO.actualizar(repartidor);

        if (actualizado) {

            ventanaLista.cargarRepartidores();

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor actualizado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );


            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}