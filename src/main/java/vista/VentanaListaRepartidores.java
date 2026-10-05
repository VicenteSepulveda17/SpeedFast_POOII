package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaRepartidores extends JFrame {

    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VentanaListaRepartidores() {

        setTitle("Lista de repartidores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");

        tablaRepartidores = new JTable(modeloTabla);

        btnEditar = new JButton("Editar repartidor");
        btnEliminar = new JButton("Eliminar repartidor");

        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarRepartidores();

        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());
    }

    public void cargarRepartidores() {

        modeloTabla.setRowCount(0);

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        for (Repartidor repartidor : repartidorDAO.listarTodos()) {

            modeloTabla.addRow(new Object[]{
                    repartidor.getIdRepartidor(),
                    repartidor.getNombre()
            });
        }
    }

    private void editarRepartidor() {

        int filaSeleccionada = tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un repartidor de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idRepartidor = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

        Repartidor repartidor = new Repartidor(idRepartidor, nombre);

        VentanaEditarRepartidor ventanaEditar =
                new VentanaEditarRepartidor(repartidor, this);

        ventanaEditar.setVisible(true);
    }

    private void eliminarRepartidor() {

        int filaSeleccionada = tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un repartidor de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idRepartidor = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        Object[] opciones = {"Sí", "No"};

        int confirmacion = JOptionPane.showOptionDialog(
                this,
                "¿Seguro que quieres eliminar este repartidor?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[1]
        );

        if (confirmacion != 0) {
            return;
        }

        RepartidorDAO repartidorDAO = new RepartidorDAO();

        boolean eliminado = repartidorDAO.eliminar(idRepartidor);

        if (eliminado) {

            cargarRepartidores();

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor eliminado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}


