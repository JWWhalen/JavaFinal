import java.awt.CardLayout;

import javax.swing.JFrame;

public class HealthMonitoringAppGUI {
    private JFrame frame;
    private CardLayout cardLayout;

    public HealthMonitoringAppGUI() {
        // Create the main frame
        frame = new JFrame("Health Monitoring System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // You can adjust the size based on your content

        cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // Initialize all panels and add them to the frame
        initializeUI();

        frame.setVisible(true);
    }

    private void initializeUI() {
        // Create and add panels
        frame.add(new LoginPanel(this).getPanel(), "Login");
        frame.add(new RegistrationPanel(this).getPanel(), "Register");
        frame.add(new MainMenuPanel(this).getPanel(), "MainMenu");
        frame.add(new DoctorPatientsPanel(this).getPanel(), "DoctorPatients");
        frame.add(new PatientHealthDataPanel(this).getPanel(), "PatientHealthData");
        frame.add(new HealthRecommendationsPanel(this).getPanel(), "HealthRecommendations");
        frame.add(new HealthDataEntryPanel(this).getPanel(), "HealthData");
        frame.add(new ViewRecommendationsPanel(this).getPanel(), "Recommendations");
        frame.add(new MedicineReminderPanel(this).getPanel(), "MedicineReminder");
    }

    public void switchPanel(String panelName) {
        cardLayout.show(frame.getContentPane(), panelName);
    }

    public static void main(String[] args) {
        new HealthMonitoringAppGUI();
    }
}
