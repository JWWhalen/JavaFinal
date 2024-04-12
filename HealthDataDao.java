
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HealthDataDao {
    /**
     * A description of the entire Java function.
     *
     * @param  healthData   description of parameter
     * @return         	description of return value
     */
    public boolean createHealthData(HealthData healthData) {
        String query = "INSERT INTO health_data (user_id, weight, height, steps, heart_rate, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getCon();
             PreparedStatement statement = con.prepareStatement(query)) {
            
            statement.setInt(1, healthData.getUserId());
            statement.setDouble(2, healthData.getWeight());
            statement.setDouble(3, healthData.getHeight());
            statement.setInt(4, healthData.getSteps());
            statement.setInt(5, healthData.getHeartRate());
    
            // Convert String to java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(healthData.getDate());
            statement.setDate(6, sqlDate);
    
            int updatedRows = statement.executeUpdate();
            return updatedRows != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        }

        /**
         * a method to save recommendations for a specific user in the database.
         *
         * @param recommendations   a list of recommendations to be saved
         * @param userId            the ID of the user for whom recommendations are being saved
         * @return                  true if all recommendations were successfully saved, false otherwise
         */
        public boolean saveRecommendations(List<String> recommendations, int userId) {
            String query = "INSERT INTO recommendations (user_id, recommendation_text, date) VALUES (?, ?, CURRENT_DATE)";
            try (Connection con = DatabaseConnection.getCon();
                 PreparedStatement ps = con.prepareStatement(query)) {
                int count = 0;
                for (String rec : recommendations) {
                    ps.setInt(1, userId);
                    ps.setString(2, rec);
                    ps.addBatch(); // add to batch
                    count++;
                }
                int[] results = ps.executeBatch(); // execute insert
                return results.length == count; // check if all inserts were successful
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        public List<String> getRecommendationsByUserId(int userId) {
            List<String> recommendations = new ArrayList<>();
            String query = "SELECT recommendation_text FROM recommendations WHERE user_id = ? ORDER BY date DESC";
            try (Connection con = DatabaseConnection.getCon();
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    recommendations.add(rs.getString("recommendation_text"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return recommendations;
        }
        
  public HealthData getHealthDataById(int id) { 
  // get health data by id from database
  int healthDataId = 0;
  int userId = 0;
  double weight = 0.0;
  double height = 0.0;
  int steps = 0;
  int heartRate = 0;
  String date = null;

  String query = "SELECT * FROM health_data WHERE id = ?";

    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        healthDataId = rs.getInt("id");
        userId = rs.getInt("user_id");
        weight = rs.getDouble("weight");
        height = rs.getDouble("height");
        steps = rs.getInt("steps");
        heartRate = rs.getInt("heart_rate");
        date = rs.getString("date");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new HealthData(healthDataId, userId, weight, height, steps, heartRate, date);
  }
// get health data by user id from database 
  public List<HealthData> getHealthDataByUserId(int userId) { 
    int healthDataId = 0;
    double weight = 0.0;
    double height = 0.0;
    int steps = 0;
    int heartRate = 0;
    String date = null;

    String query = "SELECT * FROM health_data WHERE user_id = ?";

    List<HealthData> healthDataList = new ArrayList<>();

    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        healthDataId = rs.getInt("id");
        weight = rs.getDouble("weight");
        height = rs.getDouble("height");
        steps = rs.getInt("steps");
        heartRate = rs.getInt("heart_rate");
        date = rs.getString("date");
        healthDataList.add(new HealthData(healthDataId, userId, weight, height, steps, heartRate, date));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return healthDataList;
  }

  public boolean updateHealthData(HealthData healthData) { 
  // update health data in the database
  String query = "UPDATE health_data SET user_id = ?, weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? WHERE id = ?";

  try {
    Connection con = DatabaseConnection.getCon();
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, healthData.getUserId());
    statement.setDouble(2, healthData.getWeight());
    statement.setDouble(3, healthData.getHeight());
    statement.setInt(4, healthData.getSteps());
    statement.setInt(5, healthData.getHeartRate());
    statement.setString(6, healthData.getDate());
    statement.setInt(7, healthData.getId());
    int updatedRows = statement.executeUpdate();
    if (updatedRows != 0) {
      return true;
    }
  } catch (SQLException e) {
    e.printStackTrace();

  }
  return false;
}


  public boolean deleteHealthData(int id) { 
  /* delete health data from the database */ 
  String query = "DELETE FROM health_data WHERE id = ?";

    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, id);
      int updatedRows = statement.executeUpdate();
      if (updatedRows != 0) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
}
