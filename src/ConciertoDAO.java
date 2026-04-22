import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConciertoDAO {

    // Insertar un nuevo concierto
    public void insertar(Concierto concierto) {


        try (Connection conn = ConexionBD.obtenerConexion()) {

            String sql = "INSERT INTO Concierto (artista_id, fecha, lugar, precioEntrada) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, concierto.getArtista().getId());
            ps.setDate(2, new java.sql.Date(concierto.getFecha().getTime())); //pasamos el String a Date para que lo entienda el SQL
            ps.setString(3, concierto.getLugar());
            ps.setDouble(4, concierto.getPrecioEntrada());
            ps.executeUpdate();
            System.out.println("Concierto insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar concierto: " + e.getMessage());
        }
    }

    // Obtener todos los conciertos con datos del artista
    public List<Concierto> listarTodos() {
        List<Concierto> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.fecha, c.lugar, c.precioEntrada, "
                + "a.id AS artista_id, a.nombre, a.generoMusical, a.paisOrigen "
                + "FROM Concierto c "
                + "JOIN Artista a ON c.artista_id = a.id";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Artista artista = new Artista(
                        rs.getInt("artista_id"),
                        rs.getString("nombre"),
                        rs.getString("generoMusical"),
                        rs.getString("paisOrigen")
                );
                lista.add(new Concierto(
                        rs.getInt("id"),
                        artista,
                        rs.getDate("fecha"),
                        rs.getString("lugar"),
                        rs.getDouble("precioEntrada")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar conciertos: " + e.getMessage());
        }

        return lista;
    }

    //Buscar un concierto por id con datos del artista

    public Concierto buscarPorId(int id) {

        Concierto concierto = null; //Por si no hay ninguno

        try (Connection conn = ConexionBD.obtenerConexion()) {

            String sql = "SELECT c.id, c.fecha, c.lugar, c.precioEntrada, "
                    + "a.id AS artista_id, a.nombre, a.generoMusical, a.paisOrigen "
                    + "FROM Concierto c "
                    + "INNER JOIN Artista a ON c.artista_id = a.id "
                    + "WHERE c.id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Artista artista = new Artista(
                            rs.getInt("artista_id"),
                            rs.getString("nombre"),
                            rs.getString("generoMusical"),
                            rs.getString("paisOrigen")
                    );
                    concierto = new Concierto(
                            rs.getInt("id"),
                            artista,
                            rs.getDate("fecha"),
                            rs.getString("lugar"),
                            rs.getDouble("precioEntrada")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar concierto: " + e.getMessage());
        }

        return concierto;
    }

    // Eliminar un concierto por id
    public void eliminar(int id) {

        try (Connection conn = ConexionBD.obtenerConexion()){

            String sql = "DELETE FROM Concierto WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            System.out.println("Concierto eliminado correctamente." + filas);


        } catch (SQLException e) {
            System.out.println("Error al eliminar concierto: " + e.getMessage());
        }
    }
}