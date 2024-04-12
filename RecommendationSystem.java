

import java.util.ArrayList;
import java.util.List;

/**
 * In this basic version of the
 * RecommendationSystem class, complete the generateRecommendations to take a
 * HealthData object as input and generates recommendations based on the user's heart rate and step count.
 * You can also expand this class to include more health data analysis and generate more specific
 * recommendations based on the user's unique health profile
 * NOTE:
 * To integrate this class into your application, you'll need to pass the HealthData object to the generateRecommendations method
 * and store the generated recommendations in the recommendations table in the database.
 */

public class RecommendationSystem {
    private static final int MIN_HEART_RATE = 60;
    private static final int MAX_HEART_RATE = 100;
    private static final double MIN_BMI = 18.5;
    private static final double MAX_BMI = 24.9;
    private static final int MIN_STEPS = 10000;

    public List<String> generateRecommendations(HealthData healthData) {
        List<String> recommendations = new ArrayList<>();

        // Analyze heart rate
        int heartRate = healthData.getHeartRate();
        if (heartRate < MIN_HEART_RATE) {
            recommendations.add("Your heart rate is lower than the recommended range. " +
                    "Consider increasing your physical activity to improve your cardiovascular health.");
        }

        // Analyze steps
        int steps = healthData.getSteps();
        if (steps < MIN_STEPS) {
            recommendations.add("You're not reaching the recommended daily step count. " +
                    "Try to incorporate more walking or other physical activities into your daily routine.");
        }
        
        // BMI Analysis (simplistic approach)
        double heightInMeters = healthData.getHeight() / 100; // Assuming height is in cm
        double bmi = healthData.getWeight() / (heightInMeters * heightInMeters);
        if (bmi < MIN_BMI) {
            recommendations.add("Your BMI is lower than the recommended range. Consider consulting with a healthcare professional for dietary advice.");
        } else if (bmi > MAX_BMI) {
            recommendations.add("Your BMI is higher than the recommended range. Regular exercise and a balanced diet are recommended.");
        }

        return recommendations;
    }
}
