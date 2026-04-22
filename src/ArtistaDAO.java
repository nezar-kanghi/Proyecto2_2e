import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO { //OPERACIONES CRUD DE ARTISTA

    // Insertar un nuevo artista
    public void insertar(Artista artista) {

        String sql = "INSERT INTO Artista (nombre, generoMusical, paisOrigen) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, artista.getNombre());
            ps.setString(2, artista.getGeneroMusical());
            ps.setString(3, artista.getPaisOrigen());
            ps.executeUpdate();
            System.out.println("Artista insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar artista: " + e.getMessage());
        }
    }

    // Obtener todos los artistas
    public List<Artista> listarTodos() {

        List<Artista> lista = new ArrayList<>(); //VAMOS A GUARDAR LOS DATOS EN UN ARRAYLIST

        String sql = "SELECT id, nombre, generoMusical, paisOrigen FROM Artista";

        try (Connection conn = ConexionBD.obtenerConexion();
             Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Artista(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("generoMusical"),
                        rs.getString("paisOrigen")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar artistas: " + e.getMessage());
        }

        return lista;
    }

    //Buscar un artista por id
    public Artista buscarPorId(int id) {

        String sql = "SELECT id, nombre, generoMusical, paisOrigen FROM Artista WHERE id = ?";
        Artista artista = null; //ponemos null por si no existe ningun artista ya que el metodo devuelve un objeto y si no hay anda tiene que devolver null

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            //Mostramos el artista de dicho id
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    artista = new Artista(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("generoMusical"),
                            rs.getString("paisOrigen")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar artista: " + e.getMessage());
        }

        return artista; //devolvemos el resultado
    }

    //Eliminar un artista por id
    public void eliminar(int id) {
        String sql = "DELETE FROM Artista WHERE id = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Artista eliminado correctamente.");
            } else {
                System.out.println("No se encontro ningun artista con ese id.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar artista: " + e.getMessage());
        }
    }
}