package vista;


import modelo.EstadoPedido;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.PedidoDAO;

import java.awt.*;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnEditar;
    private JButton btnEliminar;

    public VentanaListaPedidos() {


        setTitle("Lista de pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Estado");

        tablaPedidos = new JTable(modeloTabla);
        btnEditar = new JButton("Editar Pedido");
        btnEliminar = new JButton("Eliminar pedido");

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarPedidos();

        btnEditar.addActionListener(e -> editarPedido());
        btnEliminar.addActionListener(e -> eliminarPedido());
    }

    public void cargarPedidos() {

        modeloTabla.setRowCount(0);

        PedidoDAO pedidoDAO = new PedidoDAO();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            modeloTabla.addRow(new Object[]{
                    pedido.getId_pedido(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getEstado()
            });
        }
    }

    private void editarPedido() {

        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un pedido de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String direccion = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String tipo = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        EstadoPedido estado = EstadoPedido.valueOf(
                modeloTabla.getValueAt(filaSeleccionada, 3).toString()
        );

        Pedido pedido = new Pedido(
                idPedido,
                direccion,
                tipo,
                estado
        );

        VentanaEditarPedido ventanaEditar = new VentanaEditarPedido(pedido, this);
        ventanaEditar.setVisible(true);
    }

    private void eliminarPedido() {

        int filaSeleccionada = tablaPedidos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un pedido de la tabla.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        Object[] opciones = {"Sí", "No"};

        int confirmacion = JOptionPane.showOptionDialog(
                this,
                "¿Estas seguro de eliminar el pedido " + idPedido + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[1]
        );

        if (confirmacion != 0){
            return;
        }

        PedidoDAO pedidoDAO = new PedidoDAO();

        boolean eliminado = pedidoDAO.eliminar(idPedido);

        if (eliminado) {

            cargarPedidos();

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido eliminado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

