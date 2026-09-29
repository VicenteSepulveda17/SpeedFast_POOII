package vista;


import modelo.Pedido;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.PedidoDAO;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    public VentanaListaPedidos() {


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

        PedidoDAO pedidoDAO = new PedidoDAO();

        for (Pedido pedido : pedidoDAO.listarTodos()) {
            modeloTabla.addRow(new Object[]{
                    pedido.getId_pedido(),
                    pedido.getDireccion(),
                    pedido.getTipo()
            });
        }
    }
}

