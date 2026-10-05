package vista;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaClientes extends JFrame {

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VentanaListaClientes() {

        setTitle("Lista de clientes");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");

        tablaClientes = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaClientes);

        add(scrollPane, BorderLayout.CENTER);

        btnEditar = new JButton("Editar Cliente");
        btnEliminar = new JButton("Eliminar Cliente");

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarClientes();

        btnEditar.addActionListener(e -> editarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());

    }

    public void cargarClientes() {

        modeloTabla.setRowCount(0);

        ClienteDAO clienteDAO = new ClienteDAO();

        List<Cliente> clientes = clienteDAO.listarTodos();

        for (Cliente cliente : clientes) {

            modeloTabla.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombre()
            });
        }
    }

    private void editarCliente() {

        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un cliente de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

        Cliente cliente = new Cliente(id, nombre);

        VentanaEditarCliente ventanaEditar =
                new VentanaEditarCliente(cliente, this);

        ventanaEditar.setVisible(true);
    }

    private void eliminarCliente() {

        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un cliente de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

        int confirmacion = JOptionPane.showOptionDialog(
                this,
                "¿Seguro que quieres eliminar al cliente " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new Object[]{"Sí", "No"},
                "No"
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            ClienteDAO clienteDAO = new ClienteDAO();

            boolean eliminado = clienteDAO.eliminar(id);

            if (eliminado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente eliminado correctamente.",
                        "Confirmación",
                        JOptionPane.INFORMATION_MESSAGE
                );

                cargarClientes();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el cliente.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
