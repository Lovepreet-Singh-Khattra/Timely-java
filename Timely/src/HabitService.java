import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class HabitService {

    Scanner sc = new Scanner(System.in);

    public void addHabit(int userId) {

        System.out.print("Enter habit name: ");
        String name = sc.nextLine();

        String sql =
            "INSERT INTO habits(user_id, habit_name) VALUES(?,?)";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, name);

            ps.executeUpdate();

            System.out.println("Habit added successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewHabits(int userId) {

        String sql = "SELECT * FROM habits WHERE user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- YOUR HABITS -----");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Habit: " + rs.getString("habit_name"));
                System.out.println(
                    "Last completed: " + rs.getString("last_completed_date")
                );
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markCompletedToday(int userId) {

        System.out.print("Enter habit ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql =
            "UPDATE habits SET last_completed_date=? WHERE id=? AND user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ps.setInt(2, id);
            ps.setInt(3, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Habit completed for today!");
            } else {
                System.out.println("Habit not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showCompletedToday(int userId) {

        String sql =
            "SELECT COUNT(*) AS total FROM habits " +
            "WHERE user_id=? AND last_completed_date=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                    "Habits completed today: " + rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}