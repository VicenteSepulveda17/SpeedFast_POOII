package vista;

import dao.EntregaDAO;
import modelo.Entrega;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaEntregas extends JFrame {

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VentanaListaEntregas() {

        setTitle("Lista de entregas");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Pedido");
        modeloTabla.addColumn("Repartidor");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");

        tablaEntregas = new JTable(modeloTabla);
        btnEditar = new JButton("Editar entrega");
        btnEliminar = new JButton("Eliminar entrega");

        JScrollPane scrollPane = new JScrollPane(tablaEntregas);

        add(scrollPane, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        cargarEntregas();
        btnEditar.addActionListener(e -> editarEntrega());
        btnEliminar.addActionListener(e -> eliminarEntrega());
    }

    private void cargarEntregas() {

        modeloTabla.setRowCount(0);

        EntregaDAO entregaDAO = new EntregaDAO();

        for (Entrega entrega : entregaDAO.listarTodos()) {

            modeloTabla.addRow(new Object[]{
                    entrega.getId(),
                    entrega.getIdPedido(),
                    entrega.getIdRepartidor(),
                    entrega.getFecha(),
                    entrega.getHora()
            });
        }
    }

    private void editarEntrega() {

        int filaSeleccionada = tablaEntregas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una entrega de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 1);
        int idRepartidor = (int) modeloTabla.getValueAt(filaSeleccionada, 2);

        java.time.LocalDate fecha =
                (java.time.LocalDate) modeloTabla.getValueAt(filaSeleccionada, 3);

        java.time.LocalTime hora =
                (java.time.LocalTime) modeloTabla.getValueAt(filaSeleccionada, 4);

        Entrega entrega = new Entrega(
                id,
                idPedido,
                idRepartidor,
                fecha,
                hora
        );

        VentanaEditarEntrega ventanaEditar =
                new VentanaEditarEntrega(entrega);

        ventanaEditar.setVisible(true);

        ventanaEditar.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                cargarEntregas();
            }
        });
    }

    private void eliminarEntrega() {

        int filaSeleccionada = tablaEntregas.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una entrega de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idEntrega = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        Object[] opciones = {"Sí", "No"};

        int confirmacion = JOptionPane.showOptionDialog(
                this,
                "¿Seguro que quieres eliminar esta entrega?",
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

        EntregaDAO entregaDAO = new EntregaDAO();

        boolean eliminado = entregaDAO.eliminar(idEntrega);

        if (eliminado) {

            cargarEntregas();

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega eliminada correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
