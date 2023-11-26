import java.sql.*;

public class Prueba {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection conexion = DriverManager.getConnection("jdbc:derby:ejemploDB;create=true");
        String q1 = "CREATE TABLE paises (id INT PRIMARY KEY, nombre VARCHAR(40), presidente VARCHAR(40), PIB INT)";
        PreparedStatement sentencia = conexion.prepareStatement(q1);
        sentencia.execute();
        String q2 = "INSERT INTO paises VALUES (1,'España','Pedro Sanchez',123456)";
        sentencia = conexion.prepareStatement(q2);
        sentencia.execute();
        String q3 = "select * from paises";
        sentencia = conexion.prepareStatement(q3);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            System.out.println(resultado.getInt(1) + " " + resultado.getString(2) + " " + resultado.getString(3) + " " + resultado.getInt(4));
        }
        conexion.close();

    }
}