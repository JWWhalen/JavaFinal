import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicineReminderManager {

    public MedicineReminderManager() {
    }

    public boolean addReminder(MedicineReminder reminder) {
        String query = "INSERT INTO medicine_reminders (user_id, medicine_name, dosage, schedule, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
    
            ps.setInt(1, reminder.getUserId());
            ps.setString(2, reminder.getMedicineName());
            ps.setString(3, reminder.getDosage());
            ps.setString(4, reminder.getSchedule());
    
            // Convert String to java.sql.Date
            java.sql.Date startDate = java.sql.Date.valueOf(reminder.getStartDate());
            java.sql.Date endDate = java.sql.Date.valueOf(reminder.getEndDate());
    
            ps.setDate(5, startDate);
            ps.setDate(6, endDate);
    
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MedicineReminder> getRemindersForUser(int userId) {
        List<MedicineReminder> userReminders = new ArrayList<>();
        String query = "SELECT * FROM medicine_reminders WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MedicineReminder reminder = new MedicineReminder(
                        rs.getInt("user_id"),
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("schedule"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                    );
                    userReminders.add(reminder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userReminders;
    }

    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDate now = LocalDate.now();
        String query = "SELECT * FROM medicine_reminders WHERE user_id = ? AND start_date <= ? AND end_date >= ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, now.toString()); // start_date
            ps.setString(3, now.toString()); // end_date

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MedicineReminder reminder = new MedicineReminder(
                        rs.getInt("user_id"),
                        rs.getString("medicine_name"),
                        rs.getString("dosage"),
                        rs.getString("schedule"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                    );
                    dueReminders.add(reminder);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dueReminders;
    }
}
