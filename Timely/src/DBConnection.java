import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() {

        Properties properties = new Properties();

        try {
            FileInputStream file =
                    new FileInputStream("db.properties");

            properties.load(file);
            file.close();

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    url, username, password
            );

        } catch (IOException e) {
            System.out.println("Could not read db.properties.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            System.out.println(e.getMessage());
        }

        return null;
    }
}