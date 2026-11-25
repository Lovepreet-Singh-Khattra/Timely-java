import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService {

    Scanner sc = new Scanner(System.in);

    public User login() {

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return null;
        }

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");

                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }

            System.out.println("Invalid email or password.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void register() {

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        String sql =
            "INSERT INTO users(name, email, password) VALUES(?,?,?)";

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            ps.executeUpdate();

            System.out.println("Registration successful!");

        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    public void viewProfile(User user) {

        System.out.println("\n----- MY PROFILE -----");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    public void updateProfile(User user) {

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new email: ");
        String email = sc.nextLine();

        String sql = "UPDATE users SET name=?, email=? WHERE id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, user.getId());

            ps.executeUpdate();

            user.setName(name);
            user.setEmail(email);

            System.out.println("Profile updated successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}