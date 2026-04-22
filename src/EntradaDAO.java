import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EntradaDAO {

    //Metodo para guardar una nueva venta de entradas
    public void insertar(Entrada entrada) {

        try (Connection conn = ConexionBD.obtenerConexion()) {

            String sql = "INSERT INTO Entrada (concierto_id, comprador, cantidad, fechaCompra) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, entrada.getConcierto().getId());
            ps.setString(2, entrada.getComprador());
            ps.setInt(3, entrada.getCantidad());
            ps.setDate(4, new java.sql.Date(entrada.getFechaCompra().getTime())); //Transformamos la fecha string en date para que la entienda oracle
            ps.executeUpdate();
            System.out.println("Entrada registrada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al registrar entrada: " + e.getMessage());
        }
    }

    // Obtener todas las entradas con datos del concierto y del artista
    public List<Entrada> listarTodas() {
        List<Entrada> lista = new ArrayList<>(); //nueva lista para guardar los datos de entrada

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement()) {

            String sql = "SELECT e.id, e.comprador, e.cantidad, e.fechaCompra, "
                    + "c.id AS concierto_id, c.lugar, c.precioEntrada, c.fecha AS fecha_concierto, "
                    + "a.id AS artista_id, a.nombre, a.generoMusical, a.paisOrigen "
                    + "FROM Entrada e "
                    + "JOIN Concierto c ON e.concierto_id = c.id "
                    + "JOIN Artista a ON c.artista_id = a.id";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Artista artista = new Artista(
                        rs.getInt("artista_id"),
                        rs.getString("nombre"),
                        rs.getString("generoMusical"),
                        rs.getString("paisOrigen")
                );
                Concierto concierto = new Concierto(
                        rs.getInt("concierto_id"),
                        artista,
                        rs.getDate("fecha_concierto"),
                        rs.getString("lugar"),
                        rs.getDouble("precioEntrada")
                );
                lista.add(new Entrada(
                        rs.getInt("id"), concierto,
                        rs.getString("comprador"),
                        rs.getInt("cantidad"),
                        rs.getDate("fechaCompra")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entradas: " + e.getMessage());
        }

        return lista;
    }
}