package vista;

import controlador.GestorPedidos;
import modelo.Pedido;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private GestorPedidos gestorPedidos;

    public VentanaListaPedidos(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;


        setTitle("Lista de pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");

        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        add(scrollPane);
        cargarPedidos();
    }

    private void cargarPedidos() {
        for (Pedido pedido : gestorPedidos.getPedidos()) {
            modeloTabla.addRow(new Object[]{
                    pedido.getId_pedido(),
                    pedido.getDireccion(),
                    pedido.getTipo()
            });
        }
    }
}

