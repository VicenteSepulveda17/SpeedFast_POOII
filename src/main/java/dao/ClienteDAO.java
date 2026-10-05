package dao;

import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public boolean guardar(Cliente cliente) {

        String sql = "INSERT INTO cliente (id_cliente, nombre) VALUES (?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente guardado correctamente.");
                return true;
            } else {
                System.out.println("No se pudo guardar el cliente.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Cliente> listarTodos() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT id_cliente, nombre FROM cliente";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {

                Cliente cliente = new Cliente(
                        resultado.getInt("id_cliente"),
                        resultado.getString("nombre")
                );

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public boolean actualizar(Cliente cliente) {

        String sql = "UPDATE cliente SET nombre = ? WHERE id_cliente = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setInt(2, cliente.getIdCliente());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el cliente para actualizar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCliente) {

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el cliente para eliminar.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
