import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    /**
     * retrieves a doctor from the database based on the provided doctorId.
     *
     * @param  doctorId   the unique identifier of the doctor
     * @return           the Doctor object corresponding to the doctorId, or null if not found
     */
    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = null;
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                // Assuming Doctor class has constructor with these parameters
                doctor = new Doctor(doctorId, firstName, lastName, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    
    public class DoctorPatientRelationship { 
        private int doctorId;//doctor
        private int patientId;//patent
      
        public DoctorPatientRelationship(int doctorId, int patientId) { 
            this.doctorId = doctorId;
            this.patientId = patientId;
        }
        
        public int getDoctorId() {
            return doctorId;
        }
    
        public int getPatientId() {
            return patientId;
        }
    }
    
    public List<HealthData> getHealthDataByPatientId(int patientId) {
        return healthDataDao.getHealthDataByUserId(patientId);
    }

    public List<User> getPatientsByDoctorId(int doctorId) {
        List<User> patients = new ArrayList<>();
        String query = "SELECT * FROM users WHERE is_doctor = false AND id IN (SELECT patient_id FROM doctor_patient WHERE doctor_id = ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isDoctor = rs.getBoolean("is_doctor");
                patients.add(new User(userId, firstName, lastName, email, password, isDoctor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
    public boolean addOrModifyHealthRecommendations(int userId, List<String> recommendations) {
        String insertQuery = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES (?, ?, CURRENT_DATE)";
        String deleteQuery = "DELETE FROM recommendations WHERE user_id = ?";
        
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement deletePs = con.prepareStatement(deleteQuery);
             PreparedStatement insertPs = con.prepareStatement(insertQuery)) {
            
            //delete existing recommendations for the patient
            deletePs.setInt(1, userId);
            deletePs.executeUpdate();
            
            // insert new recommendations
            for (String recommendation : recommendations) {
                insertPs.setInt(1, userId);
                insertPs.setString(2, recommendation);
                insertPs.addBatch();
            }
            insertPs.executeBatch();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean assignPatientToDoctor(int patientId, int doctorId) {
        String updateQuery = "UPDATE users SET doctor_id = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, patientId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<DoctorPatientRelationship> getDoctorPatients(int doctorId) {
        List<DoctorPatientRelationship> relationships = new ArrayList<>();
        String query = "SELECT * FROM doctor_patient WHERE doctor_id = ?";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int patientId = rs.getInt("patient_id");
                    relationships.add(new DoctorPatientRelationship(doctorId, patientId));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relationships;
    }
    // Add more methods for other doctor-specific tasks
}
