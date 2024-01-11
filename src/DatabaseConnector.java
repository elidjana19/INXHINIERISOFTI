import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection;

    public static Connection connect() {
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3307/";

            String dbName = "detyre_kursi";
            String dbUsername = "root";
            String dbPassword = "mypassword";

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);

        }catch(Exception ex){
            ex.printStackTrace();
            }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
