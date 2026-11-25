import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class DailyNoteService {

    Scanner sc = new Scanner(System.in);

    public void addNote(int userId) {

        System.out.print("Write your daily note: ");
        String note = sc.nextLine();

        String sql =
            "INSERT INTO daily_notes(user_id, note_date, note_text) VALUES(?,?,?)";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(3, note);

            ps.executeUpdate();

            System.out.println("Note saved successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewNotes(int userId) {

        String sql =
            "SELECT * FROM daily_notes WHERE user_id=? ORDER BY note_date DESC";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n----- YOUR DAILY NOTES -----");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Date: " + rs.getDate("note_date"));
                System.out.println("Note: " + rs.getString("note_text"));
                System.out.println("----------------------------");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateNote(int userId) {

        System.out.print("Enter note ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter updated note: ");
        String note = sc.nextLine();

        String sql =
            "UPDATE daily_notes SET note_text=? WHERE id=? AND user_id=?";

        Connection conn = DBConnection.getConnection();

        if (conn == null) {
            return;
        }

        try (Connection c = conn;
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, note);
            ps.setInt(2, id);
            ps.setInt(3, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Note updated successfully!");
            } else {
                System.out.println("Note not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteNote(int userId) {

        System.out.print("Enter note ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql =
            "DELETE FROM daily_notes WHERE id=? AND user_id=?";

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
                System.out.println("Note deleted successfully!");
            } else {
                System.out.println("Note not found.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}