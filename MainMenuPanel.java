import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel {
    private JPanel panel;
    private JButton enterHealthDataButton, viewRecommendationsButton, setupMedicineReminderButton, logoutButton;
    private HealthMonitoringAppGUI mainApp;

    public MainMenuPanel(HealthMonitoringAppGUI app) {
        mainApp = app;
        panel = new JPanel(new GridLayout(5, 1));

        panel.add(new JLabel("Main Menu"));

        enterHealthDataButton = new JButton("Enter Health Data");
        panel.add(enterHealthDataButton);
        enterHealthDataButton.addActionListener(e -> mainApp.switchPanel("HealthData"));

        viewRecommendationsButton = new JButton("View Recommendations");
        panel.add(viewRecommendationsButton);
        viewRecommendationsButton.addActionListener(e -> mainApp.switchPanel("Recommendations"));

        setupMedicineReminderButton = new JButton("Setup Medicine Reminder");
        panel.add(setupMedicineReminderButton);
        setupMedicineReminderButton.addActionListener(e -> mainApp.switchPanel("MedicineReminder"));

        logoutButton = new JButton("Logout");
        panel.add(logoutButton);
        logoutButton.addActionListener(e -> mainApp.switchPanel("Login"));
    }

    /**
     * a description of the entire Java function.
     *
     * @return         	description of return value
     */
    public JPanel getPanel() {
        return panel;
    }
}
