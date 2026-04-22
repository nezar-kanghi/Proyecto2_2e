import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Conectamos con la base de datos.

public class ConexionBD {

    private static final String URL      = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USUARIO  = "RIBERA";
    private static final String PASSWORD = "ribera";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}