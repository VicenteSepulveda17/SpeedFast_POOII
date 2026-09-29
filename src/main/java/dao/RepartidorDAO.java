package dao;

import modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public List<Repartidor> listarTodos() {

        List<Repartidor> repartidores = new ArrayList<>();

        String sql = "SELECT id_repartidor, nombre FROM repartidor";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id_repartidor");
                String nombre = resultado.getString("nombre");

                Repartidor repartidor = new Repartidor(id, nombre);

                repartidores.add(repartidor);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar repartidores: " + e.getMessage());
        }

        return repartidores;
    }

    public void guardar(Repartidor repartidor) {

        String sql = "INSERT INTO repartidor (id_repartidor, nombre) VALUES (?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, repartidor.getIdRepartidor());
            sentencia.setString(2, repartidor.getNombre());

            sentencia.executeUpdate();

            System.out.println("Repartidor guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar repartidor: " + e.getMessage());
        }
    }
}
