import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskService {

    Scanner sc = new Scanner(System.in);

    public void addTask(int userId) {

        System.out.print("Enter task title: ");
        String title = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        String sql =
            "INSERT INTO tasks(user_id, title, description, status) VALUES(?,?,?,?)";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, "Pending");

            ps.executeUpdate();

            System.out.println("Task added successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewTasks(int userId) {

        String sql = "SELECT * FROM tasks WHERE user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- YOUR TASKS -----");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("----------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTask(int userId) {

        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new title: ");
        String title = sc.nextLine();

        System.out.print("Enter new description: ");
        String description = sc.nextLine();

        String sql =
            "UPDATE tasks SET title=?, description=? WHERE id=? AND user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.setInt(4, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Task updated successfully!");
            } else {
                System.out.println("Task not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int userId) {

        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM tasks WHERE id=? AND user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Task deleted successfully!");
            } else {
                System.out.println("Task not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markCompleted(int userId) {

        System.out.print("Enter task ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql =
            "UPDATE tasks SET status='Completed' WHERE id=? AND user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Task marked as completed!");
            } else {
                System.out.println("Task not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showDashboard(int userId) {

        String sql =
            "SELECT COUNT(*) AS total, " +
            "SUM(CASE WHEN status='Completed' THEN 1 ELSE 0 END) AS completed " +
            "FROM tasks WHERE user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n----- DASHBOARD -----");
                System.out.println("Total tasks: " + rs.getInt("total"));
                System.out.println("Completed tasks: " + rs.getInt("completed"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}